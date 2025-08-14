package com.voidvvv.imgui.test.manager;

public class SplitImageManager {
    int rows = 1;
    int columns = 1;

    boolean splitEnabled = false;

    public boolean isSplitEnabled() {
        return splitEnabled;
    }

    public void setSplitEnabled(boolean splitEnabled) {
        this.splitEnabled = splitEnabled;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public void reset () {
        this.rows = 1;
        this.columns = 1;
    }
}
