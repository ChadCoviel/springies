package SpringAndMass;

import java.util.Scanner;

import jboxGlue.PhysicalObjectRect;
import jboxGlue.PhysicalObject;
import jgame.JGColor;

//import package SpringAndMass;

public class Mass extends PhysicalObjectRect{

	//private int myMass;
	//These integers are used to indicate where the mass is connected
	//to the spring (ie hook points)
	private double hookX,hookY;
			
	//height and width
	private double myHeight,myWidth;
	
	//constructor for our Mass
	public Mass(int collisionId, double xCoord, double yCoord, double mass) {
		super("mass", collisionId, JGColor.black, width, height, mass);
		
		//Height and width of mass
		myHeight = height;
		myWidth = width;
		
		//Set Mass position
		this.x = xCoord;
		this.y = yCoord;
		
		//Set initial velocities
		this.xspeed = xVelocity;
		this.yspeed = yVelocity;
		
		//Default: hook point defined as the midsections of the
		//top of the mass
		hookX = x;
		hookY = y+height/2.0;
	}
	
	//Method to attach the mass to the spring
	public void attachToSpring(double springHookX,double springHookY){
		x = springHookX;
		y = springHookY+myHeight/2;
	}
	
	public static Mass createMass (Scanner xmltext){
		int id=xmltext.nextInt();
		double x=xmltext.nextDouble();
		double y=xmltext.nextDouble();
		double mass = xmltext.nextDouble();
		Mass created;
		if(mass<0){
			created = new FixedMass(id,x,y,mass);
		} else {
			created = new Mass(id,x,y,mass);
		}
		return created;
	}
	
	
	
	
	
}
