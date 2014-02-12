springies
=========

*We did not consult any other students or TAs. Chad met with Professor Duvall when he had a .jar import problem, and received some advice on making a new function for the DOMParser. We did use the following online resources:

	http://www.jbox2d.org/processing/doc/index.html - Physics
	http://www.13thmonkey.org/~boris/jgame/JGame/javadoc/overview-summary.html - jgame; jgame.impl; 		jgame.platform
	
That is all.*

**NOTE: We had a lot of trouble with GitHub for this project. While we got through the initial commit, merge and pull exercise that Professor Duvall showed us in recitation. We still ended up having trouble with merging with the master while we were working on the actual project. So we resorted to using our Macbooks' Airdrop utility to transfer code and get the project up and running. Once the project was done, we finally figured out how to merge with the master. This is why our GitHub data graphs for merges, commits and etc. looks so poor. We were GitHub illiterate for the majority of this project until the very end.**

Starter code for Springies team project

Initial Design Document - Springies

Chad Coviel (cmc86);
Pritam Mathivanan(pm98)

The spring-mass model will consist of at least three distinct classes: Spring, Mass and Force. A class Muscle would extend Spring, and have a few additional methods that are distinct. For example, a method might consider the number of muscle units that are contracting and relaxing simultaneously or take into account muscle fatigue from previous work. Such methods would not be needed in the Spring class because we hope to simulate an ideal spring-mass model. 

Preset values for springs, masses and muscles will be drawn from .xml files that will be included with the source code. We will not be creating our own .xml parser. 

The packages we will need include Springies, JGame jars, and jboxGlue. The Mass class would be part of a package we create that includes Spring and thus Muscles. A mass would be a rigid structure but the spring is flexible. The Mass and Spring have little in common and so would be completely separate classes. A main class such as MassAndSpring would combine the two. The Spring class would extend the PhysicalObject class that is part of the jboxGlue package and the Mass class would extend the PhysicalObjectRect class. Both the Spring and Mass would utilize the force class to simulate the effect that different applied forces have on the model during simulation. Finally, a class that extends all of the aforementioned classes would be responsible for creating (painting) the objects onto the canvas of the world. 

The Mass class is simple. The constructor takes in the mass as a parameter and perhaps the dimensions although a default would be implemented if it is not provided. Unimportant characteristics like color will be provided as well. There would also have to be specified a hook point for the mass where the spring is attached but a default could be provided if not specified. The constructor in this class will have to consider the x and y positions of the mass as well. When attached to the Spring, the x and y positions are immediately set to be the endpoint of the spring where it is to be attached. Several other methods will have the following functions, apply a force, apply gravity and check if the position of the mass is out of bounds in respect to the simulation canvas. 
	
The Spring Class would begin with a constructor that allows the user to set up the parameters of the Spring. Parameters include connected masses (at the beginning, end or both), spring length, and the spring stiffness factor ‘k’. Besides this constructor, there should also be a method to derive a stiffness factor value given a spring length and connected masses; the method would utilize a simple formula to do so. Additionally, x and y positions would be specified for both the start and endpoint of the spring. The spring will have two hook points - one for each end. This would allow us to set up a model in such a way that one end is attached to the wall and the other end is attached to a mass.
	
The Force Class would have methods that allow for zero force, a specified force (with direction and magnitude), and a derived force (direction and distance between two points). This class will accommodate different types of forces. There should also be methods to invert the forces direction, reset the force magnitude, add and subtract the force with other forces, scale the force and get the angle between two forces. All of these would be essential to realistically simulate a spring-mass model. 

On the other hand, Spring and Mass will have methods that update their respective locations every frame of the simulation. The code might be more efficient if these methods are combined into one class that takes care of all the update actions for Spring and Mass.

Moreover, the Mass and Spring will have to be “painted” onto the canvas. However, instead of having individual methods in each class that do so, all the “painting” will be combined into one class called Paint. This allows for easier maintenance of the code and simple debugging. The Paint class would be the class to extend all the others as mentioned above.
	
Most likely, we will need a class that enables the actual simulation of the models. It would aptly be named Simulation. It could extend Paint or inherit the other classes in some other manner. At this point in time, we are debating implementation options. 
