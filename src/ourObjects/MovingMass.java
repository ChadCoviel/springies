package ourObjects;

import jgame.JGObject;

import org.jbox2d.common.Vec2;

/**
 * A Moving Mass can be affected by forces, and has an initial velocity. It will also be involved in
 * collisions due to its movement.
 */

public class MovingMass extends Mass {


	public MovingMass(String engineId, double x, double y, double xSpeed,
			double ySpeed, double mass) {
		super(engineId, x, y, mass);
		setPos(x, y);
		getBody().setLinearVelocity(new Vec2((float) xSpeed, (float) ySpeed));
	}

	/**
	 * If the mass collides with a wall, reverse the velocity with added dampening.
	 */
	@Override
	public void hit(JGObject other) {

		Vec2 velocity = myBody.getLinearVelocity();

		// checking to see if the wall is top/bottom or left/right
		// accordingly affect the velocity
		
		final double dampening = Constants.DAMPING_SCALAR;
		boolean isSide = other.getBBox().height > other.getBBox().width;
		if (isSide) {
			velocity.x *= -dampening;
		} else {
			velocity.y *= -dampening;
		}

		myBody.setLinearVelocity(velocity);
	}
}