package com.demosoft.flatworld.graphic.ui.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Andrii_Korkoshko on 1/17/2017.
 */
@Configuration
public class MainMenuConfig {


    @Bean
    Skin skin() {
        return new Skin(Gdx.files.internal("ui/uiskin.json"));
    }

    @Bean
    OrthographicCamera camera() {
        return new OrthographicCamera();
    }

    @Bean
    Viewport viewPort() {
        return new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera());
    }
}
