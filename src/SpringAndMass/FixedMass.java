package SpringAndMass;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

public class FixedMass extends Mass {

	//Fixed mass constructor

	
	
	public FixedMass(int id, double x, double y, double mass) {
		
		// TODO Auto-generated constructor stub
		
		super(id,x,y,mass);
	}

	public void paint (Graphics2D pen) {
		
		pen.setColor(Color.BLACK);
        pen.fillRect((int) getLeft(), (int) getTop(), (int) getWidth(), (int) getHeight());
		
	}
	
	//public void update
	//public void applyForce
	
	

}
