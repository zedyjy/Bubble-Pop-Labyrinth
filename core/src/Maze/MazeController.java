package Maze;

import collectables.Collectable;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import player.Player;

public class MazeController {

    private World world;
    private Array<Maze> mazeArray = new Array<Maze>();
    Maze maze;
    SpriteBatch batch;
    Player player;
    Collectable collectable;

    public MazeController(World world,SpriteBatch batch,Maze maze){
        this.world = world;
        this.batch = batch;
        this.maze = maze;
    }

    public void drawCollectables(){

    }

    public void createWalls(){
        drawMaze(1800,850,0,false);
        drawMaze(1800,0,0,false);
        drawMaze(150,750,150,true);
        drawMaze(0,750,0,true);
        drawMaze(1750,900,200,true);
        drawMaze(1500,150,200,false);
        drawMaze(1600,700,0,true);
        drawMaze(1000,700,200,false);
        drawMaze(800,850,700,true);
        drawMaze(1600,550,300,false);

        drawMaze(350,300,200,false);

        drawMaze(450,400,200,true);
        drawMaze(650,600,300,true);
        drawMaze(850,400,200,true);
        drawMaze(1050,600,300,true);

        drawMaze(1500,350,1200,false);
        drawMaze(1200,350,200,true);
        drawMaze(1300,750,600,true);

    }
    public void drawMaze(int width, int height, int fromWhere,boolean isHeight){
        if(isHeight) {
            for (int i = fromWhere; i < height; i = i + 50) {
                batch.draw(maze, width, i);
                maze.setSpritePosition(width, i);
            }
        }
        else{
            for (int i = fromWhere; i < width; i = i + 50) {
                batch.draw(maze, i, height);
                maze.setSpritePosition(i, height);
            }
        }
    }

    public Player positionThePlayer(){
        player = new Player(world,"Player",player.getX()-player.getWidth()/2f,player.getY()-player.getHeight()/2f+6,"player 1.png");
        return player;
    }
}
