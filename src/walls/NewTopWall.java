package walls;

import ourObjects.Constants;

public class NewTopWall extends WallReposition {

	public NewTopWall(double x, double y, double len, double thick, double mag,
			double exp) {
		super(x, y, len, thick, mag, exp);
	}

	@Override
	public Wall placeNewWall() {
		return new TopWall(Constants.ID_TOP_WALL, myX, myY, myHeight,
				myWidth, myMagnitude, myExponent);
	}

}
