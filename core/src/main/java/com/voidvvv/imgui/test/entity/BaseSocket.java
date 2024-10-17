package com.voidvvv.imgui.test.entity;

public class BaseSocket {

    public float xStart;
    public float yStart;
    public float x;
    public float y;
    private String name;

    public BaseSocket(String name, float x, float y) {
        this.name = name;
        this.xStart = x;
        this.yStart = y;
        this.x = x;
        this.y = y;
    }

    public void setName(String name) {
        this.name = name;
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
}
