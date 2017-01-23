package com.demosoft.flatworld.graphic;

import com.badlogic.gdx.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Andrii_Korkoshko on 5/26/2016.
 */
@Component
public class MedievalLibGDXApplication extends Game {

    @Autowired
    private LoadingScreen loadingScreen;

    public MedievalLibGDXApplication() {
        setScreen(loadingScreen);
    }

    @Override
    public void create() {
        System.out.println("create");
    }

    @Override
    public void resize(int width, int height) {
        System.out.println("resize width:" + width + " height:" + height);
    }

    @Override
    public void render() {
        /*System.out.println("render");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public void pause() {
        System.out.println("pause");
    }

    @Override
    public void resume() {
        System.out.println("resume");
    }

    @Override
    public void dispose() {
        System.out.println("dispose");
    }
}
