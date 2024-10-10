package com.voidvvv.imgui.test.entity;

public class BaseSocket {

    private float xFactor;
    private float yFactor;
    private final String name;

    public BaseSocket(String name, float x, float y) {
        this.name = name;
        this.xFactor = x;
        this.yFactor = y;
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
