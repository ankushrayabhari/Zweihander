package com.ankushrayabhari.zweihander.map.generation;

import java.util.ArrayList;

import com.ankushrayabhari.zweihander.map.as3delaunay.Point;

/**
 * Corner.java
 *
 * @author Connor
 */
public class Corner {

    public ArrayList<Center> touches = new ArrayList<Center>(); 
    public ArrayList<Corner> adjacent = new ArrayList<Corner>(); 
    public ArrayList<Edge> protrudes = new ArrayList<Edge>();
    public Point location;
    public int index;
    public boolean border;
    public double elevation;
    public boolean water, ocean, coast;
    public Corner downslope;
    public int river;
    public double moisture;
}