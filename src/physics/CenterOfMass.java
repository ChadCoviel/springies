package physics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.List;
import java.util.Scanner;

import SpringAndMass.Paint_Update;
import SpringAndMass.Mass;

import physics.Vector;

public class CenterOfMass extends Force{

	public static final Dimension DEFAULT = new Dimension(10,10);
	private static final double MAGNITUDE_CONSTANT = 10;
	private static final double EXPONENT_CONSTANT = 0.0;
	private Point2D comlocation;

	public CenterOfMass (double mag, double exp){
		super(mag,exp);
		comlocation = new Point2D.Double();

	}

	public void calcCenterOfMass (List<Paint_Update> myObjects) {
		double xcen = 0;
		double ycen = 0;
		double masstotal = 0;

		for(Paint_Update p : myObjects) {
			if (p instanceof Mass) {
				Mass created = (Mass) p;
				double mass = Math.abs(created.getMass());
				xcen += created.getX()*mass;
				ycen += created.getY()*mass;
				masstotal += mass;
			}
		}

		xcen = xcen / masstotal;
		ycen = ycen / masstotal;
		comlocation = new Point2D.Double(xcen,ycen);

	}

	private double getDistance (Mass m){
		return comlocation.distance(m.getX(),m.getY());

	}

	private double getAngle (Mass m){
		double ang = 0;
		double x = comlocation.getX() - m.getX();
		double y = comlocation.getY() - m.getY();
		ang = Vector.angleBetween(x,y);
		return ang;
	}

	public Vector obtainForce (Mass m){
		double ang = getAngle(m);
		double dis = getDistance(m);
		Vector res = super.deriveForce(ang, dis);
		return res;
	}

	public static CenterOfMass createCOM (Scanner s){
		double mag = s.nextDouble();
		double exp = s.nextDouble();
		return new CenterOfMass(mag,exp);
	}

	public static CenterOfMass defaultCOM (){
		double mag = MAGNITUDE_CONSTANT;
		double exp = EXPONENT_CONSTANT;
		return new CenterOfMass(mag,exp);
	}
}