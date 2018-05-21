package com.quzzar.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.quzzar.game.GameMain;
import com.quzzar.game.Input;
import com.quzzar.game.Objects.*;
import com.quzzar.game.Utility;

public class MapScreen implements Screen {

    private final GameMain game;
    private final MapScreen mapScreen = this;
    private final Screen returningScreen;

    private SpriteBatch batch;

    private final Image mapImg;

    private final Button arrowBtn;

    public MapScreen(final GameMain game, final Screen returningScreen) {

        this.game = game;
        this.returningScreen = returningScreen;

        this.batch = new SpriteBatch();

        mapImg = new Image(new Texture("game/map/mapImg.jpg"),
                new Location(0.5, 0.5),
                0.2,0.4);

        arrowBtn = new Button(new Texture("game/map/mapArrow.png"), new Texture("game/map/mapArrow.png"),
                new Location(0.5, 0.3),
                0.1, 0.3);

    }

    @Override
    public void show() {

        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                Input.begin();

                //Change map area button
                //Currently has same function as play button
                if (arrowBtn.containsLocation(Input.getTouchedLocation())){
                    mapScreen.dispose();
                }

                Input.end();
                return super.touchUp(screenX, screenY, pointer, button);
            }
        });

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        if(arrowBtn.containsLocation(Input.getTouchedLocation())){
            arrowBtn.drawPressed(batch);
        } else {
            arrowBtn.drawIdle(batch);
        }

        mapImg.draw(batch);

        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        Utility.screenPause();
    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        Utility.screenExit(batch);
    }
}