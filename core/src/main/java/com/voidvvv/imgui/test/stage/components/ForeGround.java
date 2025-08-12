package com.voidvvv.imgui.test.stage.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.voidvvv.imgui.test.MainGame;
import com.voidvvv.imgui.test.data.MainScreenGroundData;

public class ForeGround  extends Actor {
    private int blendSrcFunc = GL20.GL_SRC_ALPHA;
    private int blendDstFunc = GL20.GL_ONE_MINUS_SRC_ALPHA;
    private int blendSrcFuncAlpha = GL20.GL_SRC_ALPHA;
    private int blendDstFuncAlpha = GL20.GL_ONE_MINUS_SRC_ALPHA;
    private Batch currentBatch;
    MainGame instance = MainGame.getInstance();
    MainScreenGroundData data;
    BitmapFont font;

    Color originColor = new Color();
    public ForeGround(MainScreenGroundData data) {
        this.data = data;


        originColor.set(Color.BLUE);
        originColor.a = 0.7f;
    }

    @Override
    public void act(float delta) {
        font = MainGame.getInstance().getAssetManager().get(MainGame.BASIC_FONT_NAME,BitmapFont.class);
//        currentBatch = MainGame.getInstance().getDrawManager().getBaseBatch();

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        currentBatch = batch;
        renderText(batch);
        renderOrigins(batch);
    }


    private void renderOrigins(Batch batch) {
        batch.end();
        Gdx.gl.glEnable(GL20.GL_BLEND);
        if (blendSrcFunc != -1) Gdx.gl.glBlendFuncSeparate(blendSrcFunc, blendDstFunc, blendSrcFuncAlpha, blendDstFuncAlpha);

        ShapeRenderer shapeRenderer = instance.getDrawManager().getShapeRenderer();
        OrthographicCamera mainCamera = instance.getCameraManager().getMainCamera();

        shapeRenderer.setProjectionMatrix(mainCamera.combined);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(originColor);
        shapeRenderer.circle(this.data.originPoint.x, this.data.originPoint.y, 20f);
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
        batch.begin();
//        shapeRenderer.setColor(tmpColor);
    }


    private void renderText(Batch batch) {
        OrthographicCamera mainCamera = instance.getCameraManager().getMainCamera();
//        currentBatch.begin();
//        currentBatch.setProjectionMatrix(mainCamera.combined);
        float s = this.data.xs;
        while (s <= this.data.rightUp.x){
            coordinateText(s,this.data.rightUp.y,s+"");
            s+=this.data.unit;
        }
        s = this.data.ys;
        while (s <= this.data.rightUp.y){
//            drawHorizonLine(s,shapeRenderer);
            coordinateText(this.data.leftDown.x,s,s+"");
            s+=this.data.unit;
        }
//        currentBatch.end();
    }

    private void coordinateText(float s, float y,String text) {
        font.draw(currentBatch,text,s,y);

    }
}
