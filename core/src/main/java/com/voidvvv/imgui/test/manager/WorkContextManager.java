package com.voidvvv.imgui.test.manager;

import java.io.File;

public class WorkContextManager {
    private String absolutePath;
    private File contextFile;

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
}
