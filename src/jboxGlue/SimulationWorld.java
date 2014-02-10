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
import walls.WallReposition;

/**
 * The core of the code. Application of forces to the simulation. Clearing the simulation. Setting the simulation area.
 * A few methods to be utilized for user control code.
 * 
 */

public class SimulationWorld extends World {
	private Collection<Mass> massList;
	private Collection<Spring> springList;
	private Collection<Wall> wallList;
	private double viscosity = Constants.DEFAULT_VISCOSITY,
					com_magnitude = Constants.DEFAULT_COM_MAGNITUDE,
					com_exponent = Constants.DEFAULT_COM_EXPONENT;
	private float visc_multiplier = Constants.VISCOSITY_SCALAR,
					g_multiplier = Constants.GRAVITY_SCALAR,
					com_multiplier = Constants.COM_SCALAR,
					w_multiplier = Constants.WALL_SCALAR;
	private Vec2 gravity = new Vec2(0, 0);

	public SimulationWorld(AABB worldBounds, Vec2 gravity, boolean doSleep) {
		super(worldBounds, gravity, doSleep);
		massList = new ArrayList<Mass>();
		springList = new ArrayList<Spring>();
	}
	
	public void addMasses(Collection<Mass> masses) {
		massList.addAll(masses);
	}

	public void addSprings(Collection<Spring> springs) {
		springList.addAll(springs);
	}

	public void setGravity(Vec2 g) {
		gravity = g;
	}
	
	public void setViscosity(double v) {
		viscosity = v;
	}

	public void setCenterOfMass(double magnitude, double exponent) {
		com_magnitude = magnitude;
		com_exponent = exponent;
	}

	public void setWalls(Collection<Wall> walls) {
		wallList = walls;
	}
	
	public void setWallMultiplier(float multiplier) {
		w_multiplier = multiplier;
	}
	
	/*Value comparison for the gravity, viscosity and center of mass multipliers.*/

	public void valueCOMMultiplier() {
		com_multiplier = (com_multiplier == 0 ? Constants.COM_SCALAR : 0);
	}

	public void valueGravityMultiplier() {
		g_multiplier = (g_multiplier == 0 ? Constants.GRAVITY_SCALAR : 0);
	}

	public void valueViscosityMultiplier() {
		visc_multiplier = (visc_multiplier == 0 ? Constants.VISCOSITY_SCALAR : 0);
	}

	/**
	 * Pressed key initiates calculation of respective wall repulsion force.
	 */
	
	public void toggleWallForceCalc(String id) {
		for (Wall w : wallList) {
			if (w.getId().equals(id)) {
				w.toggleWallStatus();
			}
		}
	}

	public Collection<Wall> getWalls() {
		return wallList;
	}
	
	public Collection<Mass> getMasses() {
		return massList;
	}

	public float getCOMMultiplier() {
		return com_multiplier;
	}

	public float getGravityMultiplier() {
		return g_multiplier;
	}

	public float getViscosityMultiplier() {
		return visc_multiplier;
	}

	public float getWallMultiplier() {
		return w_multiplier;
	}
	
	public void applyForces() {
		applyGravity();
		applyViscosity();
		applyCenterOfMass();
		applyWallRepulsionForce();
	}

	
	private void applyGravity() {
		for (Mass m : massList) {
			m.getBody()
					.applyForce(forceGravity(m), m.getBody().getPosition());
		}
	}
	
	/**
	 * Gravity logic derived from F=m*a. The constant 'a' is not 
	 * 9.8 meters per second per second. It is an arbitrary .01f, for simulation purposes.
	 */
	
	private Vec2 forceGravity(Mass m) {
		float mass = m.getBody().getMass();
		return new Vec2(gravity.x * mass * g_multiplier, gravity.y * mass
				* g_multiplier);
	}

	/**
	 * Center of Mass (COM) is a force that pulls all objects towards the
	 * collective center of mass. 
	 * Find the location of the center of mass, and apply a force that directs
	 * the masses toward that location.
	 */
	
	private void applyCenterOfMass() {
		Vec2 comLocation = getCOMLocation();
		for (Mass m : massList) {
			Vec2 massLocation = m.getBody().getPosition();
			Vec2 unitVec = Vectors.unitVector(massLocation, comLocation);
			double dist = Math.sqrt(Math.pow(massLocation.x - comLocation.x, 2)
					+ Math.pow(massLocation.y - comLocation.y, 2));
			double scalar = Math.pow(1.0 / dist, com_exponent) * com_magnitude;
			Vec2 force = new Vec2((float) (unitVec.x * scalar),
					(float) (unitVec.y * scalar));
			m.getBody().applyForce(force, massLocation);
		}
	}

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

	
	/**
	 * Viscosity is a resistive force, and will oppose the object's direction of motion.
	 * Take the object's velocity vector, reverse it and apply it to the object.
	 */
	
	private void applyViscosity() {
		for (Mass m : massList) {
			Vec2 direction = m.getBody().getLinearVelocity();
			Vec2 oppositeDirection = direction.negate(); 
			float newX = (float) (oppositeDirection.x * viscosity * visc_multiplier);
			float newY = (float) (oppositeDirection.y * viscosity * visc_multiplier);
			Vec2 vForce = new Vec2(newX, newY);
			m.getBody().applyForce(vForce, m.getBody().getPosition());	
		}
	}

	/**
	 * Force from a wall is constant along its length.
	 * Add up the individual wall repulsion forces on a mass and apply the net force.
	 * 
	 */
	
	private void applyWallRepulsionForce() {
		for (Mass m : massList) {
			float xComponent = 0;
			float yComponent = 0;
			for (Wall w : wallList) {
				if (w.isLeftOrRightWall()) {
					if (w.getCollisionStatus()) {
						xComponent += wallRepulsionForce(m, w);
					}
				} else {
					if (w.getCollisionStatus()) {
						yComponent += wallRepulsionForce(m, w);
					}
				}
			}
			m.getBody().applyForce(new Vec2(xComponent, yComponent),
					m.getBody().getPosition());
		}
	}

	private float wallRepulsionForce(Mass m, Wall w) {
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

		return (float) (sign * w_multiplier
				* Math.pow(1 / distance, w.getExponent()) * w.getMagnitude());
	}

	/**
	 * Clears all assemblies
	 */
	public void clearObjects() {
		for (Spring s : springList) {
			s.remove();
		}
		for (Mass m : massList) {
			m.remove();
		}
		springList.clear();
		massList.clear();
	}

	/**
	 * 
	 * Pixel amount to change the wall position by is the parameter.
	 */
	public void repositionWalls(double amountPixels) {
		Collection<WallReposition> eggs = new ArrayList<WallReposition>();
		for (Wall w : wallList) {
			eggs.add(w.newWall(amountPixels));
			w.remove();
		}
		Collection<Wall> newWalls = new ArrayList<Wall>();
		for (WallReposition e : eggs) {
			newWalls.add(e.placeNewWall());
		}
		wallList = newWalls;
	}
	
}
