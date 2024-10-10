package com.voidvvv.imgui.test.lwjgl3.renderer;

import imgui.ImGui;
import imgui.app.Application;
import imgui.app.Configuration;
import imgui.flag.ImGuiWindowFlags;
import imgui.type.ImBoolean;
import imgui.type.ImInt;
import imgui.type.ImString;

public class Main extends Application {
    @Override
    protected void configure(Configuration config) {
        config.setTitle("Dear ImGui is Awesome!");
    }

    @Override
    public void process() {
        ImGui.text("Hello, World!");
        if (ImGui.button("asdasdasd")) {
            System.out.println("haha");
        }
    }

    public static void main(String[] args) {
        launch(new Main());
    }
}
