package jboxGlue;

import jgame.platform.JGEngine;

import org.jbox2d.collision.AABB;
import org.jbox2d.common.Vec2;

import ourObjects.Constants;

/**
 * Returns an instance of SimulationWorld.
 * 
 * @author Pritam, Chad
 * 
 */

public class WorldManager {
	public static SimulationWorld theWorld;

	static {
		theWorld = null;
	}

	public static SimulationWorld getWorld() {
		// make sure we have a world, just in case...
		if (theWorld == null) {
			throw new Error("call initWorld() before you call getWorld()!");
		}

		return theWorld;
	}

	public static void initWorld(JGEngine engine) {
		AABB worldBounds = new AABB(new Vec2(0, 0), new Vec2(
				engine.displayWidth(), engine.displayHeight()));
		Vec2 gravity = Constants.DEFAULT_GRAVITY;
		theWorld = new SimulationWorld(worldBounds, gravity, true);
	}
}
