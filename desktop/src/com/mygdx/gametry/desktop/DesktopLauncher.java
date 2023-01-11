package com.mygdx.gametry.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.gametry.MyTryGame;
import helpers.GameInfo;

import java.awt.*;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		GameInfo i = new GameInfo();

		//set height and width
		config.setWindowedMode(i.WIDTH, i.HEIGHT);
		new Lwjgl3Application(new MyTryGame(), config);//
	}
}
