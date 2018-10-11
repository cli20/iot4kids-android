package com.adalaachref.iot4kids.model;

/**
 * Created by dell on 12/11/2017.
 */

public class Position {

    private int xMax;
    private int yMax;
    private int xMin;
    private int yMin;
    private int x;
    private int y;

    public Position(int xMax, int yMax, int xMin, int yMin, int x, int y) {
        this.xMax = xMax;
        this.yMax = yMax;
        this.xMin = xMin;
        this.yMin = yMin;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getxMax() {
        return xMax;
    }

    public void setxMax(int xMax) {
        this.xMax = xMax;
    }

    public int getyMax() {
        return yMax;
    }

    public void setyMax(int yMax) {
        this.yMax = yMax;
    }

    public int getxMin() {
        return xMin;
    }

    public void setxMin(int xMin) {
        this.xMin = xMin;
    }

    public int getyMin() {
        return yMin;
    }

    public void setyMin(int yMin) {
        this.yMin = yMin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (xMax != position.xMax) return false;
        if (yMax != position.yMax) return false;
        if (xMin != position.xMin) return false;
        return yMin == position.yMin;

    }

    @Override
    public int hashCode() {
        int result = xMax;
        result = 31 * result + yMax;
        result = 31 * result + xMin;
        result = 31 * result + yMin;
        return result;
    }

    @Override
    public String toString() {
        return "Position{" +
                "xMax=" + xMax +
                ", yMax=" + yMax +
                ", xMin=" + xMin +
                ", yMin=" + yMin +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
