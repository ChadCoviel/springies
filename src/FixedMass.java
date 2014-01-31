
public class FixedMass extends Mass {

	//Very large mass for this object so that it cannot be moved
	private static final double HUGE_MASS = 1000000000;
	
	//Fixed mass constructor
	public FixedMass(int collisionId, double xCoord, 
			double yCoord) {
		//A fixed mass has no speed. It does not move.
		super(collisionId, 1.0, 1.0, xCoord, yCoord, HUGE_MASS, 0, 0);
		
		x = xCoord;
		y = yCoord;
	}

}
