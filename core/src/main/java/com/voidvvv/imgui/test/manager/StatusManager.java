package com.voidvvv.imgui.test.manager;

import com.voidvvv.imgui.test.data.PolygonStatus;

public class StatusManager {
    private PolygonStatus polygonStatus;

    public PolygonStatus getPolygonStatus() {
        return polygonStatus;
    }

    public void setPolygonStatus(PolygonStatus polygonStatus) {
        this.polygonStatus = polygonStatus;
    }

    public StatusManager() {
        polygonStatus = new PolygonStatus();
    }
}
