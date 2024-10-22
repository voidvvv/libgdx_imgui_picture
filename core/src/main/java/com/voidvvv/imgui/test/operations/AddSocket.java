package com.voidvvv.imgui.test.operations;

import com.badlogic.gdx.utils.Pool;
import com.voidvvv.imgui.test.entity.BasePicture;
import com.voidvvv.imgui.test.entity.BaseSocket;

import java.util.List;

public class AddSocket implements Operation, Pool.Poolable {
    private BasePicture picture;
    private int socketIndex;

    public BasePicture getPicture() {
        return picture;
    }

    public int getSocketIndex() {
        return socketIndex;
    }

    public void init(BasePicture basePicture, int baseSocket) {
        picture = basePicture;
        socketIndex = baseSocket;
    }

    @Override
    public void revert() {
        if (this.picture == null || socketIndex<0) {
            return;
        }
        List<BaseSocket> sockets = this.picture.getSockets();
        sockets.remove(this.socketIndex);
    }

    @Override
    public void reset() {
        this.picture = null;
        this.socketIndex = -1;
    }
}
