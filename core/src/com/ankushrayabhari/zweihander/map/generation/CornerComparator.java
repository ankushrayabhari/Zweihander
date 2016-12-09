package com.ankushrayabhari.zweihander.map.generation;

import java.util.Comparator;

import com.ankushrayabhari.zweihander.map.as3delaunay.Point;

public class CornerComparator implements Comparator<Corner> {
	
	private Point center;
	
	@Override
	public int compare(Corner corner1, Corner corner2) {
		Point point1 = corner1.location;
		Point point2 = corner2.location;
		
		if (point1.x - center.x >= 0 && point2.x - center.x < 0)
	        return -1;
	    if (point1.x - center.x < 0 && point2.x - center.x >= 0)
	        return 1;
	    if (point1.x - center.x == 0 && point2.x - center.x == 0) {
	        if (point1.y - center.y >= 0 || point2.y - center.y >= 0)
	            return (point1.y > point2.y) ? -1 : 1;
	        return (point2.y > point1.y) ? -1 : 1;
	    }

	    // compute the cross product of vectors (center -> a) x (center -> b)
	    double det = (point1.x - center.x) * (point2.y - center.y) - (point2.x - center.x) * (point1.y - center.y);
	    if (det < 0)
	        return -1;
	    if (det > 0)
	        return 1;

	    // points a and b are on the same line from the center
	    // check which point is closer to the center
	    double d1 = (point1.x - center.x) * (point1.x - center.x) + (point1.y - center.y) * (point1.y - center.y);
	    double d2 = (point2.x - center.x) * (point2.x - center.x) + (point2.y - center.y) * (point2.y - center.y);
	    return (d1 > d2) ? -1 : 1;
	}
	
	public void setCenterPoint(Point point) {
		this.center = point;
	}
}

