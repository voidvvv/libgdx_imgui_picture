package com.voidvvv.imgui.test.entity;

public class BaseSocket {

    private float xFactor;
    private float yFactor;
    private float x;
    private float y;
    private final String name;

    public BaseSocket(String name, float xf, float yf, float x, float y) {
        this.name = name;
        this.xFactor = xf;
        this.yFactor = yf;
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public BaseSocket(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public float getxFactor() {
        return xFactor;
    }

    public float getyFactor() {
        return yFactor;
    }
}
