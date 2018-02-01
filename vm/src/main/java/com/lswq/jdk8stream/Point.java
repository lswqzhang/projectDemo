package com.lswq.jdk8stream;

import com.google.common.collect.Lists;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class Point {
    private Double x;
    private Double y;

    public Point() {
        
    }

    public Point(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Point{");
        sb.append("x=").append(x);
        sb.append(", y=").append(y);
        sb.append('}');
        return sb.toString();
    }

    public static Function<Point, Double> keyXExtractor = point -> point.getX();
    public static Function<Point, Double> keyYExtractor = point -> point.getY();
    public static Comparator<Double> keyComparator;

    static {
        keyComparator = Comparator.naturalOrder();
    }

    public static Comparator<Point> comparatorX = (p1, p2) -> keyComparator.compare(keyXExtractor.apply(p1), keyXExtractor.apply(p2));
    public static Comparator<Point> comparatorY = (p1, p2) -> keyComparator.compare(keyYExtractor.apply(p1), keyYExtractor.apply(p2));

    public static void main(String[] args) {
        final Point p1 = new Point(Double.valueOf(4), Double.valueOf(2));
        final Point p2 = new Point(Double.valueOf(3), Double.valueOf(3));
        List<Point> initList = Lists.newArrayList(p1, p2);
        initList.stream().sorted(comparatorX).forEach(System.err::println);
        initList.stream().sorted(comparatorY).forEach(System.err::println);
    }
}
