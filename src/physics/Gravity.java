package physics;

import java.util.Scanner;
import SpringAndMass.Mass;

public class Gravity {

	private static final double angle_constant = 90;
	private static final double magnitude_constant = 9.8;

	private Vector myGravity;

	public Gravity (double ang, double mag) {
		myGravity = new Vector(ang,mag);
	}

	public Vector forceonMass(Mass m){
		Vector mforce = new Vector(myGravity.getDirection(), getMagnitude());
		mforce.scale(m.getMass());
		return mforce;
	}

	public static Gravity createGravity (Scanner s){
		double angles = s.nextDouble();
		double magnitude = s.nextDouble();
		return new Gravity(angles,magnitude);
	}



}