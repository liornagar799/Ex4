                          ![Test Image 1](https://github.com/liornagar799/Ex4/blob/main/src/image.png)
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

**StudentCode**
In this class the screen is booted, as well as the thread that runs the game. In the while loop we want to run as long as the server is running and perform: the GUI implementation, loading the game data from the server, loading the Pokemons from the server, loading the agents from the server , loading the graph from the server, positioning and moving the agents efficiently which will raise the points.

**implementaion:**
The class that calls all the other classes is: StudentCode Its main and important role is to connect the server and the client and run the game.

## Main Department Details: StudentCode
**main** This class contains the primary function in which it connects to a server at IP 127.0.0.1 and port: 6666.If you are unable to make the connection the program will throw an error.
The function will receive the following data from the server in the form of a String: Info, Graph, getAgents, getPokemons, isRunning and will send the data to the following functions:
**loadAgents** The function will divide the String according to the data contained in it, turn it into numeric data and initialize the agent.

**loadPokemons** The function will divide the String according to the data contained in it, turn it into numeric data and initialize the Pokemon.

**loadgraph** The function will divide the String according to the data contained in it, turn it into numeric data and initialize the graph.

**loadgame** The function will divide the String according to the data contained in it, turn it into numerical data and initialize the game.

The function will add the data using the **addAgentToTheGame** function which will sort the Pokemon using the Comparator according to the value of the Pokemon- the agent will prefer a perception of the highest value Pokemon.
After all the loads we will start the client's game in front of the server and it will be activated by the while loop which will run the game for us as long as we get from the server that the game is running.
When the time runs out and we get null we want to exit the game Also, we want to exit if the user presses the STOP THE GAME button.

 

 
 ## Performence

 
##  GUI:
In order to implement the graphical interface we created two departments:
 
**MyFrame:** This class initializes the MyPanel class that will be specified in the following setting.
 
**MyPanel**:This class implements the GUI, it updates the following drawings: the drawing of the graph, the drawing of the agents, the drawing of the Pokemon, the drawing of the game data.

