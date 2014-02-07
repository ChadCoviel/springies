package Simulation;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public class MuscleParser {
	private ArrayList<ArrayList<Object>> muscleList = new ArrayList<ArrayList<Object>>();
	private Document doc;
	private NodeList nodes;
	
	public MuscleParser(Document d){
		doc = d;
		nodes = doc.getElementsByTagName("muscle");
	}
	
	public void iterateThrough(){
		for (int i = 0; i < nodes.getLength(); i++) {
			
			ArrayList<Object> nestedMuscleList = new ArrayList<Object>();

			Node node = nodes.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				
				nestedMuscleList.add(element.getAttribute("a"));
				nestedMuscleList.add(element.getAttribute("b"));
				nestedMuscleList.add(Double.parseDouble(element.getAttribute("restlength")));
				
				
				if (element.getAttribute("constant").length() == 0) {
					double muscleConstant = 1;
					nestedMuscleList.add(muscleConstant);
				} else {
					nestedMuscleList.add(Double.parseDouble(element.getAttribute("constant")));
				}
				
				nestedMuscleList.add(Double.parseDouble(element.getAttribute("amplitude")));
				
				muscleList.add(nestedMuscleList);

			}

		}
	}
	
	public ArrayList<ArrayList<Object>> getMuscleList(){
		return muscleList;
	}

}
