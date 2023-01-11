package Maze;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import helpers.GameInfo;

public class Maze extends Sprite {

    private World world;
    private Body body;

    public Maze(World world,String name){
        super(new Texture(name));
        this.world = world;
        //setPosition(x+getWidth()/2f, y+getHeight()/2f);
        //createBody();
    }

    public void createBody(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(getX()/ GameInfo.PPM,getY()/GameInfo.PPM);

        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox((getWidth()/2.5f)/GameInfo.PPM,(getHeight()/2.5f)/GameInfo.PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1;//mass

        Fixture fixture = body.createFixture(fixtureDef);

        fixture.setUserData("Maze Wall");

        //eğer topladığı bi şeyse içinden alıp geçmek istiyosa
        //fixture.setSensor(true);

        shape.dispose();
    }

    public void setSpritePosition(float x, float y){
        setPosition(x+getWidth()/2f, y+getHeight()/2f);
        createBody();
    }
}
