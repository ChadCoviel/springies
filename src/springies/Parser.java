package springies;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import jboxGlue.SimulationWorld;
import jboxGlue.WorldManager;

import org.jbox2d.common.Vec2;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ourObjects.Constants;
import ourObjects.Mass;
import ourObjects.MovingMass;
import ourObjects.Muscle;
import ourObjects.Spring;
import walls.BottomWall;
import walls.LeftWall;
import walls.RightWall;
import walls.TopWall;
import walls.Wall;

/**
 * 
 * Parse XML files to create an assembly and obtain environmental forces.
 *
 * 
 */
public class Parser {

	private List<Spring> springList;
	private List<Wall> wallList;
	
	private Map<String, Mass> massMap;
	
	private static final String ID_MASS = "mass", ID_FIXED = "fixed",
			ID_SPRING = "spring", ID_MUSCLE = "muscle", ID_WALL = "wall";
	
	private int parseWidth, parseHeight;

	public Parser(int w, int h) {
		parseWidth = w;
		parseHeight = h;
		springList = new ArrayList<Spring>();
		wallList = new ArrayList<Wall>();
		massMap = new HashMap<String, Mass>();
		
	}
	
	public Collection<Spring> getSpringList() {
		return springList;
	}

	public Collection<Mass> getMassList() {
		return massMap.values();
	}

	/**
	 * Call the parsing of all necessary XML files and setup the simulation by calling on SimulationWorld.
	 */
	public void parseObjectsForAssembly(String path) {
		massMap.clear();
		springList.clear();
		try {
			NodeList root = buildDocument(path);
			NodeList model = getNode("model", root).getChildNodes();
			NodeList nodes = getNode("nodes", model).getChildNodes();
			NodeList links = getNode("links", model).getChildNodes();
			constructMasses(nodes);
			constructSpringsAndMuscles(links);
			WorldManager.getWorld().addMasses(getMassList());
			WorldManager.getWorld().addSprings(getSpringList());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Generate walls and forces from given file (usually environment.xml).
	 */
	
	public void parseEnvironmentXML(String path) {
		SimulationWorld springiesWorld = WorldManager.getWorld();
		try {
			NodeList root = buildDocument(path);
			NodeList environment = getNode("environment", root).getChildNodes();

			
			Node centermass = getNode("centermass", environment);
			double comMag = Double.parseDouble(getNodeAttributes("magnitude",
					centermass));
			double comExp = Double.parseDouble(getNodeAttributes("exponent",
					centermass));
			springiesWorld.setCenterOfMass(comMag, comExp);
			
		
			Node gravity = getNode("gravity", environment);
			double angle = (Double
					.parseDouble(getNodeAttributes("direction", gravity)))
					* Math.PI/ 180;
			double magnitude = Double.parseDouble(getNodeAttributes("magnitude",
					gravity));
			springiesWorld.setGravity(new Vec2((float) (magnitude * Math.cos(angle)),
					(float) (magnitude * Math.sin(angle))));

			
			Node viscosity = getNode("viscosity", environment);
			double visc = Double
					.parseDouble(getNodeAttributes("magnitude", viscosity));
			springiesWorld.setViscosity(visc);

			
			for (int i = 0; i < environment.getLength(); i++) {
				Node curNode = environment.item(i);
				if (curNode.getNodeName().equals(ID_WALL)) {
					String id = getNodeAttributes("id", curNode);
					String strRepulsion = getNodeAttributes("magnitude", curNode);
					String strExponent = getNodeAttributes("exponent", curNode);
					double repulsion = strRepulsion.isEmpty() ? Constants.DEFAULT_WALL_REPULSION
							: Double.parseDouble(strRepulsion);
					double exponent = strExponent.isEmpty() ? Constants.DEFAULT_WALL_EXPONENT
							: Double.parseDouble(strExponent);

					Wall wall = null;

					if (id.equals(Constants.ID_TOP_WALL)) {
						double x = parseWidth / 2, y = Constants.WALL_THICKNESS / 2;
						wall = new TopWall(id, x, y, parseWidth,
								Constants.WALL_THICKNESS, repulsion, exponent);
					} else if (id.equals(Constants.ID_LEFT_WALL)) {
						double x = Constants.WALL_THICKNESS / 2, y = parseHeight / 2;
						wall = new LeftWall(id, x, y, parseHeight
								- Constants.WALL_THICKNESS * 2, 
								Constants.WALL_THICKNESS, repulsion, exponent);
					} else if (id.equals(Constants.ID_BOTTOM_WALL)) {
						double x = parseWidth / 2, y = parseHeight
								- Constants.WALL_THICKNESS / 2;
						wall = new BottomWall(id, x, y, parseWidth,
								Constants.WALL_THICKNESS, repulsion, exponent);
					} else if (id.equals(Constants.ID_RIGHT_WALL)) {
						double x = parseWidth - Constants.WALL_THICKNESS / 2, y = parseHeight / 2;
						wall = new RightWall(id, x, y, parseHeight
								- Constants.WALL_THICKNESS * 2,
								Constants.WALL_THICKNESS, repulsion, exponent);
					}
					if (wall != null) {
						wallList.add(wall);
					}
				}
			}

			WorldManager.getWorld().setWalls(wallList);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Generate the masses.
	 */
	
	private void constructMasses(NodeList nodes) {
		for (int i = 0; i < nodes.getLength(); i++) {
			Node curNode = nodes.item(i);
			String nodeType = curNode.getNodeName();

			if (nodeType.equals(ID_MASS) || nodeType.equals(ID_FIXED)) {
				
				String id = getNodeAttributes("id", curNode);
				double x = Double.parseDouble(getNodeAttributes("x", curNode));
				double y = Double.parseDouble(getNodeAttributes("y", curNode));
				Vec2 positionXY = checkForOffScreenAndCorrect(x, y);
				x = positionXY.x;
				y = positionXY.y;

				// Velocities in x and y
				String stringVelocityX = getNodeAttributes("vx", curNode);
				double velocityX = stringVelocityX.isEmpty() ? 0 : Double.parseDouble(stringVelocityX);
				String stringVelocityY = getNodeAttributes("vy", curNode);
				double velocityY = stringVelocityY.isEmpty() ? 0 : Double.parseDouble(stringVelocityY);
				String stringMass = getNodeAttributes("mass", curNode);
				
				double mass = stringMass.isEmpty() ? (nodeType
						.equals(Parser.ID_MASS) ? Constants.MASS_DEFAULT_MASS
						: 0) : Double.parseDouble(stringMass);

				if (nodeType.equals(Parser.ID_FIXED)) {
					massMap.put(id, new Mass("", x, y, mass));
				} else {
					massMap.put(id, new MovingMass("", x, y, velocityX, velocityY, mass));
				}
			}
		}
	}

	/**
	 * Generate springs and muscles
	 */
	
	private void constructSpringsAndMuscles(NodeList links) {
		for (int i = 0; i < links.getLength(); i++) {
			Node curNode = links.item(i);
			String nodeType = curNode.getNodeName();

			if (nodeType.equals(ID_SPRING) || nodeType.equals(ID_MUSCLE)) {
				
				Mass m1 = massMap.get(getNodeAttributes("a", curNode));
				Mass m2 = massMap.get(getNodeAttributes("b", curNode));

				String stringRestLength = getNodeAttributes("restLength", curNode);
				double restLength = stringRestLength.isEmpty() ? 0 : Double
						.parseDouble(stringRestLength);
				boolean isRestLengthGiven = !stringRestLength.isEmpty();
				String strConstant = getNodeAttributes("constant", curNode);
				double constantSpringK = strConstant.isEmpty() ? Constants.DEFAULT_SPRING_CONSTANT
						: Double.parseDouble(strConstant);
				String stringAmplitude = getNodeAttributes("amplitude", curNode);
				double amplitude = stringAmplitude.isEmpty() ? 0 : Double
						.parseDouble(stringAmplitude);

				if (nodeType.equals(Parser.ID_SPRING)) {
					Spring createSpring = new Spring(Parser.ID_SPRING, m1, m2);
					createSpring.setConstant(constantSpringK);
					if (isRestLengthGiven) {
						createSpring.setRestLength(restLength);
					}
					springList.add(createSpring);
				} else if (nodeType.equals(Parser.ID_MUSCLE)) {
					Muscle createMuscle = new Muscle(Parser.ID_MUSCLE, m1, m2);
					if (isRestLengthGiven) {
						createMuscle.setAverageRestLength(restLength);
					} else {
						createMuscle.setAverageRestLength(Math.sqrt(Math.pow(
								m2.getX() - m1.getX(), 2)
								+ Math.pow(m2.getY() - m1.getY(), 2)));
					}
					createMuscle.setConstant(constantSpringK);
					createMuscle.setAmplitude(amplitude);
					springList.add(createMuscle);
				}
			}
		}
	}

	/**
	 * Smaller XML parsing methods.
	 **/
	
	protected String getNodeAttributes(String attributeName, Node node) {
		NamedNodeMap attributes = node.getAttributes();
		for (int y = 0; y < attributes.getLength(); y++) {
			Node attr = attributes.item(y);
			if (attr.getNodeName().equalsIgnoreCase(attributeName)) {
				return attr.getNodeValue();
			}
		}
		return "";
	}
	

	protected String getNodeAttributes(String fieldName, String attributeName, NodeList nodes) {
		for (int x = 0; x < nodes.getLength(); x++) {
			Node node = nodes.item(x);
			if (node.getNodeName().equalsIgnoreCase(fieldName)) {
				NodeList childrenNodes = node.getChildNodes();
				for (int y = 0; y < childrenNodes.getLength(); y++) {
					Node data = childrenNodes.item(y);
					if (data.getNodeType() == Node.ATTRIBUTE_NODE) {
						if (data.getNodeName().equalsIgnoreCase(attributeName))
							return data.getNodeValue();
					}
				}
			}
		}

		return "";
	}
	
	protected Node getNode(String fieldName, NodeList nodes) {
		for (int x = 0; x < nodes.getLength(); x++) {
			Node node = nodes.item(x);
			if (node.getNodeName().equalsIgnoreCase(fieldName)) {
				return node;
			}
		}

		return null;
	}

	protected String getNodeValue(Node node) {
		NodeList childrenNodes = node.getChildNodes();
		for (int x = 0; x < childrenNodes.getLength(); x++) {
			Node data = childrenNodes.item(x);
			if (data.getNodeType() == Node.TEXT_NODE)
				return data.getNodeValue();
		}
		return "";
	}

	protected String getNodeValue(String fieldName, NodeList nodes) {
		for (int x = 0; x < nodes.getLength(); x++) {
			Node node = nodes.item(x);
			if (node.getNodeName().equalsIgnoreCase(fieldName)) {
				NodeList childrenNodes = node.getChildNodes();
				for (int y = 0; y < childrenNodes.getLength(); y++) {
					Node data = childrenNodes.item(y);
					if (data.getNodeType() == Node.TEXT_NODE)
						return data.getNodeValue();
				}
			}
		}
		return "";
	}
	
	private NodeList buildDocument(String docToBuild){
		try{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder(); 
			Document doc = db.parse(new File(docToBuild));
			return doc.getChildNodes();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	private Vec2 checkForOffScreenAndCorrect(double x, double y) {
		
		if (x < Constants.WALL_THICKNESS * 2) {
			x = Constants.WALL_THICKNESS * 2;
		} else if (x > parseWidth - Constants.WALL_THICKNESS * 2) {
			x = parseWidth - Constants.WALL_THICKNESS * 2;
		}

		if (y < Constants.WALL_THICKNESS * 2) {
			y = Constants.WALL_THICKNESS * 2;
		} else if (y > parseHeight - Constants.WALL_THICKNESS * 2) {
			y = parseHeight - Constants.WALL_THICKNESS * 2;
		}
		return new Vec2((float) x, (float) y);
	}
}
