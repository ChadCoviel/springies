import jboxGlue.PhysicalObject;
import jgame.JGColor;

public class Spring extends PhysicalObject{

	//Hook points
	private double hookXTop,hookYTop;
	private double hookXBottom,hookYBottom;
	
	private int numZigs;
	
	protected Spring(String name, int collisionId, JGColor color,int zigs) {
		super(name, collisionId, color);
		// TODO Auto-generated constructor stub
		numZigs = zigs;
	}

	@Override
	protected void paintShape() {
		// TODO Auto-generated method stub
		
	}
	
}
