package springies;

import java.util.Collection;

import jboxGlue.*;
import jgame.platform.JGEngine;
import ourObjects.*;

@SuppressWarnings("serial")
public class UserControl extends JGEngine{
	
	
	private Mass closestMass, clickMass;
	private Spring clickSpring;
	private boolean isClicked = false;

	
	/**
	 * With a user click, create a temporary mass at the click point. This new mass is connected to the closest
	 * mass on a nearby assembly via a new spring. Using the mouse, the user can drag the new mass, and 
	 * spring-connected assembly around the screen.
	 */
	
	public void checkMouseClick() {
		if (!isClicked && getMouseButton(1)) {
			isClicked = true;
			int mouseX = getMouseX(), mouseY = getMouseY();
			closestMass = findClosestMass(mouseX, mouseY);
			clickMass = new Mass("mouseMass", mouseX, mouseY, 0);
			clickSpring = new Spring("mouseSpring", clickMass, closestMass);
		} else if (isClicked && !getMouseButton(1)) {
			isClicked = false;
			clickMass.remove();
			clickMass = null;
			clickSpring.remove();
			clickSpring = null;
		}
		if (isClicked) {
			clickMass.setPos(getMouseX(), getMouseY());
		}
	}	
	
	/**
	 * Use the up and down arrow keys on to increase or decrease the size of the area (respectively)
	 * enclosed by the walls of the simulation.
	 */
	
	public void changeWallSize() {
		if (getKey(KeyUp)) {
			clearKey(KeyUp);
			WorldManager.getWorld()
					.repositionWalls(Constants.PIXELS_FOR_SIZE_CHANGE);
		} else if (getKey(KeyDown)) {
			clearKey(KeyDown);
			WorldManager.getWorld().repositionWalls(
					-Constants.PIXELS_FOR_SIZE_CHANGE);
		}
	}
	
	
	/**
	 * Discover the closest mass using the distance formula. Check every mass on all assemblies on-screen.
	 * */
	
	private Mass findClosestMass(int mX, int mY) {
		Collection<Mass> masses = WorldManager.getWorld().getMasses();
		double minimumDistance = Integer.MAX_VALUE; 
		Mass closestMass = null;
		for (Mass m : masses) {
			if (Math.pow(mX - m.getX(), 2) + Math.pow(mY - m.getY(), 2) < minimumDistance) {
				minimumDistance = Math.pow(mX - m.getX(), 2)
						+ Math.pow(mY - m.getY(), 2);
				closestMass = m;
			}
		}
		return closestMass;
	}
	
	/**
	 * Press the appropriate key to enact forces in the simulation.
	 * */
	
	public void toggleForcesKeys() {
		// Center Of Mass Force
		if (getKey('M')) {
			clearKey('M');
			WorldManager.getWorld().valueCOMScalar();
		}
		
		// Gravity
		if (getKey('G')) {
			clearKey('G');
			WorldManager.getWorld().valueGravityScalar();
		}

		// Viscosity
		if (getKey('V')) {
			clearKey('V');
			WorldManager.getWorld().valueViscosityScalar();
		}

		// Wall Repulsion Force
		if (getKey('1')) {
			clearKey('1');
			WorldManager.getWorld().toggleWallForceCalc(Constants.ID_TOP_WALL);
		}
		if (getKey('2')) {
			clearKey('2');
			WorldManager.getWorld().toggleWallForceCalc(Constants.ID_RIGHT_WALL);
		}
		if (getKey('3')) {
			clearKey('3');
			WorldManager.getWorld().toggleWallForceCalc(Constants.ID_BOTTOM_WALL);
		}
		if (getKey('4')) {
			clearKey('4');
			WorldManager.getWorld().toggleWallForceCalc(Constants.ID_LEFT_WALL);
		}
	}
	
	/**
	 * JGEngine methods to be implemented in Springies.java.
	 * */
	
	@Override
	public void initCanvas() {}

	@Override
	public void initGame() {}

}
