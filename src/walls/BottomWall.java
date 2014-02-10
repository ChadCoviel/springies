package walls;

public class BottomWall extends Wall {

	public BottomWall(String id, double x, double y, double length,
			double thickness, double magnitude, double exponent) {
		super(id, x, y, length, thickness, magnitude, exponent);
		myHeight = length;
		myWidth = thickness;
	}

	@Override
	public boolean isLeftOrRightWall() {
		return false;
	}

	@Override
	public WallReposition newWall(double pixels) {
		double newX = myX;
		double newY = myY + pixels;
		return new NewBottomWall(newX, newY, myHeight + 2 * pixels,
				myWidth, myMagnitude, myExponent);
	}
}
