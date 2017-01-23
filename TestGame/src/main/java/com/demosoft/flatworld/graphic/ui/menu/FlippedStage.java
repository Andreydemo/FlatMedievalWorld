package com.demosoft.flatworld.graphic.ui.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Created by Andrii_Korkoshko on 1/17/2017.
 */
public class FlippedStage extends Stage {

    private boolean flipped = true;

    public FlippedStage(Viewport viewport) {
        super(viewport);
    }

    public FlippedStage(Viewport viewport, boolean flliped) {
        super(viewport);
        this.flipped = flliped;
    }

    public FlippedStage() {
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (flipped) {
            System.out.println("FlippedStage touchUp y:" + (Gdx.graphics.getHeight() - screenY));
            return super.touchUp(screenX, Gdx.graphics.getHeight() - screenY, pointer, button);
        } else {
            System.out.println("FlippedStage touchUp y:" + (screenY));
            return super.touchUp(screenX, screenY, pointer, button);
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (flipped) {
            return super.touchDown(screenX, Gdx.graphics.getHeight() - screenY, pointer, button);
        } else {
            return super.touchDown(screenX, screenY, pointer, button);
        }
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (flipped) {
            return super.touchDragged(screenX, Gdx.graphics.getHeight() - screenY, pointer);
        } else {
            return super.touchDragged(screenX, screenY, pointer);
        }
    }

    public boolean isFlipped() {
        return flipped;
    }

    public void setFlipped(boolean flipped) {
        this.flipped = flipped;
    }
}
