package physics;

import physics.Vector;
import java.util.Scanner;
import SpringAndMass.Mass;

public class Gravity {

	private static final double ANGLE_CONSTANT = 90;
	private static final double MAGNITUDE_CONSTANT = 9.8;

	private Vector myGravity;

	public Gravity (double ang, double mag) {
		myGravity = new Vector(ang,mag);
	}

	public Vector forceonMass(Mass m){
		Vector mforce = new Vector(myGravity.getDirection(), myGravity.getMagnitude());
		mforce.scale(m.getMass()); //implemented by Chad
		return mforce;

	}

	public static Gravity createGravity (Scanner s){
		double angles = s.nextDouble();
		double magnitude = s.nextDouble();
		return new Gravity(angles,magnitude);
	}

	public static Gravity basicGravity() {
		double angles = ANGLE_CONSTANT;
		double magnitude = MAGNITUDE_CONSTANT;
		return new Gravity(angles,magnitude);
	}



}