package collectables;

import Maze.Maze;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import player.Player;

public class CollectableController {
    private World world;
    SpriteBatch batch;
    Player player;
    Collectable collectable;

    public CollectableController(World world,SpriteBatch batch,Collectable collectable){
        this.world = world;
        this.batch = batch;
        this.collectable = collectable;
    }

    public void drawCollectables(float x ,float y){
        collectable.setCollectablePosition(x,y);
        batch.draw(collectable,collectable.getX(),collectable.getY());
    }
}
