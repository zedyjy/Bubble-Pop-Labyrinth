package huds;

import Maze.Maze;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.gametry.MyTryGame;
import helpers.GameInfo;
import scenes.Level1GamePlay;
import scenes.MainMenu;
import scenes.Options;

public class UIHud {
    private MyTryGame game;
    private Stage stage;
    private Viewport viewport;
    private ImageButton basketBtn,pauseBtn,resumeBtn,quitBtn;
    private Image pausePanel;

    public UIHud(MyTryGame game){
        this.game = game;
        viewport = new FitViewport(GameInfo.WIDTH,GameInfo.HEIGHT,new OrthographicCamera());

        stage = new Stage(viewport, game.getBatch());

        Gdx.input.setInputProcessor(stage);

        createImages();
        addAllListeners();
        stage.addActor(pauseBtn);
        stage.addActor(basketBtn);
    }

    void createImages(){
    }

    void addAllListeners(){
        pauseBtn = new ImageButton(new SpriteDrawable(new Sprite(new Texture("Buttons/pause.png"))));
        pauseBtn.setPosition(1730,900, Align.topRight);

        pauseBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                createPausePanel();
            }
        });
        basketBtn = new ImageButton(new SpriteDrawable(new Sprite(new Texture("Buttons/img_3.png"))));
        basketBtn.setPosition(1823,903, Align.topRight);

        basketBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new Options(game));
            }
        });
    }

    void createPausePanel(){
        pausePanel = new Image(new Texture(("Backgrounds/b.png")));
        resumeBtn = new ImageButton(new SpriteDrawable(new Sprite(new Texture("Buttons/resume btn.png"))));
        quitBtn = new ImageButton(new SpriteDrawable(new Sprite(new Texture("Buttons/quit btn.png"))));

        pausePanel.setPosition(GameInfo.WIDTH/2f,GameInfo.HEIGHT/2f,Align.center);
        resumeBtn.setPosition(GameInfo.WIDTH/2f,GameInfo.HEIGHT/2f+50,Align.center);
        quitBtn.setPosition(GameInfo.WIDTH/2f,GameInfo.HEIGHT/2f-80,Align.center);

        resumeBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                removePausePanel();
            }
        });
        quitBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenu(game));
            }
        });
        stage.addActor(pausePanel);
        stage.addActor(resumeBtn);
        stage.addActor(quitBtn);
    }

    void removePausePanel(){
        pausePanel.remove();
        resumeBtn.remove();
        quitBtn.remove();
    }
    public Stage getStage() {
        return stage;
    }
}
