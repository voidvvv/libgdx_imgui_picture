package com.voidvvv.imgui.test.test;

import java.util.stream.IntStream;

public class StringTest {

    public static void main(String[] args) {
        String s = Character.toString(128077) + Character.toString(127998);
        System.out.println(s);
        System.out.println(Character.toString(128077 + 127998));
        System.out.println(Character.toString(0x1F475));
        IntStream intStream = s.codePoints();
        intStream.forEach(System.out::println);
        System.out.println();
        System.out.println();
    }
}
