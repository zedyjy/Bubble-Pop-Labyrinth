package com.mygdx.gametry;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import scenes.Level1GamePlay;
import scenes.MainMenu;

public class MyTryGame extends Game {
	private SpriteBatch batch;

    public SpriteBatch getBatch(){
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
			Gdx.graphics.setWindowedMode(1900,970);
		}
    	return this.batch;
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new MainMenu(this));
	}

	@Override
	public void render () {
    	super.render();

	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
