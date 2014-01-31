package SpringAndMass;

import jboxGlue.PhysicalObject;
import jgame.JGColor;

public class Spring extends PhysicalObject{

	//Hook points
	private double hookXTop,hookYTop;
	private double hookXBottom,hookYBottom;
	
	//Represents the K constant that is a property of springs
	private double springyness;
	
	private int numZigs;
	
	public Spring(String mass1, String mass2,int restLength,
			double K) {
		super("spring", 1, JGColor.black);
		
		//Springyness constant
		springyness = K;
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
		
	}
	
	//This method will allow us to stretch the string
	public void stringStretch(){
		
	}
	
}
