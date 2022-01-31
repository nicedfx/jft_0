package com.github.nicedfx.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointsTests {

    @Test
    public void testPointFirst() {
        Point p1 = new Point(6, 12);
        Point p2 = new Point(7, 2);

        Assert.assertEquals(p1.distance(p2), 10.04987562112089);
    }

    @Test
    public void testPointSecond() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(1, 1);

        Assert.assertEquals(p1.distance(p2), 1.0);
    }

    @Test
    public void testPointThird() {
        Point p1 = new Point(1, 100);
        Point p2 = new Point(100, 1);

        Assert.assertEquals(p1.distance(p2), 140.0071426749364);
    }
}
