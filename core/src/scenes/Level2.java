package scenes;

import Maze.Maze;
import collectables.Collectable;
import collectables.CollectableController;
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

public class Level2 implements Screen, ContactListener{
    private MyTryGame game;
    private Sprite bgs;
    Player player,player2;
    private World world;
    private OrthographicCamera box2dCamera;
    private Box2DDebugRenderer debugRenderer;
    UIHud uiHud;
    private Viewport viewport;
    Maze tree1,tree2,tree3,tree4,tree5,tree6,tree7,tree8;
    CollectableController collectableController;
    Collectable collectable;

    public Level2(MyTryGame game){
        this.game = game;

        createBackgrounds();

        box2dCamera = new OrthographicCamera();
        box2dCamera.setToOrtho(false, GameInfo.WIDTH/GameInfo.PPM,GameInfo.HEIGHT/GameInfo.PPM);
        box2dCamera.position.set(GameInfo.WIDTH/2f,GameInfo.HEIGHT/2f,0);
        viewport = new StretchViewport(GameInfo.WIDTH,GameInfo.HEIGHT,box2dCamera);

        debugRenderer = new Box2DDebugRenderer();

        world = new World(new Vector2(0,0),true);
        player = new Player(world,"Player", 70,858,"playr.png");
        tree1 = new Maze(world,"Trees/cute tree 1.png");
        tree2 = new Maze(world,"Trees/cute tree 2.png");
        tree3 = new Maze(world,"Trees/cute tree 3.png");
        tree4 = new Maze(world,"Trees/cute tree 4.png");
        tree5 = new Maze(world,"Trees/cute tree 5.png");
        tree6 = new Maze(world,"Trees/cute tree 6.png");
        tree7 = new Maze(world,"Trees/cute tree 7.png");
        tree8 = new Maze(world,"Trees/cute tree 8.png");

        uiHud = new UIHud(game);
        collectable = new Collectable(world,"ice cream","Collectables/ice cream.png");
        collectableController = new CollectableController(world, game.getBatch(), collectable);

        world.setContactListener(this);
    }

    void createBackgrounds(){
        bgs = new Sprite(new Texture("Backgrounds/brown bg.png"));
    }

    void drawBackgrounds(){
        game.getBatch().draw(bgs, 0, 0);
    }

    @Override
    public void show() {

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
    public void render(float delta) {
        update(delta);

        player.updatePlayer();

        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin();
        drawBackgrounds();
        player.drawPlayerIdle(game.getBatch());
        player.drawAnimation(game.getBatch());

        game.getBatch().draw(tree1,500,200);
        tree1.setSpritePosition(500, 200);
        game.getBatch().draw(tree1,700,600);
        tree1.setSpritePosition(700, 600);

        game.getBatch().draw(tree2,600,10);
        tree2.setSpritePosition(600, 10);
        game.getBatch().draw(tree2,200,200);
        tree2.setSpritePosition(200, 200);

        game.getBatch().draw(tree3,200,400);
        tree3.setSpritePosition(200, 400);
        game.getBatch().draw(tree3,1500,500);
        tree3.setSpritePosition(1500, 500);

        game.getBatch().draw(tree4,1300,450);
        tree4.setSpritePosition(1300, 450);
        game.getBatch().draw(tree4,1000,340);
        tree4.setSpritePosition(1000, 340);

        game.getBatch().draw(tree5,10,10);
        tree5.setSpritePosition(10, 10);
        game.getBatch().draw(tree5,50,730);
        tree5.setSpritePosition(50, 730);

        game.getBatch().draw(tree6,1700,300);
        tree6.setSpritePosition(1700, 300);
        game.getBatch().draw(tree6,1500,750);
        tree6.setSpritePosition(1500, 750);

        game.getBatch().draw(tree7,1200,750);
        tree7.setSpritePosition(1200, 750);
        game.getBatch().draw(tree7,1470,250);
        tree7.setSpritePosition(1470, 250);
        game.getBatch().draw(tree7,1700,30);
        tree7.setSpritePosition(1700, 30);
        game.getBatch().draw(tree7,760,800);
        tree7.setSpritePosition(760, 800);

        game.getBatch().draw(tree8,1740,310);
        tree8.setSpritePosition(1740, 310);
        game.getBatch().draw(tree8,100,580);
        tree8.setSpritePosition(100, 580);
        game.getBatch().draw(tree8,1400,30);
        tree8.setSpritePosition(1400, 30);

        game.getBatch().end();

        collectableController.drawCollectables(20,20);

        debugRenderer.render(world,box2dCamera.combined);

        game.getBatch().setProjectionMatrix(uiHud.getStage().getCamera().combined);
        uiHud.getStage().draw();

        world.step(Gdx.graphics.getDeltaTime(),6,2);

        advanceToNextLevel();
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

    public void advanceToNextLevel(){
        if(player.getX()>1790 && player.getY()<200){
            game.setScreen( new Level2( game ));
        }
    }
}
