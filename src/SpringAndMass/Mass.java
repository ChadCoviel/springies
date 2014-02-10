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
			
	//height and width
	private double myHeight,myWidth;
	
	//constructor for our Mass
<<<<<<< HEAD
	public Mass(int collisionId, double xCoord, double yCoord, double mass) {
		super("mass", collisionId, JGColor.black, 1, 1, mass);
		
=======
	public Mass(String id, double xCoord, double yCoord, 
			double mass,double xVelocity,double yVelocity) {
		super(id, 1, JGColor.black, 1.0, 1.0, mass);

>>>>>>> d4cf575608809a151e86956545da180b163849f0
		//Set Mass position
		this.x = xCoord;
		this.y = yCoord;
				
		//Default: hook point defined as the midsections of the
		//top of the mass
		hookX = x;
<<<<<<< HEAD
		hookY = y+1/2;
=======
//		hookY = y+height/2.0;
>>>>>>> d4cf575608809a151e86956545da180b163849f0
	}
	
	//Method to attach the mass to the spring
	public void attachToSpring(double springHookX,double springHookY){
		x = springHookX;
		y = springHookY+1/2;
	}
	
<<<<<<< HEAD
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
=======
	@Override
	public void move(){
		//Allows the user to grab the mass object
		while(myEngine.getMouseButton(1) & myEngine.getMouseX() <= this.x + myHeight
				& myEngine.getMouseX() >= this.x - myHeight & myEngine.getMouseY() >= this.y - myHeight
				& myEngine.getMouseY() <= this.y + myHeight ){
			this.x = myEngine.getMouseX();
			this.y = myEngine.getMouseY();
>>>>>>> d4cf575608809a151e86956545da180b163849f0
		}
	}
}
