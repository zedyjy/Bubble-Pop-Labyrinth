package player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import helpers.GameInfo;

public class Player extends Sprite {

    private World world;
    private Body body;
    float x,y;
    private TextureAtlas textureAtlas;
    private Animation animation;
    private float elapsedTime;
    private boolean isWalking;

    public Player(World world,String name, float x, float y,String type){
        super(new Texture(type));
        this.world = world;
        setPosition(x-getWidth()/2f, y-getHeight()/2f);
        createBody();
        this.x = x;
        this.y = y;
        textureAtlas = new TextureAtlas("PlayerAnimation/player.atlas");
    }

    void createBody(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(getX()/ GameInfo.PPM,getY()/GameInfo.PPM);

        body = world.createBody(bodyDef);
        body.setFixedRotation(true);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(((getWidth()/3f)/GameInfo.PPM),((getHeight()/3f)/GameInfo.PPM));

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 100000f;//mass
        fixtureDef.friction = 200000f;

        Fixture fixture = body.createFixture(fixtureDef);

        fixture.setUserData("Player");

        shape.dispose();
    }
    public void updatePlayer(){
        this.setPosition(body.getPosition().x * GameInfo.PPM,body.getPosition().y * GameInfo.PPM);
    }

    public void drawPlayerIdle(SpriteBatch batch){
        if(!isWalking) {
            batch.draw(this, getX() - getWidth() / 2f, getY() - getHeight() / 2f + 6);
        }
    }

    public Body getBody() {
        return this.body;
    }

    public void movePlayer(float x,float y){
        if(x < 0 && !this.isFlipX()){
            this.flip(true,false);
        } else if(x > 0 && this.isFlipX()){
            this.flip(true,false);
        }
        body.setLinearVelocity(x,y);
        isWalking = true;
    }

    public void drawAnimation(SpriteBatch batch){
        Array<TextureAtlas.AtlasRegion> frames = textureAtlas.getRegions();

        for(TextureRegion frame : frames){
            if(body.getLinearVelocity().x<0 && !frame.isFlipX()){
                frame.flip(true,false);
            }else if(body.getLinearVelocity().x>0 && frame.isFlipX()){
                frame.flip(true,false);
            }
        }
        if(isWalking){
            elapsedTime += Gdx.graphics.getDeltaTime();

            animation = new Animation(1f/30f,textureAtlas.getRegions());

            batch.draw((TextureRegion) animation.getKeyFrame(elapsedTime,true),getX()-getWidth()/2f,getY()-getHeight()/2f+6);
        }
    }
    public void setWalking(boolean isWalking){
        this.isWalking = isWalking;
    }
    public boolean getWalking(){
        return isWalking;
    }
}
