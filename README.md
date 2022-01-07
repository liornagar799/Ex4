# Ex4
Our goal is to maximize the total amount of weights of the captured Pokemon.
# Witing language:
JAVA
# introduction:
In this assignment, we were asked to implement the main tools learned during the course.
We designed a "Pokemon game" where given a weighted graph, Pokemon and agents, we positioned the agents so they could catch as many Pokemon as possible. The Pokemon are placed at the edges of the graph, therefore, the agent should take the appropriate edge to catch the Pokemon. Our goal is to maximize the total amount of weights of the captured Pokemon. In addition, it is forbidden to exceed the maximum number of server readings allowed per second - a maximum of 10.

## Planning of the main departments:

**Node** represents the set of operations applicable on a node in a weighted graph.

**DiGraph**This class implement #GraphInterface interface:The interface has a road-system or communication network in mind and should support a large number of nodes (over 100,000).
The GraphInterface class represents a Directional Weighted Graph,with effective representation.
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
