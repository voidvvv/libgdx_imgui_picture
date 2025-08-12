package com.voidvvv.imgui.test.manager;

import java.util.Deque;
import java.util.LinkedList;

public class DebugManager {

    public final Deque<String> debugMessages = new LinkedList<>();

    int capacity = 100;

    int size = 0;



    public void pushMsg (String msg) {
//        System.out.println("DebugManager pushMsg: " + msg);
        debugMessages.offerLast(msg);
        while ((++size) >= capacity) {
            debugMessages.pollFirst();
        }
    }
}
