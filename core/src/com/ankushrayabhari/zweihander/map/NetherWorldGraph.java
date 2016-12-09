package com.ankushrayabhari.zweihander.map;

import java.util.Random;

import com.ankushrayabhari.zweihander.core.Constants;
import com.ankushrayabhari.zweihander.map.as3delaunay.Point;
import com.ankushrayabhari.zweihander.map.generation.VoronoiGraph;

public class NetherWorldGraph extends VoronoiGraph {
	private final int bumps;
	private final double startAngle, dipAngle, dipWidth, islandFactor;
	
	private NetherWorldGraph(int bounds, int sites, int lloydRelaxation, Random random) {
		super(bounds, sites, lloydRelaxation, random);
		bumps = random.nextInt(5) + 1;
        startAngle = random.nextDouble() * 2 * Math.PI;
        dipAngle = random.nextDouble() * 2 * Math.PI;
        dipWidth = random.nextDouble() * .5 + .2;
        islandFactor = 1.3;
	}
	
	//Radial implementation
	@Override
    protected boolean isWater(Point point) {
    	point = new Point(2 * (point.x / bounds.width - 0.5), 2 * (point.y / bounds.height - 0.5));

        double angle = Math.atan2(point.y, point.x);
        double length = 0.5 * (Math.max(Math.abs(point.x), Math.abs(point.y)) + point.length());

        double r1 = 0.5 + 0.40 * Math.sin(startAngle + bumps * angle + Math.cos((bumps + 3) * angle));
        double r2 = 0.7 - 0.20 * Math.sin(startAngle + bumps * angle - Math.sin((bumps + 2) * angle));
        if (Math.abs(angle - dipAngle) < dipWidth
                || Math.abs(angle - dipAngle + 2 * Math.PI) < dipWidth
                || Math.abs(angle - dipAngle - 2 * Math.PI) < dipWidth) {
            r1 = r2 = 0.2;
        }
        return !(length < r1 || (length > r1 * islandFactor && length < r2));
    }
	
	public static NetherWorldGraph createGraph(Random random) {
		NetherWorldGraph graph = new NetherWorldGraph(Constants.BOUNDS, Constants.SITES, Constants.LLOYD_RELAXATION, random);
		graph.generateGraph();
		return graph;
	}
}
