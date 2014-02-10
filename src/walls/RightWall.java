package walls;

public class RightWall extends Wall {

	public RightWall(String id, double x, double y, double length,
			double thickness, double magnitude, double exponent) {
		super(id, x, y, thickness, length, magnitude, exponent);
		myHeight = length;
		myWidth = thickness;
	}

	@Override
	public boolean isLeftOrRightWall() {
		return true;
	}

	@Override
	public WallReposition newWall(double pixels) {
		double newX = myX + pixels;
		double newY = myY;
		return new NewRightWall(newX, newY, myHeight + 2 * pixels, myWidth,
				myMagnitude, myExponent);
	}
}
