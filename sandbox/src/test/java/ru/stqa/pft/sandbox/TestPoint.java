package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestPoint {
    Point point = new Point(10, 10);


    @Test
    public void testPoint1 () {
        Point a = new Point(1,5);
        Assert.assertEquals(point.distance(a),10.295630140987);
    }

    @Test
    public void testPoint2 () {
        Point a = new Point( 3, 4);
        if (point.distance(a) != 0) {
            System.out.println("It is OKay!");
        } else {
            System.out.println("Отрицательное значение!");
        }
    }
}



