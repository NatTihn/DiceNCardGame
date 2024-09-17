package physics;

public class CollisionBox extends CollisionShape {
	public float width, height;

	
	public CollisionBox(float width, float height) {
		this(0, 0, width, height);
	}

	public CollisionBox(float x, float y, float width, float height) {
		super(x, y);
		this.width = width;
		this.height = height;
	}


	public boolean testPoint(float px, float py) {
		return ((x <= px) && (px <= x + width)) && ((y <= py) && (py <= y + width));
	}

	

	public boolean testOverlap(CollisionBox box) {
		return testPoint(box.x, box.y) || testPoint(box.x, box.y + box.height) || 
		testPoint(box.x + box.width, box.y)  || testPoint(box.x + box.width, box.y + box.height);
	}
}
