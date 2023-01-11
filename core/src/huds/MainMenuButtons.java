package huds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.gametry.MyTryGame;
import helpers.GameInfo;
import scenes.Level1;
import scenes.Level1GamePlay;
import scenes.Level2;
import scenes.Options;

import java.awt.*;

public class MainMenuButtons {
    private MyTryGame game;
    private Stage stage;
    private Viewport viewport;
    private ImageButton playBtn;
    private ImageButton fullScreenBtn;
    private ImageButton musicBtn;
    private ImageButton quitBtn;
    private ImageButton heartbtn;

    public MainMenuButtons(MyTryGame game){
        this.game = game;
        viewport = new FitViewport(GameInfo.WIDTH,GameInfo.HEIGHT,new OrthographicCamera());

        stage = new Stage(viewport, game.getBatch());

        Gdx.input.setInputProcessor(stage);

        createAndPositionButtons();

        addAllListeners();

        stage.addActor(playBtn);
        stage.addActor(fullScreenBtn);
        stage.addActor(heartbtn);
        stage.addActor(quitBtn);
        stage.addActor(musicBtn);
    }

    void createAndPositionButtons(){
        playBtn = new ImageButton(new SpriteDrawable(new Sprite(new Texture("Buttons/super start btn.png"))));
        fullScreenBtn = new ImageButton(new SpriteDrawable(new Sprite(new Texture("Buttons/img_2.png"))));
        heartbtn = new ImageButton(new SpriteDrawable(new Sprite(new Texture("Buttons/super heart btn.png"))));
        quitBtn = new ImageButton(new SpriteDrawable(new Sprite(new Texture("Buttons/img_1.png"))));
        musicBtn = new ImageButton(new SpriteDrawable(new Sprite(new Texture("Buttons/img.png"))));

        playBtn.setPosition(GameInfo.WIDTH/2f,GameInfo.HEIGHT/2f+40, Align.center);
        fullScreenBtn.setPosition(GameInfo.WIDTH/2f,GameInfo.HEIGHT/2f-80, Align.center);
        quitBtn.setPosition(GameInfo.WIDTH/2f+3,GameInfo.HEIGHT/2f-140, Align.center);
        musicBtn.setPosition(GameInfo.WIDTH/2f,GameInfo.HEIGHT/2f-210, Align.center);
        heartbtn.setPosition(1650,770);
    }

    public Stage getStage() {
        return stage;
    }

    void addAllListeners(){
        playBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new Level2(game));
            }
        });
        fullScreenBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.graphics.setResizable( true );
                Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
            }
        });
        quitBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        });
    }
}
