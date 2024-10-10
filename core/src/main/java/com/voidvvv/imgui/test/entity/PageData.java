package com.voidvvv.imgui.test.entity;

public class PageData {
    public int current;

    public int size;

    public PageData() {
        current = 1;
        size = 10;
    }

    public int start () {
        return (current - 1 )* size;
    }

    public int end () {
        return (current - 1 )* size + size;
    }
}
