package SpringAndMass;

import java.awt.Dimension;
import java.util.Map;
import java.util.Scanner;

public class Muscle extends Spring implements Paint_Update{

	private double muscAmp;
	private double muscOriLength;
	private double muscTime;

	public Muscle (Mass begin, Mass finish, double length, double k, double amp) {
		super(begin,finish,length,k);
		muscAmp = amp;
		muscOriLength = length;
		muscTime = 0;
	}

	public void update (double timePassed, Dimension bounds){
		muscTime += timePassed;
		super.update(timePassed, bounds);
	}

	public static Muscle createMuscle (Scanner s, Map<Integer, Mass> massMap){

		Mass one = massMap.get(s.nextInt());
		Mass two = massMap.get(s.nextInt());
		double rest_length = s.nextDouble();
		double kvalue = s.nextDouble();
		double amplitude = s.nextDouble();
		return new Muscle(one,two,rest_length,kvalue,amplitude);
	}

}