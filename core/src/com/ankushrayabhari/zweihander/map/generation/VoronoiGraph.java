package com.ankushrayabhari.zweihander.map.generation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

import com.ankushrayabhari.zweihander.map.as3delaunay.LineSegment;
import com.ankushrayabhari.zweihander.map.as3delaunay.Point;
import com.ankushrayabhari.zweihander.map.as3delaunay.Rectangle;
import com.ankushrayabhari.zweihander.map.as3delaunay.Voronoi;
import com.badlogic.gdx.utils.Disposable;

public abstract class VoronoiGraph implements Disposable {
	private Voronoi voronoi;
	private ArrayList<Edge> edges;
	private ArrayList<Corner> corners;
	private ArrayList<Center> centers;
	protected final Rectangle bounds;
	protected Random random;

	protected VoronoiGraph(int bounds, int sites, int lloydRelaxation, Random random) {
		voronoi = new Voronoi(sites, bounds, bounds, random, null);
		edges = new ArrayList<Edge>();
		corners = new ArrayList<Corner>();
		centers = new ArrayList<Center>();	
		this.bounds = voronoi.get_plotBounds();
		this.random = random;
	}
	
	protected void generateGraph() {
		buildGraph();
        improveCorners();
        assignCornerElevations();
        assignOceanCoastAndLand();
        redistributeElevations(landCorners());
        assignPolygonElevations();
        calculateDownslopes();
        createRivers();
        assignCornerMoisture();
        redistributeMoisture(landCorners());
        assignPolygonMoisture();
        assignBiomes();
	}
	
	private void improveCorners() {
        Point[] points = new Point[corners.size()];
        
        for (Corner corner : corners) {
            if (corner.border) {
                points[corner.index] = corner.location;
            } else {
                double x = 0;
                double y = 0;
                for (Center center : corner.touches) {
                    x += center.location.x;
                    y += center.location.y;
                }
                points[corner.index] = new Point(x / corner.touches.size(), y / corner.touches.size());
            }
        }
        
        for(Corner corner : corners) {
            corner.location = points[corner.index];
        }

        for(Edge edge : edges) {
        	if(edge.v0 != null && edge.v1 != null) {
        		edge.setVornoi(edge.v0, edge.v1);
        	}
        }
    }
	
	//generate the graph structure and populate lists
	private void buildGraph() {
		final HashMap<Point, Center> pointCenterMap = new HashMap<Point, Center>();
        final ArrayList<Point> points = voronoi.siteCoords();
        
        for(Point point : points) {
        	Center center = new Center();
        	center.location = point;
        	center.index = centers.size();
            centers.add(center);
            pointCenterMap.put(point, center);
        }
       
        //bug fix
        for(Center center : centers) {
        	voronoi.region(center.location);
        }

        final ArrayList<com.ankushrayabhari.zweihander.map.as3delaunay.Edge> libraryEdges = voronoi.edges();
        final HashMap<Integer, Corner> pointCornerMap = new HashMap<Integer, Corner>();

        for (com.ankushrayabhari.zweihander.map.as3delaunay.Edge libraryEdge : libraryEdges) {
            final LineSegment vEdge = libraryEdge.voronoiEdge();
            final LineSegment dEdge = libraryEdge.delaunayLine();

            final Edge edge = new Edge();
            edge.index = edges.size();
            edges.add(edge);

            edge.v0 = makeCorner(pointCornerMap, vEdge.p0);
            edge.v1 = makeCorner(pointCornerMap, vEdge.p1);
            edge.d0 = pointCenterMap.get(dEdge.p0);
            edge.d1 = pointCenterMap.get(dEdge.p1);

            // Centers point to edges. Corners point to edges.
            if (edge.d0 != null) {
                edge.d0.borders.add(edge);
            }
            if (edge.d1 != null) {
                edge.d1.borders.add(edge);
            }
            if (edge.v0 != null) {
                edge.v0.protrudes.add(edge);
            }
            if (edge.v1 != null) {
                edge.v1.protrudes.add(edge);
            }

            // Centers point to centers.
            if (edge.d0 != null && edge.d1 != null) {
                addToCenterList(edge.d0.neighbors, edge.d1);
                addToCenterList(edge.d1.neighbors, edge.d0);
            }

            // Corners point to corners
            if (edge.v0 != null && edge.v1 != null) {
                addToCornerList(edge.v0.adjacent, edge.v1);
                addToCornerList(edge.v1.adjacent, edge.v0);
            }

            // Centers point to corners
            if (edge.d0 != null) {
                addToCornerList(edge.d0.corners, edge.v0);
                addToCornerList(edge.d0.corners, edge.v1);
            }
            if (edge.d1 != null) {
                addToCornerList(edge.d1.corners, edge.v0);
                addToCornerList(edge.d1.corners, edge.v1);
            }

            // Corners point to centers
            if (edge.v0 != null) {
                addToCenterList(edge.v0.touches, edge.d0);
                addToCenterList(edge.v0.touches, edge.d1);
            }
            if (edge.v1 != null) {
                addToCenterList(edge.v1.touches, edge.d0);
                addToCenterList(edge.v1.touches, edge.d1);
            }
        }
	}
	
	protected abstract boolean isWater(Point point);
	
	private void assignCornerElevations() {
        LinkedList<Corner> queue = new LinkedList<Corner>();
        for (Corner corner : corners) {
        	corner.water = isWater(corner.location);
            if (corner.border) {
                corner.elevation = 0;
                queue.add(corner);
            } else {
                corner.elevation = Double.MAX_VALUE;
            }
        }

        while (!queue.isEmpty()) {
            Corner corner = queue.pop();
            for (Corner adjacent : corner.adjacent) {
                double newElevation = 0.01 + corner.elevation;
                if (!corner.water && !adjacent.water) {
                    newElevation += 1;
                }
                if (newElevation < adjacent.elevation) {
                    adjacent.elevation = newElevation;
                    queue.add(adjacent);
                }
            }
        }
    }

	private void assignOceanCoastAndLand() {
        LinkedList<Center> queue = new LinkedList<Center>();
        final double waterThreshold = .3;
        for (final Center center : centers) {
            int numWater = 0;
            for (final Corner corner : center.corners) {
                if (corner.border) {
                    center.border = center.water = center.ocean = true;
                    queue.add(center);
                }
                if (corner.water) {
                    numWater++;
                }
            }
            center.water = center.ocean || ((double) numWater / center.corners.size() >= waterThreshold);
        }
        while (!queue.isEmpty()) {
            final Center center = queue.pop();
            for (final Center neighbor : center.neighbors) {
                if (neighbor.water && !neighbor.ocean) {
                    neighbor.ocean = true;
                    queue.add(neighbor);
                }
            }
        }
        for (Center center : centers) {
            boolean oceanNeighbor = false;
            boolean landNeighbor = false;
            for (Center neighbor : center.neighbors) {
                oceanNeighbor |= neighbor.ocean;
                landNeighbor |= !neighbor.water;
            }
            center.coast = oceanNeighbor && landNeighbor;
        }

        for (Corner corner : corners) {
            int numOcean = 0;
            int numLand = 0;
            for (Center center : corner.touches) {
                numOcean += center.ocean ? 1 : 0;
                numLand += !center.water ? 1 : 0;
            }
            corner.ocean = numOcean == corner.touches.size();
            corner.coast = numOcean > 0 && numLand > 0;
            corner.water = corner.border || ((numLand != corner.touches.size()) && !corner.coast);
        }
    }
	
	// Helper functions for the following for loop; ideally these would be inlined
    private void addToCornerList(ArrayList<Corner> list, Corner corner) {
        if (corner != null && !list.contains(corner)) {
            list.add(corner);
        }
    }

    private void addToCenterList(ArrayList<Center> list, Center center) {
        if (center != null && !list.contains(center)) {
            list.add(center);
        }
    }
    
    //ensures that each corner is represented by only one corner object
    private Corner makeCorner(HashMap<Integer, Corner> pointCornerMap, Point point) {
        if (point == null) {
            return null;
        }
        int index = (int) ((int) point.x + (int) (point.y) * bounds.width * 2);
        Corner corner = pointCornerMap.get(index);
        if (corner == null) {
            corner = new Corner();
            corner.location = point;
            corner.border = bounds.liesOnAxes(point);
            corner.index = corners.size();
            corners.add(corner);
            pointCornerMap.put(index, corner);
        }
        return corner;
    }

    private ArrayList<Corner> landCorners() {
        final ArrayList<Corner> list = new ArrayList<Corner>();
        for (Corner corner : corners) {
            if (!corner.ocean && !corner.coast) {
                list.add(corner);
            }
        }
        return list;
    }

    private void redistributeElevations(ArrayList<Corner> landCorners) {
        Collections.sort(landCorners, new Comparator<Corner>() {
            @Override
            public int compare(Corner corner1, Corner corner2) {
                if (corner1.elevation > corner2.elevation) {
                    return 1;
                } else if (corner1.elevation < corner2.elevation) {
                    return -1;
                }
                return 0;
            }
        });

        final double SCALE_FACTOR = 1.1;
        for (int i = 0; i < landCorners.size(); i++) {
            double y = (double) i / landCorners.size();
            double x = Math.sqrt(SCALE_FACTOR) - Math.sqrt(SCALE_FACTOR * (1 - y));
            x = Math.min(x, 1);
            landCorners.get(i).elevation = x;
        }

        for (Corner corner : corners) {
            if (corner.ocean || corner.coast) {
                corner.elevation = 0.0;
            }
        }
    }

    private void assignPolygonElevations() {
        for (Center center : centers) {
            double total = 0;
            for (Corner corner : center.corners) {
                total += corner.elevation;
            }
            total += 0.05 + (0.1 - 0.05)*random.nextDouble();
            center.elevation = total / center.corners.size();
        }
    }

    private void calculateDownslopes() {
        for (Corner corner : corners) {
            Corner down = corner;
            for (Corner adjacent : corner.adjacent) {
                if (adjacent.elevation <= down.elevation) {
                    down = adjacent;
                }
            }
            corner.downslope = down;
        }
    }

    private void createRivers() {
        for (int i = 0; i < bounds.width / 2; i++) {
            Corner corner = corners.get(random.nextInt(corners.size()));
            if (corner.ocean || corner.elevation < 0.3 || corner.elevation > 0.9) {
                continue;
            }
            
            while (!corner.coast) {
                if (corner == corner.downslope) {
                    break;
                }
                Edge edge = lookupEdgeFromCorner(corner, corner.downslope);
                if (!edge.v0.water || !edge.v1.water) {
                    edge.river++;
                    corner.river++;
                    corner.downslope.river++; 
                }
                corner = corner.downslope;
            }
        }
    }

    private Edge lookupEdgeFromCorner(Corner corner, Corner downslope) {
        for (Edge edge : corner.protrudes) {
            if (edge.v0 == downslope || edge.v1 == downslope) {
                return edge;
            }
        }
        return null;
    }

    private void assignCornerMoisture() {
        LinkedList<Corner> queue = new LinkedList<Corner>();
        for (Corner corner : corners) {
            if ((corner.water || corner.river > 0) && !corner.ocean) {
                corner.moisture = corner.river > 0 ? Math.min(3.0, (0.2 * corner.river)) : 1.0;
                queue.push(corner);
            } else {
                corner.moisture = 0.0;
            }
        }

        while (!queue.isEmpty()) {
            Corner corner = queue.pop();
            for (Corner adjacent : corner.adjacent) {
                double newMoisture = .9 * corner.moisture;
                if (newMoisture > adjacent.moisture) {
                    adjacent.moisture = newMoisture;
                    queue.add(adjacent);
                }
            }
        }

        // Salt water
        for (Corner corner : corners) {
            if (corner.ocean || corner.coast) {
                corner.moisture = 1.0;
            }
        }
    }

    private void redistributeMoisture(ArrayList<Corner> landCorners) {
        Collections.sort(landCorners, new Comparator<Corner>() {
            @Override
            public int compare(Corner corner1, Corner corner2) {
                if (corner1.moisture > corner2.moisture) {
                    return 1;
                } else if (corner1.moisture < corner2.moisture) {
                    return -1;
                }
                return 0;
            }
        });
        for (int i = 0; i < landCorners.size(); i++) {
            landCorners.get(i).moisture = (double) i / landCorners.size();
        }
    }

    private void assignPolygonMoisture() {
        for (Center center : centers) {
            double total = 0;
            for (Corner corner : center.corners) {
                total += corner.moisture;
            }
            center.moisture = total / center.corners.size();
        }
    }
    
    private BiomeData getBiome(Center center) {
    	if (center.ocean) {
            return BiomeData.OCEAN;
        } else if (center.water) {
            if (center.elevation < 0.3) {
                return BiomeData.MARSH;
            }
            if (center.elevation > 0.7) {
                return BiomeData.ICE;
            }
            return BiomeData.LAKE;
        } else if (center.coast) {
            return BiomeData.BEACH;
        } else if (center.elevation > 0.8) {
            if (center.moisture > 0.50) {
                return BiomeData.SNOW;
            } else if (center.moisture > 0.33) {
                return BiomeData.TUNDRA;
            } else if (center.moisture > 0.16) {
                return BiomeData.BARE;
            } else {
                return BiomeData.SCORCHED;
            }
        } else if (center.elevation > 0.6) {
            if (center.moisture > 0.66) {
                return BiomeData.TAIGA;
            } else if (center.moisture > 0.33) {
                return BiomeData.SHRUBLAND;
            } else {
                return BiomeData.TEMPERATE_DESERT;
            }
        } else if (center.elevation > 0.3) {
            if (center.moisture > 0.83) {
                return BiomeData.TEMPERATE_RAIN_FOREST;
            } else if (center.moisture > 0.50) {
                return BiomeData.TEMPERATE_DECIDUOUS_FOREST;
            } else if (center.moisture > 0.16) {
                return BiomeData.GRASSLAND;
            } else {
                return BiomeData.TEMPERATE_DESERT;
            }
        } else {
            if (center.moisture > 0.66) {
                return BiomeData.TROPICAL_RAIN_FOREST;
            } else if (center.moisture > 0.33) {
                return BiomeData.TROPICAL_SEASONAL_FOREST;
            } else if (center.moisture > 0.16) {
                return BiomeData.GRASSLAND;
            } else {
                return BiomeData.SUBTROPICAL_DESERT;
            }
        }
    }

    private void assignBiomes() {
        for (Center center : centers) {
            center.biome = getBiome(center);
        }
    }
    
    public ArrayList<Center> getCenterList() {
    	return centers;
    }
    
    public ArrayList<Edge> getEdgeList() {
    	return edges;
    }
    
    public Voronoi getVoronoi() {
    	return voronoi;
    }
    
    public void dispose() {
    	for(Center center : centers) {
    		center.dispose();
    	}
    }
}
