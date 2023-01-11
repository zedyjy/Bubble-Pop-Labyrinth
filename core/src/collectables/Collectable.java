package collectables;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import helpers.GameInfo;

public class Collectable extends Sprite {

    private World world;
    private Fixture fixture;
    private String name;
    private Body body;

    public Collectable(World world,String name,String type){
        super(new Texture(type));
        this.world = world;
        this.name = name;
    }

    public void createCollectableBody(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;

        bodyDef.position.set(getX()/ GameInfo.PPM,getY()/GameInfo.PPM);

        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(((getWidth()/3f)/GameInfo.PPM),((getHeight()/3f)/GameInfo.PPM));

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;

        fixture = body.createFixture(fixtureDef);

        shape.dispose();
    }

    public void setCollectablePosition(float x, float y){
        this.setPosition(x,y);
    }
}
