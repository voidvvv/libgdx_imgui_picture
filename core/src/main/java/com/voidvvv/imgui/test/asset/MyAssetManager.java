package com.voidvvv.imgui.test.asset;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Collection;
import java.util.Stack;

public class MyAssetManager {
    private Stack<Sprite> spriteStack = new Stack<>();
    private MyAssetManager () {}

    AssetManager assetManager = new AssetManager();

    public static final MyAssetManager instance;

    static {
        instance = new MyAssetManager();
    }

    public void pushImage(Texture texture) {
        Sprite sprite = new Sprite(texture);
        sprite.setPosition(50,50);
        sprite.setSize(50,50);
        spriteStack.push(sprite);

    }

    public Sprite popImage() {
        if (!spriteStack.isEmpty()) {
            return spriteStack.pop();
        }
        return null;
    }

    public Collection<Sprite> getSprites() {
        return spriteStack;
    }
}
