package com.voidvvv.imgui.test.entity;

public class BaseRect {
    private int width;
    private int height;

    private byte type;

    public BaseRect(int height, byte type, int width) {
        this.height = height;
        this.type = type;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getType() {
        return type;
    }

    public int getWidth() {
        return width;
    }
}
