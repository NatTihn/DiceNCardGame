package io.github.gamblerDungeon;

import java.lang.reflect.Array;
import java.util.Arrays;

import org.w3c.dom.Text;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.math.collision.OrientedBoundingBox;
import com.badlogic.gdx.utils.Align;

import physics.DynamicCollisionBox;

public class Card {
	private Sprite txtr;
	private CardData data;
	private BitmapFont font;
	private DynamicCollisionBox collisionBox;

	private static FreeTypeFontGenerator fontGen;
	private static FreeTypeFontParameter fontParam;
	public final Vector2 position;

	static {
		fontGen = new FreeTypeFontGenerator(Gdx.files.internal("fonts/IMPACT.TTF"));
		fontParam = new FreeTypeFontParameter();
		fontParam.size = 22;
		fontParam.color = new Color(0xEE4E4EFF);
	}

	public Card(int id) {
		position = new Vector2();
		Texture cardTxtr = new Texture("textures/aceSpade.png");
		float scale = 0.2f;
		txtr = new Sprite(cardTxtr);
		txtr.setBounds(0, 0, cardTxtr.getWidth() * scale, cardTxtr.getHeight() * scale);
		txtr.setOrigin(cardTxtr.getWidth() * scale * 0.5f, cardTxtr.getHeight() * scale * 0.5f);

		font = fontGen.generateFont(fontParam);
		collisionBox = new DynamicCollisionBox(cardTxtr.getWidth() * scale, cardTxtr.getHeight() * scale);
		System.out.println(Arrays.toString(collisionBox.dotSelf[0]));
		System.out.println(Arrays.toString(collisionBox.dotSelf[1]));
		System.out.println(Arrays.toString(collisionBox.dotSelf[2]));
		System.out.println(Arrays.toString(collisionBox.dotSelf[3]));

		// setRotation(45f);

	}

	public void setRotation(float r) {
		txtr.setRotation(r);
		Vector3 p = new Vector3();
	}

	public void setPosition(Vector2 p) {
		txtr.setPosition(p.x, p.y);
	}

	public Vector2 getPosition() {
		return position;
	}

	public void draw(Batch batch) {
		
		if(isIn())
			txtr.setFlip(false, true);
		else
			txtr.setFlip(false, false);

		txtr.draw(batch);
		font.draw(batch, "hello", position.x, position.y, txtr.getWidth() , Align.center, true);

	}

	public boolean isIn() {
		// System.out.println(Input.getXY().toString());
		return collisionBox.testPoint(Input.getXY());
	}
}
