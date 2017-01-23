package com.demosoft.flatworld.graphic;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by Andrii_Korkoshko on 1/16/2017.
 */
@Component
public class GraphicApplicationContainer {

    private LwjglApplication lwjglApplication;

    @Autowired
    private  MedievalLibGDXApplication game;



    public GraphicApplicationContainer() {

    }

    @PostConstruct
    void init() {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "FlatMedievalWorld";
        //cfg.useGL20 = true;
        cfg.width = 800;
        cfg.height = 640;


        game = new MedievalLibGDXApplication();
        lwjglApplication = new LwjglApplication(game, cfg);
    }

    public MedievalLibGDXApplication getGame() {
        return game;
    }

    public LwjglApplication getLwjglApplication() {
        return lwjglApplication;
    }
}
