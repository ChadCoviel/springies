package SpringAndMass;

import java.awt.Dimension;
import java.awt.Graphics2D;


public interface Paint_Update {


	void update (double timeDone, Dimension bounds);


	void paint (Graphics2D pen);

}