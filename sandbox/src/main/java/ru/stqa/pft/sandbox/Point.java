package ru.stqa.pft.sandbox;

public class Point {
    public double x1, y1, x2, y2;

    public Point(double x1,double y1,double x2,double y2){
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }
    /**
     * @return = Вывод расчета длины отрезка по теореме Пифагора.
     * Для расчета расмотрим прямоугольный треугольник, т.е. "p1 = x1 - x2, p2 = y1 - y2"
     */
    public double distance() {
        return Math.sqrt((this.x2-this.x1)*(this.x2-this.x1)+(this.y2-this.y1)*(this.y2-this.y1));
    }

}
