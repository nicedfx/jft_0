package com.github.nicedfx.sandbox;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Point {
    private final double x;
    private final double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static double distance(Point p1, Point p2) {
        return sqrt(pow((p2.x - p1.x), 2) + pow((p2.y - p1.y), 2));
    }

    public double distance(Point p2) {
        return sqrt(pow((p2.x - this.x), 2) + pow((p2.y - this.y), 2));
    }
}
