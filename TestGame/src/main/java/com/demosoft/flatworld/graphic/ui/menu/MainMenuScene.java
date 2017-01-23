package com.demosoft.flatworld.graphic.ui.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.demosoft.flatworld.graphic.BaseScene;
import com.demosoft.flatworld.graphic.SpritesLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by Andrii_Korkoshko on 1/17/2017.
 */
@Component
public class MainMenuScene extends BaseScene {

    @Autowired
    private FlippedStage stage;

    @Autowired
    private OrthographicCamera camera;

    @Autowired
    private Viewport viewport;

    @Autowired
    private Skin skin;

    private Image background;
    private Image title;

    @Autowired
    private SpritesLoader spritesLoader;

    @PostConstruct
    void init() {
        background = new Image(spritesLoader.manager.get("ui/menu_background.jpg", Texture.class));
        title = new Image(spritesLoader.manager.get("logo.png", Texture.class));
        moveBackgroundToCentre(background);
        stage.addActor(background);
        stage.addActor(title);
        if (Gdx.input.getInputProcessor() instanceof InputMultiplexer) {
            ((InputMultiplexer) Gdx.input.getInputProcessor()).addProcessor(0, stage);
        }
    }


    public MainMenuScene() {
    }

    public void moveBackgroundToCentre(Image bg) {
        int sWidth = Gdx.graphics.getWidth();
        int sHeight = Gdx.graphics.getHeight();
        if (sWidth == bg.getWidth() && sHeight == bg.getHeight()) {
            return;
        }
        Vector2 diff = new Vector2(sWidth / 2 - bg.getWidth() / 2, sHeight / 2 - bg.getHeight() / 2);
        bg.setX(diff.x);
        bg.setY(diff.y);
    }

    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        // Show the loading screen
        stage.act();
        stage.draw();

        // Table.drawDebug(stage);
        super.render(delta);
        camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }

    @Override
    public void resize(int width, int height) {
        System.out.println("mune resize");
        //Gdx.graphics.setDisplayMode(width, height, Gdx.graphics.isFullscreen());
        Gdx.graphics.setWindowedMode(width, height);
        moveBackgroundToCentre(background);
        viewport.update(width, height);
        camera.update();
    }
}
