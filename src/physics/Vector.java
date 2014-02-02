package physics;

public class Vector {

	private double angle;
	private double magnitude;

	public Vector(){
		this(0,0);
	}

	public Vector (double ang, double mag){
		setAngle(ang);
		setMagnitude(mag);
	}

	public void reset() {
		setAngle(0);
		setMagnitude(0);
	}

	protected void setAngle(double val){
		angle = val;
	}

	protected void setMagnitude(double val){
		magnitude = val;
	}

	public void scale (double amount){
		setMagnitude(magnitude*amount);
	}

}