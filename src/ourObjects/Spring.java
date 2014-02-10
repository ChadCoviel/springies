package ourObjects;

import org.jbox2d.common.Vec2;

import jboxGlue.PhysicalObject;
import jboxGlue.Vectors;

/**
 * Spring will apply force according to Hooke's Law to each mass on end.
 * Rest length is calculated and spring constant can be parsed from a .xml or set. 
 */

public class Spring extends PhysicalObject {
	private double restLength, springConstant;
	private Mass mass1, mass2;
	private boolean notOnScreen;

	public Spring(String engineId, Mass m1, Mass m2) {
		super(engineId, Constants.CID_SPRING, Constants.COLOR_SPRING);
		restLength = distance(m1.getX(), m1.getY(), m2.getX(), m2.getY());
		springConstant = Constants.DEFAULT_SPRING_CONSTANT;
		mass1 = m1;
		mass2 = m2;
		notOnScreen = false;
	}

	/*
	 * A few set methods
	 * */
	
	public void setRestLength(double rl) {
		restLength = rl;
	}

	public void setConstant(double constant) {
		springConstant = constant;
	}

	/*
	 * A few get methods.
	 * */

	public double getRestLength() {
		return restLength;
	}

	public Mass getMass1() {
		return mass1;
	}

	public Mass getMass2() {
		return mass2;
	}
	
	/*
	 * Methods related to spring's presence on screen.
	 * */
	
	@Override
	public void destroy() {
		notOnScreen = true;
	}

	public void move() {
		if (notOnScreen) {
			return;
		}
		applyHookesForce();
	}
	
	/**
	 * Calculate the distance between points.
	 * */
	
	private static double distance(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}
	
	/**
	 * Use the distance formula for returning the instantaneous length.
	 * */
	
	private double getCurrentLength() {
		return distance(mass1.getX(), mass1.getY(), mass2.getX(),
				mass2.getY());
	}

	/**
	 * F=kx (Hooke's Law) to calculate force applied on each mass at the end of the spring.
	 */
	private void applyHookesForce() {
		
		Vec2 unitVec = Vectors.unitVector(mass1.getBody().getPosition(),
				mass2.getBody().getPosition());

		float calculatedForce = Constants.SPRING_SCALAR
				* (float) ((getCurrentLength() - restLength) * springConstant);

		mass1.getBody().applyForce(new Vec2(unitVec.x * calculatedForce, unitVec.y * calculatedForce),
				mass1.getBody().getPosition());
		mass2.getBody().applyForce(
				new Vec2(-unitVec.x * calculatedForce, -unitVec.y * calculatedForce),
				mass2.getBody().getPosition());
	}

	/**
	 * Represent the spring as a line between the two masses.
	 */
	@Override
	public void paintShape() {
		if (notOnScreen) {
			return;
		}
		myEngine.setColor(myColor);
		Vec2 point1 = mass1.getBody().getPosition();
		Vec2 point2 = mass2.getBody().getPosition();
		myEngine.drawLine(point1.x, point1.y, point2.x, point2.y);
	}

}
