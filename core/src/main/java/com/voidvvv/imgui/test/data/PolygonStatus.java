package com.voidvvv.imgui.test.data;

public class PolygonStatus {
    public static final int NO_OP = 0;
    public static final int POLYGON_ENTER_ADDING_STATUS = 1;

    public static final int POLYGON_EDITING_STATUS = 2;

    public int status = NO_OP;

    public void enterAddPolygonStatus () {
        status = POLYGON_ENTER_ADDING_STATUS;
    }

    public void editingPolygon () {
        status = POLYGON_EDITING_STATUS;
    }

    public void reset () {
        status = NO_OP;
    }
}
