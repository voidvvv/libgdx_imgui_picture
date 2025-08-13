package com.voidvvv.imgui.test.manager;

import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;
import java.util.List;

public class FrameAttackCheckColorManager {
    List<Color> attackFrameColors = new ArrayList<>();

    public FrameAttackCheckColorManager() {

        newColorUntil(15);
    }

    public List<Color> getAttackFrameColors() {
        return attackFrameColors;
    }


    public void newColorUntil (int n) {
        n = Math.min(n, 15);
        while (attackFrameColors.size() < n) {
            newColor();
        }
    }

    public int size() {
        return attackFrameColors.size();
    }
    float step = 0.2f;
    private void newColor() {
        if (attackFrameColors.isEmpty()) {
            attackFrameColors.add(Color.BLUE.cpy());
        } else {
            Color lastColor = attackFrameColors.get(attackFrameColors.size() - 1);
            Color newColor = new Color(lastColor);
            newColor.r += step;
            if (newColor.r > 1f) {
                newColor.r = 0f;
                newColor.g += step;
                if (newColor.g > 1f) {
                    newColor.g = 0f;
                    newColor.b += step;
                    if (newColor.b > 1f) {
                        newColor.b = 0f;
                    }
                }
            }
            attackFrameColors.add(newColor);
        }
    }



    public Color getColor(int i) {
        return attackFrameColors.get(i % attackFrameColors.size());
    }
}
