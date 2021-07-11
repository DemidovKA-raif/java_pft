package ru.stqa.pft.sandbox;

import static ru.stqa.pft.sandbox.Point.distance;

public class Runner {
    public static void main(String[] args) {
        Point p1 = new Point();
        Point p2 = new Point();

        p1.x1 = 4;
        p1.y1 = 4;
        p2.x2 = 6;
        p2.y2 = 6;

        System.out.println("Расстояние между точками: " + distance(p1,p2));
    }
}
