package walls;

import ourObjects.Constants;

public class NewBottomWall extends WallReposition {

	public NewBottomWall(double x, double y, double len, double thick,
			double mag, double exp) {
		super(x, y, len, thick, mag, exp);
	}

	@Override
	public Wall placeNewWall() {
		return new BottomWall(Constants.ID_BOTTOM_WALL, myX, myY, myHeight,
				myWidth, myMagnitude, myExponent);
	}

}
