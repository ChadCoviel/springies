package SpringAndMass;

import org.jbox2d.common.Vec2;

import jboxGlue.PhysicalObject;
import jgame.JGColor;

public class Spring extends PhysicalObject{

	//Hook points
	private double hookXTop,hookYTop;
	private double hookXBottom,hookYBottom;
	
	//Represents the K constant that is a property of springs
	private double springyness;
	
	private double restLength;
	
	private int numZigs;
	
	public Spring(String mass1, String mass2,int restL,
			double K) {
		super("spring", 1, JGColor.black);
		
		//Springyness constant
		springyness = K;
		
		//Define the rest length of the spring
		restLength = restL;
	}
	
	//Default springyness constructor if no value provided for K
	public Spring(String mass1, String mass2,int restLength) {
		super("spring", 1, JGColor.black);
		// TODO Auto-generated constructor stub
		
		springyness = 1;
	}

	//Paint the Spring
	@Override
	protected void paintShape() {
		// TODO Auto-generated method stub
		
	}
	
	//This method will allow us to contract the spring
	public void stringContract(){
		this.myBody.applyForce(new Vec2((float)x, (float)y), myBody.m_xf.position);
	}
	
	//This method will allow us to stretch the string
	public void stringStretch(){
		
	}
	
}
