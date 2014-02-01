package SpringAndMass;

public class FixedMass extends Mass {

	//Fixed mass constructor
	public FixedMass(String id, double xCoord, 
			double yCoord) {
		//A fixed mass has no speed. It does not move.
		super(id, xCoord, yCoord, 0, 0, 0);
		
		x = xCoord;
		y = yCoord;
	}
	
	

}
