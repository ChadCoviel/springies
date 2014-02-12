package springies;

import javax.swing.JOptionPane;

import jboxGlue.*;

/**
 * 
 * Sets up the "canvas" and calls on WorldManager.
 * 
 * Parses the .xml file using the Parser class, and provides a starting simulation.
 * 
 * Runs the code for forces, object movement and user control in each frame of the simulation.
 * 
 */

@SuppressWarnings("serial")
public class Springies extends UserControl implements Frames {
	private static Parser myParser;
	private boolean gravityOn = false;
	private boolean viscosityOn = false;
	private boolean centerOfMassOn = false;

	public Springies() {
		// set the window size
		int height = 480;
		double aspect = 16.0 / 9.0;
		initEngine((int) (height * aspect), height);
	}

	@Override
	public void initCanvas() {
		// I have no idea what tiles do...
		setCanvasSettings(1, // width of the canvas in tiles
				1, // height of the canvas in tiles
				displayWidth(), // width of one tile
				displayHeight(), // height of one tile
				null,// foreground colour -> use default colour white
				null,// background colour -> use default colour black
				null // standard font -> use default font
		);
	}

	/**
	 * Parser parses the given environment and assembly XML files.
	 */
	@Override
	public void initGame() {
		setFrameRate(60, 2);
		WorldManager.initWorld(this);
		myParser = new Parser(displayWidth(), displayHeight());
		myParser.parseEnvironmentXML("assets/environment.xml");
		myParser.parseObjectsForAssembly("assets/jello.xml");
	}

	/**
	 * Apply all environmental forces and check for user input.
	 */
	@Override
	public void doFrame() {
		if(getKey('V'))
			viscosityOn = !viscosityOn;
		if(getKey('G'))
			gravityOn = !gravityOn;
		if(getKey('M'))
			centerOfMassOn = !centerOfMassOn;
		WorldManager.getWorld().step(1f, 1);
		WorldManager.getWorld().applyForces();
		checkMouseClick();
		toggleForcesKeys();
		toggleMuscleAmplitude(myParser);
		changeWallSize();
		moveObjects();
		clearOrAddAssemblies();
	}
	
	/**
	 * Press the appropriate key to clear or load an assembly.
	 * */

	private void clearOrAddAssemblies() {
		if (getKey('C')) {
			clearKey('C');
			WorldManager.getWorld().clearObjects();
		} else if (getKey('N')) {
			clearKey('N');
			String file = JOptionPane
					.showInputDialog("To add an assembly, type the file name without the '.xml' ending.");
			myParser.parseObjectsForAssembly("assets/" + file + ".xml");
		}
	}
	
	@Override
	public void paintFrame() {
		drawString("viscosity:"+viscosityOn,20, 20, -1);
		drawString("gravity:"+gravityOn,20, 40, -1);
		drawString("center of mass:"+centerOfMassOn,20, 60, -1);
	}
}
