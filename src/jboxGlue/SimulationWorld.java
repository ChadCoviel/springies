package jboxGlue;

import java.util.ArrayList;
import java.util.Collection;

import org.jbox2d.collision.AABB;
import org.jbox2d.common.Vec2;


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

public class SimulationWorld extends Forces {
	
	
	private Collection<Wall> wallList;
	private float visc_scalar = Constants.VISCOSITY_SCALAR,
					g_scalar = Constants.GRAVITY_SCALAR,
					com_scalar = Constants.COM_SCALAR,
					w_scalar = Constants.WALL_SCALAR;
	

	public SimulationWorld(AABB worldBounds, Vec2 gravity, boolean doSleep) {
		super(worldBounds, gravity, doSleep);
	}
	
	/*
	 * Basic set and get methods for functionality between classes and other packages.
	 * */
	
	public void setWalls(Collection<Wall> walls) {
		wallList = walls;
	}
	
	public void setWallMultiplier(float multiplier) {
		w_scalar = multiplier;
	}
	
	public Collection<Wall> getWalls() {
		return wallList;
	}
	
	public Collection<Mass> getMasses() {
		return massList;
	}

	public float getCOMScalar() {
		return com_scalar;
	}

	public float getGravityScalar() {
		return g_scalar;
	}

	public float getViscosityScalar() {
		return visc_scalar;
	}

	public float getWallScalar() {
		return w_scalar;
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
	
	/**
	 * Applies all forces necessary for simulation. Force calculations are in the Force class.
	 * */
	
	public void applyForces() {
		applyGravity();
		applyCenterOfMass();
		applyViscosity();
		applyWallRepulsionForce();
	}

	/*
	 * Loop through the massList for the application of each force.
	 * */
	
	private void applyGravity() {
		for (Mass m : massList) {
			m.getBody()
					.applyForce(forceGravity(m), m.getBody().getPosition());
		}
	}
	
	private void applyCenterOfMass() {
	
		for (Mass m : massList) {
			Vec2 massLocation = m.getBody().getPosition();
			Vec2 force = forceCenterOfMass(m);
			m.getBody().applyForce(force, massLocation);
		}
	}
	
	private void applyViscosity() {
		for (Mass m : massList) {
			Vec2 viscosityForce = forceViscosity(m);
			m.getBody().applyForce(viscosityForce, m.getBody().getPosition()); 																	
		}
	}
	
	private void applyWallRepulsionForce() {
		for (Mass m : massList) {
			float xComponent = 0;
			float yComponent = 0;
			for (Wall w : wallList) {
				if (w.isLeftOrRightWall()) {
					if (w.getCollisionStatus()) {
						xComponent += forceWallRepulsion(m, w);
					}
				} else {
					if (w.getCollisionStatus()) {
						yComponent += forceWallRepulsion(m, w);
					}
				}
			}
			m.getBody().applyForce(new Vec2(xComponent, yComponent),
					m.getBody().getPosition());
		}
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
		Collection<WallReposition> movedWallList = new ArrayList<WallReposition>();
		for (Wall w : wallList) {
			movedWallList.add(w.newWall(amountPixels));
			w.remove();
		}
		Collection<Wall> newWalls = new ArrayList<Wall>();
		for (WallReposition n : movedWallList) {
			newWalls.add(n.placeNewWall());
		}
		wallList = newWalls;
	}
	
}
