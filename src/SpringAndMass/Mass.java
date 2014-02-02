package SpringAndMass;

import jboxGlue.PhysicalObjectRect;
import jboxGlue.PhysicalObject;
import jgame.JGColor;

//import package SpringAndMass;

public class Mass extends PhysicalObjectRect{

	//private int myMass;
	//These integers are used to indicate where the mass is connected
	//to the spring (ie hook points)
	private double hookX,hookY;
	
	//This object's mass
	private double m;
			
	//height and width
	private double myHeight,myWidth;
	
	//constructor for our Mass
	public Mass(String id, double xCoord, double yCoord, 
			double mass,double xVelocity,double yVelocity) {
		super(id, 1, JGColor.black, 1.0, 1.0, mass);

		m = mass;
		
		//Set Mass position
		this.x = xCoord;
		this.y = yCoord;
		
		//Set initial velocities
		this.xspeed = xVelocity;
		this.yspeed = yVelocity;
		
		//Default: hook point defined as the midsections of the
		//top of the mass
		hookX = x;
//		hookY = y+height/2.0;
	}
	
	//Method to attach the mass to the spring
	public void attachToSpring(double springHookX,double springHookY){
		x = springHookX;
		y = springHookY+1/2;
	}
	
	//Return the mass of the mass
	public double getMass(){
		return m;
	}
	
	//Return the x coordinate of this mass
	public double getX(){
		return x;
	}
	
	//Return the y coordinate of this mass
	public double getY(){
		return y;
	}
	
	//Return the x direction velocity
	public double getXVelocity(){
		return xspeed;
	}
	
	//Return the y direction velocity
	public double getYVelocity(){
		return yspeed;
	}
	
	@Override
	public void move(){
		//Allows the user to grab the mass object
		while(myEngine.getMouseButton(1) & myEngine.getMouseX() <= this.x + myHeight
				& myEngine.getMouseX() >= this.x - myHeight & myEngine.getMouseY() >= this.y - myHeight
				& myEngine.getMouseY() <= this.y + myHeight ){
			this.x = myEngine.getMouseX();
			this.y = myEngine.getMouseY();
		}
	}
}
