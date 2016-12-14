package com.ankushrayabhari.zweihander.screens;

import com.ankushrayabhari.zweihander.Zweihander;
import com.ankushrayabhari.zweihander.core.CollisionListener;
import com.ankushrayabhari.zweihander.core.Constants;
import com.ankushrayabhari.zweihander.core.KeyboardController;
import com.ankushrayabhari.zweihander.core.hud.HeadsUpDisplay;
import com.ankushrayabhari.zweihander.entities.Entity;
import com.ankushrayabhari.zweihander.entities.EntityComparator;
import com.ankushrayabhari.zweihander.entities.physical.Player;
import com.ankushrayabhari.zweihander.entities.physical.enemies.Enemy;
import com.ankushrayabhari.zweihander.map.Map;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import java.util.Iterator;
import java.util.LinkedList;

public class GameScreen implements Screen {
	private Zweihander zweihander; // move to superclass later (probably)
    private KeyboardController inputController;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private LinkedList<Entity> entityList, addList;
    private EntityComparator comparator;
	private Player player;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private Map map;
    private HeadsUpDisplay hud;
    private ShapeRenderer shapeRenderer;
    private Music music;

    public GameScreen(Zweihander zweihander) {
		this.zweihander = zweihander;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera(
                Gdx.graphics.getWidth()/20,
                Gdx.graphics.getHeight()/20
        );

        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        if(Zweihander.DEBUG) debugRenderer = new Box2DDebugRenderer();

        entityList = new LinkedList<Entity>();
        addList = new LinkedList<Entity>();

        comparator = new EntityComparator();

        world = new World(new Vector2(0,0), false);
        world.setContactListener(new CollisionListener());

        player = (Player) addEntity(new Player(this));

        map = new Map(this);
        hud = new HeadsUpDisplay(this);

        inputController = new KeyboardController();
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(hud);
        inputMultiplexer.addProcessor(inputController);
        Gdx.input.setInputProcessor(inputMultiplexer);

        this.addEntity(new Enemy(this));

        music = Gdx.audio.newMusic(Gdx.files.internal("music/sleuth.ogg"));
        music.setLooping(true);
        music.setVolume(0.5f);
        //music.play();
    }

	@Override
    public void render(float delta) {
		Gdx.gl20.glClearColor(1, 1, 1, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
        //Update all entities
        world.step(Constants.TIME_STEP, Constants.VELOCITY_ITERATIONS, Constants.POSITION_ITERATIONS);

        updateEntities(delta);
        
        camera.position.set(player.getBody().getPosition(), 0);
        camera.update();

        //Draw Map
        map.renderMap();

        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);
        
        batch.begin();
        drawEntities();
        batch.end();
        
        //if(Zweihander.DEBUG) debugRenderer.render(world, camera.combined);
        
        //Render HUD
        hud.draw();
	}

    private void updateEntities(float delta) {
        for(Entity entity : addList) {
            entityList.add(entity);
        }
        addList.clear();

        entityList.sort(comparator);
        Iterator<Entity> iterator = entityList.iterator();
        while(iterator.hasNext()) {
        	Entity entity = iterator.next();
            if(entity.isDead()) {
                entity.onDeath();
                iterator.remove();
            }
            else {
                entity.update(delta);
            }
        }
    }

    private void drawEntities() {
    	for(Entity entity : entityList) {
    		Vector3 upperBound = camera.unproject(new Vector3(Gdx.graphics.getWidth()+200, -Gdx.graphics.getHeight()-200, 0));
    		Vector3 lowerBound = camera.unproject(new Vector3(-Gdx.graphics.getWidth()-200, Gdx.graphics.getHeight()+200, 0));
    		Vector2 position = entity.getPosition();
    		if ((position.x > lowerBound.x && position.x < upperBound.x) && (position.y > lowerBound.y && position.y < upperBound.y)) {
    			entity.draw(batch);
    		}
    	}
    }

    public Entity addEntity(Entity entity) {
        addList.add(entity);
        return entity;
    }

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {
		map.dispose();
        world.dispose();
        debugRenderer.dispose();
        batch.dispose();
        hud.dispose();
        music.dispose();
	}

    public KeyboardController getInputController() { return inputController; }

    public OrthographicCamera getCamera() { return camera; }

    public World getWorld() { return world; }

    public Player getPlayer() { return player; }    
    
    public Map getMap() { return map; }

    public HeadsUpDisplay getHud() { return hud; }
}
