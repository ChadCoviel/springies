package jboxGlue;

import java.util.ArrayList;
import java.util.Collection;

import org.jbox2d.collision.AABB;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import ourObjects.Constants;
import ourObjects.Mass;
import ourObjects.Spring;
import walls.Wall;


public class Forces extends World{
	
	public Collection<Mass> massList;
	public Collection<Spring> springList;
	
	public double viscosity = Constants.DEFAULT_VISCOSITY,
					com_magnitude = Constants.DEFAULT_COM_MAGNITUDE,
					com_exponent = Constants.DEFAULT_COM_EXPONENT;
	public float visc_scalar = Constants.VISCOSITY_SCALAR,
					g_scalar = Constants.GRAVITY_SCALAR,
					com_scalar = Constants.COM_SCALAR,
					w_scalar = Constants.WALL_SCALAR;
	public Vec2 gravity = new Vec2(0, 0);

	public Forces(AABB worldAABB, Vec2 gravity, boolean doSleep) {
		super(worldAABB, gravity, doSleep);
		massList = new ArrayList<Mass>();
		springList = new ArrayList<Spring>();
	}
	
	/**
	 * Populate the collections.
	 * */
	
	public void addMasses(Collection<Mass> masses) {
		massList.addAll(masses);
	}

	public void addSprings(Collection<Spring> springs) {
		springList.addAll(springs);
	}
	
	
	/**
	 * Simple set methods.
	 * */
	
	public void setGravity(Vec2 g) {
		gravity = g;
	}

	public void setViscosity(double vis) {
		viscosity = vis;
	}

	public void setCenterOfMass(double magnitude, double exponent) {
		com_magnitude = magnitude;
		com_exponent = exponent;
	}
	
	/*Value comparison for the gravity, viscosity and center of mass multipliers.*/
	
	public void valueCOMScalar() {
		com_scalar = (com_scalar == 0 ? Constants.COM_SCALAR : 0);
	}

	public void valueGravityScalar() {
		g_scalar = (g_scalar == 0 ? Constants.GRAVITY_SCALAR : 0);
	}

	public void valueViscosityScalar() {
		visc_scalar = (visc_scalar == 0 ? Constants.VISCOSITY_SCALAR : 0);
	}
	
	/**
	 * Gravity logic derived from F=m*a. The constant 'a' is not 
	 * 9.8 meters per second per second. It is an arbitrary .01f, for simulation purposes.
	 */
	
	public Vec2 forceGravity(Mass m) {
		float mass = m.getBody().getMass();
		return new Vec2(gravity.x * mass * g_scalar, gravity.y * mass
				* g_scalar);
	}
	
	/**
	 * Center of Mass (COM) is a force that pulls all objects towards the
	 * collective center of mass. 
	 * Find the location of the center of mass, and apply a force that directs
	 * the masses toward that location.
	 */
	
	public Vec2 forceCenterOfMass(Mass m) {
		
		Vec2 comLocation = getCOMLocation();
	
		Vec2 massLocation = m.getBody().getPosition();
		Vec2 unitVec = Vectors.unitVector(massLocation, comLocation);
		double distance = Math.sqrt(Math.pow(massLocation.x - comLocation.x, 2)
				+ Math.pow(massLocation.y - comLocation.y, 2));
		double scalar = Math.pow(1.0 / distance, com_exponent) * com_magnitude;
	
	return new Vec2((float) (unitVec.x * scalar), (float) (unitVec.y * scalar));
	}
	
	/**
	 * Viscosity is a resistive force, and will oppose the object's direction of motion.
	 * Take the object's velocity vector, reverse it and apply it to the object.
	 */
	
	public Vec2 forceViscosity(Mass m) {
		
		Vec2 direction = m.getBody().getLinearVelocity();
		Vec2 oppositeDirection = direction.negate(); 
		float newX = (float) (oppositeDirection.x * viscosity * visc_scalar);
		float newY = (float) (oppositeDirection.y * viscosity * visc_scalar);
		return new Vec2(newX, newY);
	
	}
	
	/**
	 * Force from a wall is constant along its length.
	 * Add up the individual wall repulsion forces on a mass and apply the net force.
	 * 
	 */
	
	public float forceWallRepulsion(Mass m, Wall w) {
		float distance;
		int sign;

		if (w.isLeftOrRightWall()) {
			distance = Math.abs(w.getBody().getPosition().x
					- m.getBody().getPosition().x);
			sign = (w.getBody().getPosition().x < m.getBody().getPosition().x ? 1
					: -1);
		} else {
			distance = Math.abs(w.getBody().getPosition().y
					- m.getBody().getPosition().y);
			sign = (w.getBody().getPosition().y < m.getBody().getPosition().y ? 1
					: -1);
		}

		return (float) (sign * w_scalar
				* Math.pow(1 / distance, w.getExponent()) * w.getMagnitude());
	}
	
	/**
	 * Methods returns the location of center of mass.
	 * */
	
	private Vec2 getCOMLocation() {
		float sumXCoordinates = 0, sumYCoordinates = 0, totalMass = 0;
			for (Mass m : massList) {
				sumXCoordinates += m.getBody().getPosition().x * m.getBody().getMass();
				sumYCoordinates += m.getBody().getPosition().y * m.getBody().getMass();
				totalMass += m.getBody().getMass();
		}
		float avgX = sumXCoordinates / totalMass;
		float avgY = sumYCoordinates / totalMass;
		return new Vec2(avgX, avgY);
	}

	

}
