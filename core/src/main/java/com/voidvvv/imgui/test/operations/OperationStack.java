package com.voidvvv.imgui.test.operations;

import com.voidvvv.imgui.test.entity.BasePicture;
import com.voidvvv.imgui.test.entity.BaseSocket;

import java.util.ArrayDeque;
import java.util.Deque;

public class OperationStack {
    private AddSocketPool addSocketPool = new AddSocketPool(10);

    public  Deque<Operation> historyStack = new ArrayDeque<>();

    public  void addSocket (BasePicture picture, int baseSocket) {
        AddSocket operation = addSocketPool.obtain();
        operation.init(picture, baseSocket);
        historyStack.addLast(operation);
    }
}
