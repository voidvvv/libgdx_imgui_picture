package com.voidvvv.imgui.test.lwjgl3.renderer.ui;

import com.voidvvv.imgui.test.entity.BasePicture;
import com.voidvvv.imgui.test.entity.BasePolygon;
import imgui.ImGui;
import imgui.type.ImBoolean;
import imgui.type.ImString;

public class PolygonDetailPenal implements UIRender{
    BasePicture picture;
    int i;

    String name;
    ImString changedName = new ImString();
    boolean nameChange = false;

    ImBoolean show = new ImBoolean(false);

    public void init (BasePicture basePicture, int i) {
        this.picture = basePicture;
        this.i = i;
        show.set(true);
    }

    @Override
    public void render() {
        if (!show.get() || this.picture == null || this.picture.getBasePolygons() == null
        || i < 0 || i >= this.picture.getBasePolygons().size()) {
            return;
        }
        BasePolygon bp = this.picture.getBasePolygons().get(i);
        ImGui.begin("PolygonDetailPenal",show);
        // name
        {
            name = bp.getName();
            if (!nameChange) {
                ImGui.text(name);
                ImGui.sameLine();
                if (ImGui.button("changeName")) {
                    changeName();
                }
            } else {
                ImGui.inputText("name",changedName);
                ImGui.sameLine();
                if (ImGui.button("confirm")) {
                    changeName();
                }
            }
        }
        ImGui.separator();
        // points
        {
            if (!bp.complete()) {
                ImGui.text("incomplete");
            } else {
                ImGui.text("complete");
            }
        }
        ImGui.end();
        postRender();
    }

    private void changeName() {
        nameChange = !nameChange;
        if (nameChange) {
            // if now is the change status
            this.picture.getBasePolygons().get(i).setName(name);
        } else {
            // if now is not the change status
            this.changedName.set(name);
        }
    }

    private void postRender() {

    }
}
