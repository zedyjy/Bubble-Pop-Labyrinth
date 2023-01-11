package scenes;

import Maze.Maze;
import Maze.MazeController;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.gametry.MyTryGame;
import helpers.GameInfo;
import huds.UIHud;
import player.Player;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Level1GamePlay implements Screen, ContactListener {

    private MyTryGame game;
    private Sprite bgs;
    Player player,player2;
    Maze maze, mazeEmpty,mazeVertical;
    private World world;
    private OrthographicCamera box2dCamera;
    private Box2DDebugRenderer debugRenderer;
    MazeController mazeController;
    UIHud uiHud;
    private Viewport viewport;


    public Level1GamePlay(MyTryGame game){
        this.game = game;

        createBackgrounds();

        box2dCamera = new OrthographicCamera();
        box2dCamera.setToOrtho(false, GameInfo.WIDTH/GameInfo.PPM,GameInfo.HEIGHT/GameInfo.PPM);
        box2dCamera.position.set(GameInfo.WIDTH/2f,GameInfo.HEIGHT/2f,0);
        viewport = new StretchViewport(GameInfo.WIDTH,GameInfo.HEIGHT,box2dCamera);

        debugRenderer = new Box2DDebugRenderer();

        world = new World(new Vector2(0,0),true);
        player = new Player(world,"Player", 70,858,"playr.png");

        uiHud = new UIHud(game);

        world.setContactListener(this);

        maze = new Maze(world,"maze wall 1.jpg");
        mazeEmpty = new Maze(world,"pink block.png");
        mazeVertical = new Maze(world,"maze wall dik.png");

        mazeController = new MazeController(world, game.getBatch(), maze);
    }

    void createBackgrounds(){
        bgs = new Sprite(new Texture("Backgrounds/nodesigntwice.png"));
    }

    void drawBackgrounds(){
            game.getBatch().draw(bgs, 0, 0);
    }

    void update(float dt){
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            player.movePlayer(-2,0);
            player.setWalking(true);
        }else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            player.movePlayer(2,0);
            player.setWalking(true);
        }else if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player.movePlayer(0, 2);
            player.setWalking(false);
        }else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            player.movePlayer(0,-2);
            player.setWalking(false);
        }else{
            player.setWalking(false);
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        update(delta);

        player.updatePlayer();

        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin();
        drawBackgrounds();
        player.drawPlayerIdle(game.getBatch());
        player.drawAnimation(game.getBatch());

       mazeController.createWalls();

       game.getBatch().end();

        debugRenderer.render(world,box2dCamera.combined);

        game.getBatch().setProjectionMatrix(uiHud.getStage().getCamera().combined);
        uiHud.getStage().draw();

        world.step(Gdx.graphics.getDeltaTime(),6,2);

        advanceToNextLevel();
    }

    public void advanceToNextLevel(){
        if(player.getX()>1790 && player.getY()<200){
            game.setScreen( new Level2( game ));
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        bgs.getTexture().dispose();
        player.getTexture().dispose();
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture firstBody, secondBody;
        if(contact.getFixtureA().getUserData() == "Player"){
           firstBody = contact.getFixtureA();
           secondBody = contact.getFixtureB();
        }
        else{
            secondBody = contact.getFixtureA();
            firstBody = contact.getFixtureB();
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }


}
