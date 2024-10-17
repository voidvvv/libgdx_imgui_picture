package com.voidvvv.imgui.test.test;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

public class MatrixTest {
    static  Matrix4 tmpMat = new Matrix4();
    public static void main(String[] args) {
        Vector3 v3 = new Vector3();
        Matrix4 m4 = new Matrix4();
        Vector3 tmp = new Vector3();
        m4.rotate(tmp.set(0,0,1),90f)
            .translate(tmp.set(1,1,0));
        v3.set(1,1,0).mul(m4);
        System.out.println(v3);
    }
}
