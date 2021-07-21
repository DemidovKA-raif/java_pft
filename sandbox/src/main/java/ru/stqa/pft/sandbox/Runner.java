package ru.stqa.pft.sandbox;

public class Runner {
    public static void main(String[] args) {
        Point p1 = new Point(1, 5);
        Point p2 = new Point(10, 10);
        System.out.println("Расстояние между точками: " + p1.distance(p2));
    }
}