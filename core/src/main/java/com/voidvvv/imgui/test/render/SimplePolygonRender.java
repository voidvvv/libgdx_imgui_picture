package com.voidvvv.imgui.test.render;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;
import com.voidvvv.imgui.test.entity.BasePolygon;


public class SimplePolygonRender{
    ShaderProgram shaderProgram;
    float[] color = {1f,1f,1f,1f};

    public void init () {
        shaderProgram = new ShaderProgram(vertexShaderStr(),fragShaderStr());
    }

    private String fragShaderStr() {
        return """
            uniform vec4 u_color;


            void main()
            {
                gl_FragColor=u_color;
            }

            """;
    }

    private String vertexShaderStr() {
        return """
            attribute vec4 a_position;

            uniform mat4 u_projTrans;


            void main()
            {
                gl_Position =  u_projTrans * a_position;
            }

            """;
    }


    public void draw(BasePolygon basePolygon, Matrix4 transform) {
        configColor(basePolygon);
        // prepare shader
        shaderProgram.bind();
        shaderProgram.setUniformMatrix("u_projTrans",transform);
        shaderProgram.setUniform4fv("u_color",color,0,4);
        basePolygon.getMesh().render(shaderProgram,GL20.GL_TRIANGLES);
    }

    private void configColor(BasePolygon basePolygon) {
        Color color1 = basePolygon.getColor();
        color[0] = color1.r;
        color[1] = color1.g;
        color[2] = color1.b;
        color[3] = color1.a;
    }
}
