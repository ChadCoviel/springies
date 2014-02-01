package SpringAndMass;

public class FixedMass extends Mass {

	//Fixed mass constructor
	public FixedMass(String id, double xCoord, 
			double yCoord) {
		//A fixed mass has no speed. It does not move.
		//Furthermore it has no mass since an object with
		//mass can be moved with enough force. 
		super(id, xCoord, yCoord, 0, 0, 0);
		
		x = xCoord;
		y = yCoord;
	}
	
}
