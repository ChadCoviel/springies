import jboxGlue.PhysicalObjectRect;
import jboxGlue.PhysicalObject;
import jgame.JGColor;

public class Mass extends PhysicalObjectRect{

	//private int myMass;
	//These integers are used to indicate where the mass is connected
	//to the spring (ie hook points)
	private double hookX,hookY;
	
	//constructor for our Mass
	public Mass(String id, int collisionId, JGColor color, double width,
			double height, double mass) {
		super(id, collisionId, color, width, height, mass);
        //init(width, height, mass);
		// TODO Auto-generated constructor stub
		
		//Default: hook point defined as the middle and
		//top of the mass
		hookX = width/2.0;
		hookY = height;
	}
	
	//Method to attached the mass to the spring
	public void attachToSpring(){
		
	}
	
	
	
	
	
}
