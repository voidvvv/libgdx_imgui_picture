package com.voidvvv.imgui.test.lwjgl3.renderer;

import com.badlogic.gdx.*;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Graphics;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.voidvvv.imgui.test.lwjgl3.renderer.frame.FrameUI;
import com.voidvvv.imgui.test.lwjgl3.renderer.ui.Demo;
import com.voidvvv.imgui.test.lwjgl3.renderer.ui.UIRender;
import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.flag.ImGuiConfigFlags;
import imgui.flag.ImGuiWindowFlags;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import imgui.type.ImBoolean;
import org.lwjgl.glfw.GLFW;

public class ImGuiRenderer extends Game {
    UIRender uiRender;
    private final ApplicationListener game;
    private static ImGuiImplGlfw imGuiGlfw;
    private static ImGuiImplGl3 imGuiGl3;
    static long windowHandle = -1;
    BitmapFont bitmapFont;
    SpriteBatch sb;

    public ImGuiRenderer(ApplicationListener game) {
        this.game = game;
    }

    public static void initImGui() {
        imGuiGlfw = new ImGuiImplGlfw();
        imGuiGl3 = new ImGuiImplGl3();
        windowHandle = ((Lwjgl3Graphics) Gdx.graphics).getWindow().getWindowHandle();
        ImGui.createContext();
        ImGuiIO io = ImGui.getIO();
        io.setIniFilename(null);
        io.getFonts().addFontFromFileTTF("C:\\Windows\\Fonts\\simsun.ttc",20.0f, ImGui.getIO().getFonts().getGlyphRangesChineseSimplifiedCommon());
        io.getFonts().build();
        io.addConfigFlags(ImGuiConfigFlags.ViewportsEnable); // 这个设置可以让imgui的panel放置在window外部

        imGuiGlfw.init(windowHandle, true);
        imGuiGl3.init("#version 130");
    }


    private static InputProcessor tmpProcessor;

    public static void startImGui() {
        if (tmpProcessor != null) { // Restore the input processor after ImGui caught all inputs, see #end()
            Gdx.input.setInputProcessor(tmpProcessor);
            tmpProcessor = null;
        }
        imGuiGl3.newFrame();
        imGuiGlfw.newFrame();
        ImGui.newFrame();
    }
    int txtId;

    @Override
    public void create() {
        this.game.create();
        initImGui();
        uiRender = new FrameUI();
        bitmapFont = new BitmapFont();
        sb = new SpriteBatch();

        txtId = new Texture("libgdx.png").getTextureObjectHandle();
    }

    float time = 0;

    @Override
    public void render() {
        this.game.render();
        startImGui();
        uiRender.render();
        endImGui();
    }


    public static void endImGui() {

        ImGui.render();
        imGuiGl3.renderDrawData(ImGui.getDrawData());

        // If ImGui wants to capture the input, disable libGDX's input processor
        if (ImGui.getIO().getWantCaptureKeyboard() || ImGui.getIO().getWantCaptureMouse()) {
            tmpProcessor = Gdx.input.getInputProcessor();
            Gdx.input.setInputProcessor(null);
        }
//
        if (ImGui.getIO().hasConfigFlags(ImGuiConfigFlags.ViewportsEnable)) {
            final long backupCurrentContext = GLFW.glfwGetCurrentContext();
            ImGui.updatePlatformWindows();
            ImGui.renderPlatformWindowsDefault();
            GLFW.glfwMakeContextCurrent(backupCurrentContext);
        }

//        GLFW.glfwSwapBuffers(windowHandle);
//        GLFW.glfwPollEvents();
    }

    @Override
    public void dispose() {
        super.dispose();
        this.game.dispose();
        disposeImGui();
    }

    public static void disposeImGui() {
        imGuiGl3.shutdown();
        imGuiGl3 = null;
        imGuiGlfw.shutdown();
        imGuiGlfw = null;
        ImGui.destroyContext();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        this.game.resize(width, height);
    }
}
