package physics;

import java.awt.Dimension;
import java.util.Scanner;
import SpringAndMass.Mass;

import physics.Vector;

public class WallRepulsion extends Force{
	
	public static final int TOP = 1;
	public static final int RIGHT = 2;
	public static final int BOTTOM = 3;
	public static final int LEFT = 4;
	
	private double angle = 0;
	private int wallID;

	public WallRepulsion (int id, double mag, double exp){
		super(mag,exp);
		wallID = id;
		angle = getAngle();
	}

	private double getAngle() {
		
		switch(wallID) {
		case TOP:
				angle = 90;
				break;
		case RIGHT:
				angle = 180;
				break;
		case LEFT:
				angle = 0;
				break;
		case BOTTOM:
				angle = 270;
				break;
		default:
				break;
		}
		return angle;
	}
	
	 private double getDistance (Mass m, Dimension bounds) {
	        double dis = 0;
	        switch (wallID) {
	            case TOP:
	                dis = m.getY();
	                break;
	            case RIGHT:
	                dis = bounds.getWidth() - m.getX();
	                break;
	            case LEFT:
	                dis = m.getX();
	                break;
	            case BOTTOM:
	                dis = bounds.getHeight() - m.getY();
	                break;
	            default:
	                break;
	        }
	        return dis;
	    }
	 
	 public Vector deriveForce (Mass m, Dimension bounds) {
		 double dis = getDistance(m, bounds);
		 return super.deriveForce(angle, dis);
	 }
	 
	 public static WallRepulsion createWallRepulsion (Scanner s){
		 int id_wall = s.nextInt();
		 double mag = s.nextDouble();
		 double exp = s.nextDouble();
		 return new WallRepulsion(id_wall, mag, exp);
	 }

	
}
