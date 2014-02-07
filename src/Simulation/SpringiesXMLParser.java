package Simulation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SpringiesXMLParser{
	
	private MassParser massparse;
	private SpringParser springparse;
	private FixedMassParser fixedmassparse;
	private MuscleParser muscleparse;
	private ForceParser forceparse;
	
	public void readXMLFile(String xmlFile){
		//Specify type as File for XML String
		File xml = new File(xmlFile);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document xmlDoc = dBuilder.parse(xml);
			xmlDoc.getDocumentElement().normalize();
			
			massparse = new MassParser(xmlDoc);
			fixedmassparse = new FixedMassParser(xmlDoc);
			springparse = new SpringParser(xmlDoc);
			muscleparse = new MuscleParser(xmlDoc);
			forceparse = new ForceParser(xmlDoc);
			
			massparse.iterateThrough();
			fixedmassparse.iterateThrough();
			springparse.iterateThrough();
			muscleparse.iterateThrough();
			
			//Iterate through to find nodes of all 4 forces
			forceparse.parseGravity();
			forceparse.parseViscosity();
			forceparse.parseCenterMass();
			forceparse.parseWall();

		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}