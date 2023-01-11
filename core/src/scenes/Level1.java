package scenes;

import Maze.Maze;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.gametry.MyTryGame;
import helpers.GameInfo;
import huds.UIHud;
import player.Player;

public class Level1  implements Screen, ContactListener {
    private MyTryGame game;
    private Sprite [] bgs;
    Player player,player2;
    private World world;
    private OrthographicCamera mainCamera;
    private Box2DDebugRenderer debugRenderer;
    UIHud uiHud;
    private Viewport viewport;


    public Level1(MyTryGame game){
        this.game = game;

        createBackgrounds();

        mainCamera = new OrthographicCamera(GameInfo.WIDTH/GameInfo.PPM,GameInfo.HEIGHT/GameInfo.PPM);
        mainCamera.setToOrtho(false, GameInfo.WIDTH/GameInfo.PPM,GameInfo.HEIGHT/GameInfo.PPM);
        mainCamera.position.set(GameInfo.WIDTH/2f,GameInfo.HEIGHT/2f,0);
        viewport = new StretchViewport(GameInfo.WIDTH,GameInfo.HEIGHT,mainCamera);

        debugRenderer = new Box2DDebugRenderer();

        world = new World(new Vector2(0,0),true);
        player = new Player(world,"Player", 70,858,"playr.png");

        uiHud = new UIHud(game);

        world.setContactListener(this);
    }

    void createBackgrounds(){
        bgs = new Sprite[3];
        for(int i = 0; i < bgs.length; i++) {
            bgs[i] = new Sprite(new Texture("Backgrounds/level 1 bg.png"));
            bgs[i].setPosition((i*bgs[i].getHeight()),0);
        }
    }

    void drawBackgrounds(){
        for(int i = 0; i < bgs.length; i++) {
            game.getBatch().draw(bgs[i], bgs[i].getX(), bgs[i].getY());
        }
    }

    void update(float dt){
        moveCamera();
    }

    void moveCamera(){
        mainCamera.position.x += 2;
    }

    void updateScreen(float dt){
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

        updateScreen(delta);

        player.updatePlayer();

        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin();
        drawBackgrounds();
        player.drawPlayerIdle(game.getBatch());
        player.drawAnimation(game.getBatch());

        game.getBatch().end();

        //debugRenderer.render(world,mainCamera.combined);

        game.getBatch().setProjectionMatrix(mainCamera.combined);
        mainCamera.update();
        //game.getBatch().setProjectionMatrix(uiHud.getStage().getCamera().combined);
        //uiHud.getStage().draw();

        world.step(Gdx.graphics.getDeltaTime(),6,2);

        advanceToNextLevel();
    }

    public void advanceToNextLevel(){
        game.setScreen(new Level2(game));
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
        for(int i = 0; i<bgs.length; i++) {
            bgs[i].getTexture().dispose();
        }
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
