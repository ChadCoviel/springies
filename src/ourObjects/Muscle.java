package ourObjects;

/**
 * Muscle extends Spring, because a muscle is a spring with changing rest lengths.
 * 
 */

public class Muscle extends Spring {
	private double amplitude, averageRestLength;
	private int counter;

	/**
	 * Same constructor as for Spring.
	 */
	public Muscle(String engineId, Mass m1, Mass m2) {
		super(engineId, m1, m2);
		counter = 0;
		amplitude = 0;
		averageRestLength = getRestLength();
	}

	public void setAmplitude(double amp) {
		amplitude = amp;
	}

	@Override
	public void move() {
		doSinusoidalMotion();
		super.move();
	}

	public void setAverageRestLength(double rl) {
		averageRestLength = rl;
	}

	/**
	 * Oscillate the rest length with a sine function. Use an arbitrary counter for time (increment it).
	 */
	private void doSinusoidalMotion() {
		counter++;
		setRestLength(averageRestLength + Constants.AMPLITUDE_SCALAR
				* amplitude
				* Math.sin(2 * Math.PI * Constants.MUSCLE_FREQUENCY * counter));
	}
}
