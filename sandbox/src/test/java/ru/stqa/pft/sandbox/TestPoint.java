package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestPoint {

    @Test
    public void testPoint1 () {
        Point a = new Point(1,2,3,4);
        Assert.assertEquals(a.distance(),2.8284271247461903 );
    }

    @Test
    public void testPoint2 () {
        Point a = new Point(1, 2, 3, 4);
        if (a.distance() != 0) {
            System.out.println("It is OKay!");
        } else {
            System.out.println("Отрицательное значение!");
        }
    }
}



