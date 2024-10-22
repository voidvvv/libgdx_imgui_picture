package com.voidvvv.imgui.test.operations;

import com.badlogic.gdx.utils.Pool;

public class AddSocketPool extends Pool<AddSocket> {
    public AddSocketPool(int initialCapacity, int max) {
        super(initialCapacity, max);
    }

    public AddSocketPool(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    protected AddSocket newObject() {
        return new AddSocket();
    }
}
