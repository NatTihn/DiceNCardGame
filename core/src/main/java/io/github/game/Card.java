package io.github.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.math.collision.OrientedBoundingBox;

public class Card {
    private Texture txtr;
    private OrientedBoundingBox collisionBox;

    public Card(int id) {
        txtr =  new Texture("libgdx.png");
        float mat4i[] = {1, 0, 0, 0, 
                        0, 1, 0, 0, 
                        0, 0, 1, 0,
                        0, 0 ,0, 1};
        collisionBox = new OrientedBoundingBox(
            new BoundingBox(Vector3.Zero, new Vector3(100, 100, 0)), new Matrix4(mat4i));
    }

    public Vector2 getPosition() {
        BoundingBox bb = collisionBox.getBounds();
        return new Vector2(bb.getCenterX(), bb.getCenterY());
    }

    public void draw(Batch batch) {
        Vector2 bbv2 = this.getPosition();
        batch.draw(txtr, bbv2.x, bbv2.y);
    }
}
