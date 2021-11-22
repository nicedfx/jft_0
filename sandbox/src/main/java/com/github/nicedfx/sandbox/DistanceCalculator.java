package com.github.nicedfx.sandbox;

public class DistanceCalculator {

    public static void main(String[] args) {
        System.out.println("The distance between the two points is:");
        System.out.println(Point.distance(new Point(1, 4), new Point(3, 6)));

        Point p1 = new Point(6, 12);
        System.out.println("The distance calculated by the method of the Point class:");
        System.out.println(p1.distance(new Point(7, 2)));
    }
}
