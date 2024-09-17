package physics;

public abstract class CollisionShape {
	public float x, y;

	
	public CollisionShape() {
		x = 0;
		y = 0;
	}
	public CollisionShape(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public boolean testPoint(float x, float y) {
		return false;
	}

}
