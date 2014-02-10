package walls;

/**
 * To reposition Walls and create a smaller or larger simulation area, all walls must be a subclass of this abstract class.
 * 
 */

public abstract class WallReposition {
	protected double myX, myY, myHeight, myWidth, myMagnitude, myExponent;

	/**
	 * This constructor simply stores all the fields used by a Wall constructor.
	 */
	public WallReposition(double x, double y, double length, double thick, double magnitude,
			double exponent) {
		myX = x;
		myY = y;
		myHeight = length;
		myWidth = thick;
		myMagnitude = magnitude;
		myExponent = exponent;
	}

	/**
	 * This is where the magic happens. The previous wall will have already been
	 * destroyed, but the egg contains the ability to spawn a new wall here.
	 * This will create the correct concrete Wall object to go with the type of
	 * egg. i.e. a TopWallEgg will hatch a TopWall and so on.
	 * 
	 * @return A concrete Wall object
	 */
	public abstract Wall placeNewWall();

	public String toString() {
		return String.format("EGG --> x: %f, y: %f, len: %f, thick: %f", myX,
				myY, myHeight, myWidth);
	}

}
