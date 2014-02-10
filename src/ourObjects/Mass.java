package ourObjects;

import org.jbox2d.common.Vec2;

import jboxGlue.PhysicalObjectCircle;
import jgame.JGObject;

/**
 * This class is used to represent a fixed mass. The fixed mass is unaffected by forces, therefore
 * we thought it made sense to create a basic class for masses (representing fixed mass) and then create MovingMass
 * which will extend Mass. MovingMass objects are affected by forces.
 */

public class Mass extends PhysicalObjectCircle {

	
	public Mass(String engineId, double x, double y, double mass) {
		super(engineId, Constants.CID_MASS, Constants.COLOR_MASS,
				Constants.MASS_RADIUS, mass);
		setPos(x, y);
		getBody().setLinearVelocity(new Vec2(0, 0));
	}

	public double getX() {
		return getBody().getPosition().x;
	}

	public double getY() {
		return getBody().getPosition().y;
	}

	@Override
	public void paintShape() {
		myEngine.setColor(myColor);
		myEngine.drawOval(getBody().getPosition().x, getBody().getPosition().y,
				Constants.MASS_RADIUS * 2, Constants.MASS_RADIUS * 2, true,
				true);
	}

	@Override
	public void hit(JGObject obj) {}
}
