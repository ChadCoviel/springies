package walls;

public class TopWall extends Wall {

	public TopWall(String id, double x, double y, double length,
			double thickness, double magnitude, double exponent) {
		super(id, x, y, length, thickness, magnitude, exponent);
		myHeight = length;
		myWidth = thickness;
	}

	public String toString() {
		return "Wall";
	}

	@Override
	public boolean isLeftOrRightWall() {
		return false;
	}

	@Override
	public WallReposition newWall(double pixels) {
		double newX = myX;
		double newY = myY - pixels;
		return new NewTopWall(newX, newY, myHeight + 2 * pixels, myWidth,
				myMagnitude, myExponent);
	}
}
