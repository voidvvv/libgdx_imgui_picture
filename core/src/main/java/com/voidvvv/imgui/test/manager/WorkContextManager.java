package com.voidvvv.imgui.test.manager;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WorkContextManager {
    private String absolutePath;
    private File contextFile;

    Map<String, TextureRegion> assetMap = new java.util.HashMap<>();


    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public File getContextFile() {
        return contextFile;
    }

    public void setContextFile(File contextFile) {
        this.contextFile = contextFile;
    }

    public Map<String, TextureRegion> getAssetMap() {
        return assetMap;
    }

    public void addAsset (String name, TextureRegion textureRegion) {
        if (name == null || name.isEmpty() || textureRegion == null) {
            throw new IllegalArgumentException("Name or TextureRegion is null or empty");
        }
        assetMap.put(name, textureRegion);

    }

    public void initAtlas (FileHandle fh, TextureAtlas textureAtlas) {
        if (textureAtlas == null) {
            throw new IllegalArgumentException("TextureAtlas is null");
        }
        contextFile = fh.file().getParentFile();
        absolutePath = contextFile.getAbsolutePath();
        Array<TextureAtlas.AtlasRegion> regions = textureAtlas.getRegions();
        for (TextureRegion region : regions) {
            addAsset(nameOfTexture(region) ,region);
        }

    }

    static public String nameOfTexture (TextureRegion textureRegion) {
        if (textureRegion == null) {
            return "";
        }
        if (textureRegion instanceof TextureAtlas.AtlasRegion ar) {
            return ar.name + "_" + ar.index;
        }
        return "TextureRegion (" + textureRegion.getTexture().getWidth() + "x" +
            textureRegion.getTexture().getHeight() + ")";

    }
}
