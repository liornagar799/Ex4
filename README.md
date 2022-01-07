![Test Image 1](image.png)
# Ex4
Our goal is to maximize the total amount of weights of the captured Pokemon.
# Witing language:
JAVA
# Introduction:
In this assignment, we were asked to implement the main tools learned during the course.
We designed a "Pokemon game" where given a weighted graph, Pokemon and agents, we positioned the agents so they could catch as many Pokemon as possible. The Pokemon are placed at the edges of the graph, therefore, the agent should take the appropriate edge to catch the Pokemon. Our goal is to maximize the total amount of weights of the captured Pokemon. In addition, it is forbidden to exceed the maximum number of server readings allowed per second - a maximum of 10.

## Planning of the main departments:

**api** This folder contains our implementation of the graph and the algorithms of the graph from the EX2 task.

**Agent** This class contains all the agent's data.

**Pokemon** This class contains all the pokemon's data.

**Client** This is a class that we got and it makes the connection to the server and thus we get the server and need to update them in the program.After the server is running a client can connect to it (play with it)

**Game** This class contains all the game's data.

**StudentCode**This class contains the primary function in which it connects to a server at IP 127.0.0.1 and port: 6666.
In this class the screen is booted, as well as the thread that runs the game. In the while loop we want to run as long as the server is running and perform: the GUI implementation, loading the game data from the server, loading the Pokemon from the server, loading the graph from the server, positioning and moving the agents efficiently which will raise the points, loading the agents from the server.

**implementaion:**
The class that calls all the other classes is: StudentCode Its main and important role is to connect the server and the client and run the game.

## algorithems:

 

 
 ## Performence
 We tested the program we built with several different sizes of graphs.
 And compare it to the performance versus the same work in java.
 All comparisons can be found in the wiki file.
 
##  GUI:
In order to implement the graphical interface we created two departments:
 
**MyFrame:** This class initializes the MyPanel class that will be specified in the following setting.
 
**MyPanel**:This class implements the GUI, it updates the following drawings: the drawing of the graph, the drawing of the agents, the drawing of the Pokemon, the drawing of the game data.

