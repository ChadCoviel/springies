package walls;

import jboxGlue.PhysicalObjectRect;

import ourObjects.Constants;

/**
 * All walls are a subclass of this abstract class.
 * 
 */

public abstract class Wall extends PhysicalObjectRect {
	protected String myId;
	protected double myX, myY, myHeight, myWidth, myMagnitude, myExponent;
	protected boolean myCollision;

	public Wall(String id, double x, double y, double width, double height,
			double magnitude, double exponent) {
		super(id, Constants.CID_WALL, Constants.COLOR_WALL, width, height);
		setPos(x, y);
		myX = x;
		myY = y;
		myId = id;
		myMagnitude = magnitude;
		myExponent = exponent;
		myCollision = true;
	}

	public String toString() {
		return "Wall";
	}

	public String getId() {
		return myId;
	}

	public double getMagnitude() {
		return myMagnitude;
	}

	public double getExponent() {
		return myExponent;
	}

	public boolean getCollisionStatus() {
		return myCollision;
	}

	public void toggleWallStatus() {
		myCollision = !myCollision;
	}
	
	public abstract boolean isLeftOrRightWall();

	/**
	 * Method that tells programs where to place the new wall, with the size and x-y position.
	 */
	public abstract WallReposition newWall(double pixels);
}
