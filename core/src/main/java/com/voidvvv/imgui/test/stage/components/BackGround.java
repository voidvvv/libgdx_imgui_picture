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

public class BackGround extends Actor {
    BitmapFont font;
    private int blendSrcFunc = GL20.GL_SRC_ALPHA;
    private int blendDstFunc = GL20.GL_ONE_MINUS_SRC_ALPHA;
    private int blendSrcFuncAlpha = GL20.GL_SRC_ALPHA;
    private int blendDstFuncAlpha = GL20.GL_ONE_MINUS_SRC_ALPHA;
    MainScreenGroundData data;


    MainGame instance = MainGame.getInstance();



    Color lineColor = new Color();
    Color originColor = new Color();

    Color tmpColor = new Color();

    private SpriteBatch currentBatch;

    public BackGround(MainScreenGroundData data) {
        this.data = data;
        lineColor.set(Color.RED);
        lineColor.a = 0.5f;

        originColor.set(Color.BLUE);
        originColor.a = 0.7f;
    }


    public void act(float delta) {
        super.act(delta);
//        font = MainGame.getInstance().getAssetManager().get(MainGame.BASIC_FONT_NAME,BitmapFont.class);
        currentBatch = MainGame.getInstance().getDrawManager().getBaseBatch();
        resetOrigins();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.end();
        this.draw();
        batch.begin();
    }

    public void draw() {
        renderShape();
    }

    private void renderShape() {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        if (blendSrcFunc != -1) Gdx.gl.glBlendFuncSeparate(blendSrcFunc, blendDstFunc, blendSrcFuncAlpha, blendDstFuncAlpha);

        ShapeRenderer shapeRenderer = instance.getDrawManager().getShapeRenderer();
        OrthographicCamera mainCamera = instance.getCameraManager().getMainCamera();
        OrthographicCamera screenCamera = instance.getCameraManager().getScreenCamera();

        shapeRenderer.setColor(lineColor);
        shapeRenderer.setProjectionMatrix(mainCamera.combined);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        // origin pot

        // x positive
//        shapeRenderer.rectLine(0f, this.data.originPoint.y, Gdx.graphics.getWidth(), this.data.originPoint.y, 1f);
//
//        // y positive
//        shapeRenderer.rectLine(this.data.originPoint.x, 0f, this.data.originPoint.x, Gdx.graphics.getHeight(), 1f);
        // render position point
        // x positive


        // draw lines
        float s = this.data.xs;
        while (s <= this.data.rightUp.x + this.data.unit){
            drawVerticalLine(s,shapeRenderer);
            s+=this.data.unit;
        }
        s = this.data.ys;
        while (s <= this.data.rightUp.y  + this.data.unit){
            drawHorizonLine(s,shapeRenderer);
            s+=this.data.unit;
        }

        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    private void drawHorizonLine(float y, ShapeRenderer shapeRenderer) {
        float xMin = this.data.leftDown.x;
        float xMan = this.data.rightUp.x;
        shapeRenderer.rectLine(xMin, y, xMan, y, 5f);
    }

    private void drawVerticalLine(float x, ShapeRenderer shapeRenderer) {
        float yMin = this.data.leftDown.y;
        float yMax = this.data.rightUp.y;
        shapeRenderer.rectLine(x, yMin, x, yMax,5f);

    }

    private void resetOrigins() {
        // reset
        float widthF = (float) Gdx.graphics.getWidth();
        float heightF = (float) Gdx.graphics.getHeight();
        this.data.leftDown.set(0,heightF,0);
        this.data.rightUp.set(widthF, 0, 0);

        // turn all the coordinate to world coordinate
        OrthographicCamera mainCamera = instance.getCameraManager().getMainCamera();
        mainCamera.unproject(this.data.leftDown);
        mainCamera.unproject(this.data.rightUp);

        float s = this.data.leftDown.x % this.data.unit;
        this.data.xs = this.data.leftDown.x - s;
        this.data.xs -= this.data.unit;
        s = this.data.leftDown.y % this.data.unit;
        this.data.ys = this.data.leftDown.y - s;
        this.data.ys -= this.data.unit;
    }
}
