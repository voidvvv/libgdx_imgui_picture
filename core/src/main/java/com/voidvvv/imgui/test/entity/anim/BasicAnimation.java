package com.voidvvv.imgui.test.entity.anim;

import com.voidvvv.imgui.test.entity.frame.FrameData;

import java.util.List;

public class BasicAnimation {
    List<FrameData> frames;

    public FrameData getFrame(int i) {
        return frames.get(i);
    }

    public int getFrameCount() {
        return frames.size();
    }


//

}
