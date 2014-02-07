package Simulation;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public class MassParser {
	
		private HashMap<String, ArrayList<Double>> massMap = new HashMap<String, ArrayList<Double>>();
		private ArrayList<Double> massList = new ArrayList<Double>();
		private NodeList n;
		private Document doc;
		
		public MassParser(Document d){
			doc = d;
			n = doc.getElementsByTagName("mass");
		}
		
		public void iterateThrough(){
			for (int i = 0; i < n.getLength(); i++) {
				Node node = n.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					
					massList.add(Double.parseDouble(element.getAttribute("x")));
					massList.add(Double.parseDouble(element.getAttribute("y")));
					
					if (element.getAttribute("vx").length() == 0) {
						double vx = 0;
						massList.add(vx);
					} else {
						massList.add(Double.parseDouble(element.getAttribute("vx")));
					}

					if (element.getAttribute("vy: ").length() == 0) {
						double vy = 0;
						massList.add(vy);
					} else {
						massList.add(Double.parseDouble(element.getAttribute("vy")));
					}
					
					massMap.put(element.getAttribute("id"), massList);

				}
			}
		}
		
		public HashMap<String, ArrayList<Double>> getMassMap(){
			return massMap;
		}
}
