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

	/*Key method of changing the walls are the area size they enclose*/

	public abstract Wall placeNewWall();

	public String toString() {
		return "Wall";
	}

}
