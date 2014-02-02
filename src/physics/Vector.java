package physics;

import java.awt.geom.Point2D;

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
	
	public double getMagnitude() {
		return magnitude;
	}
	
	 public double getAngle () {
	       
	        final double OFFSET = 0.001;
	        double sign = (angle < 0) ? 1 : -1;
	        return ((angle + sign * OFFSET) % 360) - sign * OFFSET;
	    }

	

	public void opposite() {
		rotate(180);
		
		
	}
	
	public void rotate (double value) {
		setAngle(this.getAngle()+value);
	}


}
