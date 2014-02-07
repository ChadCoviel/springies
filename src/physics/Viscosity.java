package physics;

import java.util.Scanner;
import SpringAndMass.Mass;
import physics.Vector;


public class Viscosity extends Force{

	private static final double MAGNITUDE_CONSTANT = 0.0;

	public Viscosity (double mag) {
		super(mag,0);
	}

	public Vector visconMass (Mass m){
		Vector viscos = new Vector(m.getVelocityAngle(),m.getVelocity());
		viscos.scale(viscos.getMagnitude());
		viscos.opposite();
		return viscos;
	}

	public static Viscosity createViscosity (Scanner s){
		double amount = s.nextDouble();
		return new Viscosity(amount);
	}

	public static Viscosity basicViscosity () {

		double mag = MAGNITUDE_CONSTANT;
		return new Viscosity(mag);
	}

}