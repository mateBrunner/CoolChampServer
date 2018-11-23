package com.codecool.coolchampserver.httpmodel;

public class Points {

    private Integer point1;
    private Integer point2;

    public Points(Integer point1, Integer point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    public Points() {}

    public Integer getPoint1() {
        return point1;
    }

    public Integer getPoint2() {
        return point2;
    }
}
