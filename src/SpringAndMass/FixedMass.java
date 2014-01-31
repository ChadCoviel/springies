package SpringAndMass;

public class FixedMass extends Mass {

	//Fixed mass constructor
	public FixedMass(int collisionId, double xCoord, 
			double yCoord) {
		//A fixed mass has no speed. It does not move.
		super(collisionId, 1.0, 1.0, xCoord, yCoord, 0, 0, 0);
		
		x = xCoord;
		y = yCoord;
	}
	
	

}
