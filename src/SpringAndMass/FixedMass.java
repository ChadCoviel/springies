package SpringAndMass;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

public class FixedMass extends Mass {

	//Fixed mass constructor
<<<<<<< HEAD

	
	
	public FixedMass(int id, double x, double y, double mass) {
		
		// TODO Auto-generated constructor stub
=======
	public FixedMass(String id, double xCoord, 
			double yCoord) {
		//A fixed mass has no speed. It does not move.
		//Furthermore it has no mass since an object with
		//mass can be moved with enough force. 
		super(id, xCoord, yCoord, 0, 0, 0);
>>>>>>> d4cf575608809a151e86956545da180b163849f0
		
		super(id,x,y,mass);
	}

	public void paint (Graphics2D pen) {
		
		pen.setColor(Color.BLACK);
        pen.fillRect((int) getLeft(), (int) getTop(), (int) getWidth(), (int) getHeight());
		
	}
	
	//public void update
	//public void applyForce
	
}
