# Ex4
Our goal is to maximize the total amount of weights of the captured Pokemon.
# Witing language:
JAVA
# Introduction:
In this assignment, we were asked to implement the main tools learned during the course.
We designed a "Pokemon game" where given a weighted graph, Pokemon and agents, we positioned the agents so they could catch as many Pokemon as possible. The Pokemon are placed at the edges of the graph, therefore, the agent should take the appropriate edge to catch the Pokemon. Our goal is to maximize the total amount of weights of the captured Pokemon. In addition, it is forbidden to exceed the maximum number of server readings allowed per second - a maximum of 10.

## Planning of the main departments:

**api** This folder contains our implementation of the graph and the algorithms of the graph from the EX2 task.
**Agent**This class contains all the agent's data.
**Pokemon**This class contains all the pokemon's data.
**Client**This is a class that we got and it makes the connection to the server and thus we get the server and need to update them in the program.After the server is running a client can connect to it (play with it)
**Game**This class contains all the game's data.
**implementaion:**
We implemented the graph for the nodes and Edges we did using the data structure: dictionary.

## algorithems:

**GraphAlgo** This class implement GraphAlgoInterface interface, represents a Directed (positive) Weighted Graph Theory Algorithms.
The functions we implement:

 1. void **init**(GraphInterface g)- initialize the graph on which this set of the algorithms.
 2. GraphInterface **getGraph**()- returns the graph of this class.  
 3. **shortestPath**-return the weight of the path, and list of shortest path between src to dest, , if there is no path we return float('inf'), []
 4. **center**()-  Finds the Node with the shortest route to all other nodes.
 5. **tsp**(List<int> node_lst)- return a list of consecutive nodes which go over all the nodes in cities.
 6. **save**(file)- saves this weighted (directed) graph to the given, the file is JSON format.
 7. **load**(file)- loads a graph to this graph algorithm, the file is JSON format.
 8. **plot_graph**()- implemented a graphical interface that Displays the graph.
 

 
 ## Performence
 We tested the program we built with several different sizes of graphs.
 And compare it to the performance versus the same work in java.
 All comparisons can be found in the wiki file.
 
##  GUI:
 In order to implement the graphical interface we created two departments:
 **MyFrame:** This class initializes the MyPanel class that will be specified in the following setting.
 **MyPanel**:This class implements the GUI, it updates the following drawings: the drawing of the graph, the drawing of the agents, the drawing of the Pokemon, the drawing of the game data.

