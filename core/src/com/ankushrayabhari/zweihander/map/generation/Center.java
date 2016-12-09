package com.ankushrayabhari.zweihander.map.generation;

import java.util.ArrayList;

import com.ankushrayabhari.zweihander.map.as3delaunay.Point;
import com.badlogic.gdx.graphics.Mesh;

/**
 * Center.java
 *
 * @author Connor
 */
public class Center {

    public int index;
    public Point location;
    public ArrayList<Corner> corners = new ArrayList<Corner>();
    public ArrayList<Center> neighbors = new ArrayList<Center>();
    public ArrayList<Edge> borders = new ArrayList<Edge>();
    public boolean border, ocean, water, coast;
    public double elevation;
    public double moisture;
    public Enum biome;
    public double area;
    public Mesh mesh;
    public short[] indices;
    public float[] vertices;
    
    public Center() {
    }

    public Center(Point loc) {
        this.location = loc;
    }
    
    public void dispose() {
    	mesh.dispose();
    }
}