package walls;

import ourObjects.Constants;

public class NewRightWall extends WallReposition {

	public NewRightWall(double x, double y, double len, double thick,
			double mag, double exp) {
		super(x, y, len, thick, mag, exp);
	}

	@Override
	public Wall placeNewWall() {
		return new RightWall(Constants.ID_RIGHT_WALL, myX, myY, myHeight,
				myWidth, myMagnitude, myExponent);
	}

}
