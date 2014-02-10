package walls;

import ourObjects.Constants;

public class NewLeftWall extends WallReposition {

	public NewLeftWall(double x, double y, double len, double thick,
			double mag, double exp) {
		super(x, y, len, thick, mag, exp);
	}

	@Override
	public Wall placeNewWall() {
		return new LeftWall(Constants.ID_LEFT_WALL, myX, myY, myHeight,
				myWidth, myMagnitude, myExponent);
	}

}
