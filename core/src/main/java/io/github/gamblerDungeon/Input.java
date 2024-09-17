package io.github.gamblerDungeon;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;


public abstract class Input {
	static private int mouseX, mouseY;

	static void updateMouse(int x, int y, Camera cam) {
		Vector3  p = new Vector3(x, y, 0);
		p = cam.unproject(p);
		
		
		mouseX = MathUtils.floor(p.x);
		mouseY = MathUtils.floor(p.y);
	}

	public static int getX() {
		return mouseX;
	}

	public static int getY() {
		return mouseY;
	}
	public static Vector2 getXY() {
		return new Vector2(mouseX, mouseY);
	}
}
