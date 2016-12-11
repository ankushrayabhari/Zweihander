package com.ankushrayabhari.zweihander.map;

import com.ankushrayabhari.zweihander.core.Constants;
import com.ankushrayabhari.zweihander.map.as3delaunay.Point;
import com.ankushrayabhari.zweihander.map.generation.PerlinNoiseGenerator;
import com.ankushrayabhari.zweihander.map.generation.VoronoiGraph;

import java.util.Random;

public class OverWorldGraph extends VoronoiGraph {
	private PerlinNoiseGenerator generator;
	
	private OverWorldGraph(int bounds, int sites, int lloydRelaxation, Random random) {
		super(bounds, sites, lloydRelaxation, random);
		generator = new PerlinNoiseGenerator();
	}
	
	//Perlin Noise Implementation
	@Override
	protected boolean isWater(Point point) {
    	double x = 2*point.x/bounds.width - 1;
    	double y = 2*point.y/bounds.height - 1;
    	float perlin = generator.noise2((float) (point.x+1)/128, (float) (point.y+1)/128);
    	double value = (15+35*(x*x+y*y))/100;
    	return perlin > value;
    }
	
	public static OverWorldGraph createGraph(Random random) {
		OverWorldGraph graph = new OverWorldGraph(Constants.BOUNDS+1, Constants.SITES, Constants.LLOYD_RELAXATION, random);
		graph.generateGraph();
		return graph;
	}
}
