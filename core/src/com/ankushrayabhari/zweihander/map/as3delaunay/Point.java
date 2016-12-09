package com.ankushrayabhari.zweihander.map.as3delaunay;

/**
 * Point.java
 *
 * @author Connor
 */
public class Point {

    public static double distance(Point point1, Point point2) {
        return Math.sqrt((point1.x - point2.x) * (point1.x - point2.x) + (point1.y - point2.y) * (point1.y - point2.y));
    }
    public double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return x + ", " + y;
    }

    public double l2() {
        return x * x + y * y;
    }

    public double length() {
        return Math.sqrt(x * x + y * y);
    }
    
    public Point subtract(Point point) {
    	return new Point(this.x-point.x, this.y-point.y);
    }
    
    public static Point interpolate(Point pointA, Point pointB, double fValue) {
    	return new Point( pointA.x + (pointB.x - pointA.x) * fValue, pointA.y + (pointB.y - pointA.y) * fValue);
    }
}
