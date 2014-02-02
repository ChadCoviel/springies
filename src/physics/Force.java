package physics;

public abstract class Force {
	
	private static final int LENGTH = 50;
	private double magnitude;
	private double exponent;

	public Force (double mag, double exp){
		magnitude = mag;
		exponent = exp;
	}
	
	public Vector deriveForce (double ang, double distance) {
		Vector derived = new Vector(ang,magnitude);
		double scaling = 1 / (Math.pow(distance/LENGTH,exponent));
		derived.scale(scaling);
		return derived;
	}
	
}
