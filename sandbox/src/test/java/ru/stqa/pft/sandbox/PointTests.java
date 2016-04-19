package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 * Created by User on 17.04.2016.
 */
public class PointTests {

    @Test
    public void testDistanceFirst() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(1, 2);
        Assert.assertEquals(Point.distance(p1, p2), 1.0);
    }

    @Test
    public void testDistanceSecond() {
        Point p1 = new Point(2, 1);
        Point p2 = new Point(3, 1);
        Assert.assertEquals(Point.distance(p1, p2), 1.0);
    }
}
