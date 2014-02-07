package Simulation;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public class SpringParser {
	private ArrayList<ArrayList<Object>> springList = new ArrayList<ArrayList<Object>>();
	private Document doc;
	private NodeList nodes;
	
	public SpringParser(Document d){
		doc = d;
		nodes = doc.getElementsByTagName("spring");
	}
	
	public void iterateThrough(){
		for (int i = 0; i < nodes.getLength(); i++) {
			
			ArrayList<Object> nestedSpringList = new ArrayList<Object>();

			Node node = nodes.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;

				nestedSpringList.add(element.getAttribute("a"));
				nestedSpringList.add(element.getAttribute("b"));
				nestedSpringList.add(Double.parseDouble(element.getAttribute("restlength")));
				
				if (element.getAttribute("constant").length() == 0) {
					double springConstant = 1.0;
					nestedSpringList.add(springConstant);
				} else {
					nestedSpringList.add(Double.parseDouble(element.getAttribute("restlength")));
				}
				
				springList.add(nestedSpringList);

			}
		}
	}
	
	public ArrayList<ArrayList<Object>> getSpringList(){
		return springList;
	}
}
