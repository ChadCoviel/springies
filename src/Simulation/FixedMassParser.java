package Simulation;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FixedMassParser {
	private HashMap<String, ArrayList<Double>> fixedMassMap = new HashMap<String, ArrayList<Double>>();
	private ArrayList<Double> fixedMassList = new ArrayList<Double>();
	private Document doc;
	private NodeList nodes;
	
	public FixedMassParser(Document d){
		doc = d;
		nodes = doc.getElementsByTagName("fixed");
	}
	
	public void iterateThrough(){
		for (int i = 0; i < nodes.getLength(); i++) {
			
			ArrayList<Double> fixedList = new ArrayList<Double>();

			Node node = nodes.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				
				fixedList.add(Double.parseDouble(element.getAttribute("x")));
				fixedList.add(Double.parseDouble(element.getAttribute("y")));
				
				fixedMassMap.put(element.getAttribute("id"), fixedMassList);

			}
			
		}
	}
	
	public HashMap<String, ArrayList<Double>> getFixedMassMap(){
		return fixedMassMap;
	}
}
