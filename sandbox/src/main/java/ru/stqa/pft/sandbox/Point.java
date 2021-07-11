package ru.stqa.pft.sandbox;

public class Point {
    public double x1, y1, x2, y2;

    /**
     * @return = Вывод расчета длины отрезка по теореме Пифагора.
     * Для расчета расмотрим прямоугольный треугольник, т.е. "p1 = x1 - x2, p2 = y1 - y2"
     */
    public static double distance(Point p1, Point p2) {
        return Math.sqrt((p2.x2-p1.x1)*(p2.x2-p1.x1)+(p2.y2-p1.y1)*(p2.y2-p1.y1));
    }

}
