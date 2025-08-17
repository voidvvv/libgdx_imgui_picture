package com.voidvvv.imgui.test.lwjgl3.renderer.frame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.voidvvv.imgui.test.MainGame;
import com.voidvvv.imgui.test.entity.anim.BasicAnimation;
import com.voidvvv.imgui.test.entity.frame.FrameData;
import com.voidvvv.imgui.test.lwjgl3.renderer.ui.UIRender;
import com.voidvvv.imgui.test.manager.AnimationManager;
import com.voidvvv.imgui.test.manager.FrameAttackCheckColorManager;
import imgui.ImGui;
import imgui.flag.ImGuiColorEditFlags;
import imgui.flag.ImGuiKey;
import imgui.flag.ImGuiWindowFlags;
import imgui.gl3.ImGuiImplGl3;
import imgui.type.ImBoolean;
import imgui.type.ImFloat;
import imgui.type.ImString;

import java.util.List;

public class FileOperaPanel implements UIRender {
    ImBoolean open = new ImBoolean(true);

    ImString tip = new ImString(TIP_DEFAULT);
    final static String TIP_DEFAULT = "No Frame Data Loaded";
    final static String TIP_ERROR = "Path illegal, please input a valid path";

    ImString filePath = new ImString();

    @Override
    public void render() {
        if (open.get()) {
            ImGui.begin("FileOperaPanel", open);
            loadFilePenal();
            ImGui.separator();
            animationList();

            ImGui.end();
        }

        update();
    }

    private void animationList() {
        AnimationManager animationManager = MainGame.getInstance().getAnimationManager();
        List<BasicAnimation> animations = animationManager.getAnimations();
        if (animations.isEmpty()) {
            ImGui.text(tip.get());
            return;
        }
        for (BasicAnimation anim : animations) {
            animationSwitchButton(anim);
        }
    }

    private void animationSwitchButton(BasicAnimation anim) {
        if (ImGui.button(anim.getName())) {
            MainGame.getInstance().getAnimationManager().trySwitchAnim(anim);
        }
        ImGui.sameLine();
        if (ImGui.button("delete")) {
            MainGame.getInstance().getAnimationManager().deleteAnimation(anim);
        }
    }

    private void loadFilePenal() {
        ImGui.inputText("file", filePath);
        ImGui.sameLine();
        if (ImGui.button("openFileSys")) {
            SystemFilePanel.OPEN = true;
        }
        if (ImGui.button("load")) {
            loadFile = true;
        }
    }


    boolean loadFile = false;

    void update() {
        openFlag();
        loadFileFromPath();
//        updateRect
    }



    private void openFlag() {
        if (ImGui.isKeyDown(ImGuiKey.LeftCtrl) && ImGui.isKeyPressed(ImGuiKey.A)) {
            open.set(true);
        }
    }

    public void tryLoad(String file) {
        filePath.set(file);
        loadFile = true;
    }

    private void loadFileFromPath() {
        if (loadFile) {
            String s = filePath.get();
            if (s == null || s.isEmpty()) {
                System.out.println(System.getenv().keySet());
                String imguiAtlasHome = System.getenv("imgui_atlas_home");
                System.out.println("imguiAtlasHome = " + imguiAtlasHome);
                s = imguiAtlasHome;
                filePath.set(s);
            }

            try {
                FileHandle fh = Gdx.files.absolute(s);

                if (isPic(fh)) {
                    AssetManager assetManager = MainGame.getInstance().getAbsoluteAssetManager();
                    assetManager.load(s, Texture.class);
                    assetManager.finishLoading();
                    MainGame.getInstance().getAnimationManager().loadAnimByPic(assetManager.get(s, Texture.class));
                }

                if (isAtlas(fh)) {
                    AssetManager assetManager = MainGame.getInstance().getAbsoluteAssetManager();
                    assetManager.load(s, TextureAtlas.class);
                    assetManager.finishLoading();
                    MainGame.getInstance().getAnimationManager().loadAnimByAtlas(fh, assetManager.get(s, TextureAtlas.class));

                }

            } catch (Exception e) {
                tip.set(TIP_ERROR + "\n" + e.getMessage());
            }
            loadFile = false;
        }
    }

    private boolean isAtlas(FileHandle fh) {
        return fh != null && fh.extension().equalsIgnoreCase("atlas");
    }

    private boolean isPic(FileHandle fh) {
        return fh != null && (fh.extension().equalsIgnoreCase("png") || fh.extension().equalsIgnoreCase("jpg")
                || fh.extension().equalsIgnoreCase("jpeg") || fh.extension().equalsIgnoreCase("gif"));
    }
}
