package SpringAndMass;

import org.jbox2d.common.Vec2;

import jboxGlue.PhysicalObject;
import jgame.JGColor;

public class Spring extends PhysicalObject{

	//Hook points
	private double hookXTop,hookYTop;
	private double hookXBottom,hookYBottom;
	
	//The two masses that the spring is connected to
	private Mass m1,m2;
	
	//The string IDs of the masses
	private String m1Id, m2Id;
	
	//Represents the K constant that is a property of springs
	private double springyness;
	
	private double restLength;
	
	public Spring(String mass1, String mass2,int restL,
			double K) {
		super("spring", 1, JGColor.black);
		
		//Refer to the two masses
		m1Id = mass1;
		m2Id = mass2;
		
		//Springyness constant
		springyness = K;
		
		//Define the rest length of the spring
		restLength = restL;
	}
	
	//Default springyness constructor if no value provided for K
	public Spring(String mass1, String mass2,int restLength) {
		super("spring", 1, JGColor.black);

		springyness = 1;
	}

	//Paint the Spring
	@Override
	protected void paintShape() {
		//Paints a line that represents the String
		myEngine.drawLine(m1.x,m1.y,m2.x,m2.y);
	}
	
	//This method will allow us to contract the spring
	public void stringContract(){
		this.myBody.applyForce(new Vec2((float)x, (float)y), myBody.m_xf.position);
	}
	
	//This method will allow us to stretch the string
	public void stringStretch(){
		
	}
	
	@Override
	public void move(){
		//Allows the user to grab the spring object
		if(myEngine.getMouseButton(1)){
			
		}
	}
	
}
