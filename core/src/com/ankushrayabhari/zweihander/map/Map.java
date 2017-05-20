package com.ankushrayabhari.zweihander.map;

import com.ankushrayabhari.zweihander.core.Constants;
import com.ankushrayabhari.zweihander.map.generation.BiomeData;
import com.ankushrayabhari.zweihander.map.generation.Center;
import com.ankushrayabhari.zweihander.map.generation.CornerComparator;
import com.ankushrayabhari.zweihander.map.generation.VoronoiGraph;
import com.ankushrayabhari.zweihander.screens.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Blending;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Disposable;

import java.util.Collections;
import java.util.Random;

public class Map implements Disposable {
	private ShaderProgram textureShader;
	private VoronoiGraph graph;
	private GameScreen game;
	private CornerComparator cornerComparator;
	private Pixmap pixmap;
	private Texture minimap;
	
	public Map(GameScreen game) {
		this.game = game;
		Random random = new Random(System.nanoTime());
		graph = OverWorldGraph.createGraph(random);
		cornerComparator = new CornerComparator();
		
		ShaderProgram.pedantic = false;
		textureShader = new ShaderProgram(Gdx.files.internal("shaders/PassthroughShader.vsh"), Gdx.files.internal("shaders/PassthroughShader.fsh"));
		pixmap = new Pixmap(Constants.BOUNDS, Constants.BOUNDS, Format.RGBA8888);
		pixmap.setBlending(Blending.None);
		for(Center center : graph.getCenterList()) {
			int length = center.corners.size()+1;
			
			float[] points = new float[length*2];
			//Add center vertex
			points[0] = (float) center.location.x;
			points[1] = (float) center.location.y;
			
			//Sort the vertices in clockwise order
			cornerComparator.setCenterPoint(center.location);
			Collections.sort(center.corners, cornerComparator);
			
			for(int i = 0; i < center.corners.size(); i++) {
				points[2*i+2] = (float) center.corners.get(i).location.x;
				points[2*i+3] = (float) center.corners.get(i).location.y;
			}
			
			//store vertex position, color and texture coordinate data
			float[] vertices = new float[length*9];
			short[] indices = new short[(length-1)*3];

		    for (int i = 0; i < length; i++) {
		         vertices[9*i] = points[2*i]; //x
		         vertices[9*i+1] = points[2*i+1]; //y
		         vertices[9*i+2] = 0; //z
		         vertices[9*i+3] = 1; //r
		         vertices[9*i+4] = 1; //g
		         vertices[9*i+5] = 1; //b
		         vertices[9*i+6] = 1; //a
		         vertices[9*i+7] = points[2*i]; //u
		         vertices[9*i+8] = -points[2*i+1]; //v
		    }
		    
		    //store index data, each triangle points to center and two neighboring vertices
		    for(int i = 0; i < length-2; i++) {
		    	indices[3*i] = 0;
		    	indices[3*i+1] = (short) (i+1);
		    	indices[3*i+2] = (short) (i+2);
		    }
		    
		    //add the missing triangle
		    indices[(length-2)*3] = 0;
		    indices[(length-2)*3+1] = (short) (length-1);
		    indices[(length-2)*3+2] = 1;
		    
		    center.vertices = points;
		    center.indices = indices;
		    
		    //create mesh with vertices and indices data, store it in polygon center
		    Mesh mesh = new Mesh(true, vertices.length, indices.length, VertexAttribute.Position(), VertexAttribute.ColorUnpacked(), VertexAttribute.TexCoords(0));
		    mesh.setVertices(vertices);
		    mesh.setIndices(indices);
		    center.mesh = mesh;
		    
		    pixmap.setColor(((BiomeData) center.biome).color);
			for(int i = 0; i<indices.length/3; i++) {
				short a = indices[3*i];
				short b = indices[3*i+1];
				short c = indices[3*i+2];
				pixmap.fillTriangle(
						(int) (center.vertices[2*a]), (int) (Constants.BOUNDS-center.vertices[2*a+1]), 
						(int) (center.vertices[2*b]), (int) (Constants.BOUNDS-center.vertices[2*b+1]), 
						(int) (center.vertices[2*c]), (int) (Constants.BOUNDS-center.vertices[2*c+1])
				);
			}
		}
		
		minimap = new Texture(pixmap);

		//map border
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.StaticBody;
		bodyDef.position.set(new Vector2(0,0));
		
		ChainShape shape = new ChainShape();
		shape.createLoop(new float[] {0, 0, 0, Constants.BOUNDS, Constants.BOUNDS, Constants.BOUNDS, Constants.BOUNDS, 0});
		
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 2.5f;
        fixtureDef.friction = 0.25f;
        fixtureDef.restitution = 0.25f;
        fixtureDef.filter.categoryBits = Constants.CATEGORY_WORLD;
        fixtureDef.filter.maskBits = Constants.MASK_WORLD;
        game.getWorld().createBody(bodyDef).createFixture(fixtureDef);
	}
	
	public void renderMap() {
		Vector3 upperBound = game.getCamera().unproject(new Vector3(Gdx.graphics.getWidth()+1250, -Gdx.graphics.getHeight()-1250, 0));
		Vector3 lowerBound = game.getCamera().unproject(new Vector3(-Gdx.graphics.getWidth()-1250, Gdx.graphics.getHeight()+1250, 0));
		
		textureShader.begin();
		textureShader.setUniformMatrix("u_projTrans", game.getCamera().combined);
		textureShader.setUniformi("u_texture", 0);
		for(Center center : graph.getCenterList()) {
			if((center.location.x > lowerBound.x && center.location.x < upperBound.x) && (center.location.y > lowerBound.y && center.location.y < upperBound.y)) {
					((BiomeData) center.biome).texture.bind();
					center.mesh.render(textureShader, GL20.GL_TRIANGLES);
			}
		}
		textureShader.end();
	}
	
	public Texture getMinimap() {
		return minimap;
	}
	
	public boolean isWater(Vector2 point) {
		int color = new Color(pixmap.getPixel(Math.round(point.x), Constants.BOUNDS-Math.round(point.y))).toIntBits();
		boolean water = ((color == BiomeData.RIVER.color.toIntBits()) || (color == BiomeData.LAKE.color.toIntBits()) || (color == BiomeData.OCEAN.color.toIntBits()));	
		return water;
	}
	
	public VoronoiGraph getGraph() {
		return graph;
	}

    @Override
	public void dispose() {
        pixmap.dispose();
		minimap.dispose();
		
		graph.dispose();
		textureShader.dispose();
		for(Center center : graph.getCenterList()) {
			center.dispose();
		}
	}
}