package Simulation;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public class ForceParser {
	private Document doc;
	private NodeList gravityNodes,viscosityNodes,centerMassNodes,wallNodes;
	
	public ForceParser(Document d){
		doc = d;
		gravityNodes = doc.getElementsByTagName("gravity");
		viscosityNodes = doc.getElementsByTagName("viscosity");
		centerMassNodes = doc.getElementsByTagName("centermass");
		wallNodes = doc.getElementsByTagName("wall");
	}
	
	public void parseGravity(){
		for (int i = 0; i < gravityNodes.getLength(); i++) {

			Node node = gravityNodes.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;

				System.out.println("direction: "
						+ element.getAttribute("direction"));
				System.out.println("magnitude: "
						+ element.getAttribute("magnitude"));

			}

		}
	}
	
	public void parseViscosity(){
		for (int i = 0; i < viscosityNodes.getLength(); i++) {

			Node node = viscosityNodes.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;

				System.out.println("direction: "
						+ element.getAttribute("direction"));
				System.out.println("magnitude: "
						+ element.getAttribute("magnitude"));

			}

		}
	}
	
	public void parseCenterMass(){
		for (int i = 0; i < centerMassNodes.getLength(); i++) {

			Node node = centerMassNodes.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;

				System.out.println("direction: "
						+ element.getAttribute("direction"));
				System.out.println("magnitude: "
						+ element.getAttribute("magnitude"));

			}

		}
	}
	
	public void parseWall(){
		for (int i = 0; i < wallNodes.getLength(); i++) {

			Node node = wallNodes.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;

				System.out.println("direction: "
						+ element.getAttribute("direction"));
				System.out.println("magnitude: "
						+ element.getAttribute("magnitude"));

			}

		}
	}
}
