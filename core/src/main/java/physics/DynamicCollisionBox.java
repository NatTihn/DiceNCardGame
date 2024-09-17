package physics;

import java.util.Arrays;

import com.badlogic.gdx.math.Vector2;

public class DynamicCollisionBox extends CollisionShape {
	final public Vector2[] v;
	final public Vector2[] e;
	final public float[][] dotSelf;


	
	public DynamicCollisionBox(float x, float y, float width, float height) {
		this.x = (x + width) / 2;
		this.y = (y + height) / 2;
		v = new Vector2[4];
		e = new Vector2[4];
		dotSelf = new float[4][4];

		v[0] = new Vector2(x, y);
		v[1] = new Vector2(x + width, y);
		v[2] = new Vector2(x + width, y + height);
		v[3] = new Vector2(x, y + height);

		calcEdgeNormals();
		caclDotSelf();
	}
	public DynamicCollisionBox(float width, float height) {
		this(0, 0, width, height);
	}

	private void calcEdgeNormals() {
		Vector2 _e = new Vector2(v[0].sub(v[1])); v[0].add(v[1]);
		e[0] = new Vector2(-_e.y, -_e.x);
		_e = new Vector2(v[1].sub(v[2])); v[1].add(v[2]);
		e[1] = new Vector2(-_e.y, -_e.x);
		_e = new Vector2(v[2].sub(v[3])); v[2].add(v[3]);
		e[2] = new Vector2(-_e.y, -_e.x);
		_e = new Vector2(v[3].sub(v[0])); v[3].add(v[0]);
		e[3] = new Vector2(-_e.y, -_e.x);
		
	}

	private void caclDotSelf() {
		for (int i = 0; i < this.dotSelf.length; i++) {
			for (int j = 0; j < this.v.length; j++) 
				dotSelf[i][j] = e[i].dot(v[j]);
			Arrays.sort(dotSelf[i]);
		}
	}

	private boolean testEdgeBox(int eIndex, DynamicCollisionBox box) {
		float dotForeign[] = new float[4];


		for (int i = 0; i < box.v.length; i++) 
			dotForeign[i] = e[eIndex].dot(box.v[i]);
		

		Arrays.sort(dotForeign);
		float aMax, aMin, bMax, bMin;
		aMax = dotSelf[eIndex][3];
		aMin = dotSelf[eIndex][0];
		
		bMax = dotForeign[3];
		bMin = dotForeign[0];

		return ((bMin <= aMin) && (aMin <= bMax)) || ((bMin <= aMax) && (aMax <= bMax));
	}

	private boolean testOverlap(DynamicCollisionBox box) {
		for (int i = 0; i < this.v.length; i++) {
			if(!testEdgeBox(i, box))
				return false;
		}

		return true;
	}

	private boolean testEdgePoint(int eIndex, float x, float y) {
		float dotPoint = e[eIndex].dot(x, y);

		return (dotSelf[eIndex][0] <= dotPoint) && (dotPoint <= dotSelf[eIndex][3]);
	}

	public boolean testPoint(float x, float y) {
		for (int i = 0; i < this.e.length; i++) {
			if(!testEdgePoint(i, x, y))
				return false;
		}

		return true;
	}

	public boolean testPoint(Vector2 p) {
		return testPoint(p.x, p.y);
	}
}
