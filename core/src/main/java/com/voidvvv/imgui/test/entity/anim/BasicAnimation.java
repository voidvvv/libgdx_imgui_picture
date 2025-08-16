package com.voidvvv.imgui.test.entity.anim;

import com.voidvvv.imgui.test.entity.frame.FrameData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class BasicAnimation {
    List<FrameData> frames = new ArrayList<>();

    public FrameData getFrame(int i) {
        return frames.get(i);
    }

    public int getFrameCount() {
        return frames.size();
    }

    public BasicAnimation(Collection<FrameData> frames) {
        this.frames.addAll(frames);
    }

    public BasicAnimation(FrameData...  frames) {
        this.frames.addAll(Stream.of(frames).toList());
    }



//

}
