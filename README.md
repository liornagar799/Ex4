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
The class that calls all the other classes is: StudentCode Its main and important role is to connect the server and the client and run the game. Details regarding this department will appear below.

## Main Department Details: StudentCode
**main** This class contains the primary function in which it connects to a server at IP 127.0.0.1 and port: 6666.If you are unable to make the connection the program will throw an error.
The function will receive the following data from the server in the form of a String: Info, Graph, getAgents, getPokemons, isRunning and will send the data to the following functions:
**loadAgents** The function will divide the String according to the data contained in it, turn it into numeric data and initialize the agent.

**loadPokemons** The function will divide the String according to the data contained in it, turn it into numeric data and initialize the Pokemon.

**loadgraph** The function will divide the String according to the data contained in it, turn it into numeric data and initialize the graph.

**loadgame** The function will divide the String according to the data contained in it, turn it into numerical data and initialize the game.

The function will add the data using the **addAgentToTheGame** function which will sort the Pokemon using the Comparator according to the value of the Pokemon- the agent will prefer a perception of the highest value Pokemon.
After all the loads we will start the client's game in front of the server and it will be activated by the while loop that you will run the game for us as long as we get from the server that the game is running and move the agents using the **moveAllTheAgants** function.
When the time runs out and we get null we want to exit the game Also, we want to exit if the user presses the STOP THE GAME button.

 
## Performance
All performance was performed using the various classes we listed, as well as the GUI.
Attached to the program are testers designed to verify implementation.
in src we have 2 class of testes:
 
Game_test - check all the Functions in Game class.
 
Pokemon_test - check all the Functions in Pokemon class.

in api we have 2 class of testes:
 
DirectedWeightedGraphAlgorithmsImpl_test - check all the Functions in DirectedWeightedGraphAlgorithmsImpl class.
 
DirectedWeightedGraphImpl_test- check all the Functions inDirectedWeightedGraphImpl class.

##  GUI:
In order to implement the graphical interface we created two departments:
 
**MyFrame:** This class initializes the MyPanel class that will be specified in the following setting.
 
**MyPanel**:This class implements the GUI, it updates the following drawings: the drawing of the graph, the drawing of the agents, the drawing of the Pokemon, the drawing of the game data. The yellow circles mark the Pokemon and the green circles mark the agents

##  Run:

Download all the files in the git to your computer
 
Open CMD line 

case = number of level 0-15
 
And records:
java -jar Ex4_Server_v0.0.jar case 
 
##  Results:
case 0: {"GameServer":{"pokemons":1,"is_logged_in":false,"moves":287,"grade":115,"game_level":0,"max_user_level":-1,"id":0,"graph":"data/A0","agents":1}}

case 1:{"GameServer":{"pokemons":2,"is_logged_in":false,"moves":580,"grade":387,"game_level":1,"max_user_level":-1,"id":0,"graph":"data/A0","agents":1}}

case 2:{"GameServer":{"pokemons":3,"is_logged_in":false,"moves":288,"grade":236,"game_level":2,"max_user_level":-1,"id":0,"graph":"data/A0","agents":1}}

case 3:{"GameServer":{"pokemons":4,"is_logged_in":false,"moves":580,"grade":755,"game_level":3,"max_user_level":-1,"id":0,"graph":"data/A0","agents":1}}

case 4:{"GameServer":{"pokemons":5,"is_logged_in":false,"moves":287,"grade":182,"game_level":4,"max_user_level":-1,"id":0,"graph":"data/A1","agents":1}}

case 5:{"GameServer":{"pokemons":6,"is_logged_in":false,"moves":580,"grade":472,"game_level":5,"max_user_level":-1,"id":0,"graph":"data/A1","agents":1}}

case 6:{"GameServer":{"pokemons":1,"is_logged_in":false,"moves":287,"grade":79,"game_level":6,"max_user_level":-1,"id":0,"graph":"data/A1","agents":1}}

case 7:{"GameServer":{"pokemons":2,"is_logged_in":false,"moves":581,"grade":269,"game_level":7,"max_user_level":-1,"id":0,"graph":"data/A1","agents":1}}

case 8:{"GameServer":{"pokemons":3,"is_logged_in":false,"moves":288,"grade":110,"game_level":8,"max_user_level":-1,"id":0,"graph":"data/A2","agents":1}}

case 9:{"GameServer":{"pokemons":4,"is_logged_in":false,"moves":580,"grade":364,"game_level":9,"max_user_level":-1,"id":0,"graph":"data/A2","agents":1}}

case 10:{"GameServer":{"pokemons":5,"is_logged_in":false,"moves":288,"grade":59,"game_level":10,"max_user_level":-1,"id":0,"graph":"data/A2","agents":1}}

case 11:{"GameServer":{"pokemons":6,"is_logged_in":false,"moves":578,"grade":1390,"game_level":11,"max_user_level":-1,"id":0,"graph":"data/A2","agents":3}}

case 12:{"GameServer":{"pokemons":1,"is_logged_in":false,"moves":287,"grade":40,"game_level":12,"max_user_level":-1,"id":0,"graph":"data/A3","agents":1}}

case 13:{"GameServer":{"pokemons":2,"is_logged_in":false,"moves":579,"grade":269,"game_level":13,"max_user_level":-1,"id":0,"graph":"data/A3","agents":2}}

case 14:{"GameServer":{"pokemons":3,"is_logged_in":false,"moves":285,"grade":173,"game_level":14,"max_user_level":-1,"id":0,"graph":"data/A3","agents":3}}

case 15:{"GameServer":{"pokemons":4,"is_logged_in":false,"moves":579,"grade":300,"game_level":15,"max_user_level":-1,"id":0,"graph":"data/A3","agents":1}}

# UML
Click on the image if you want to enlarge the image.
![Test Image 2](https://github.com/liornagar799/Ex4/blob/main/UML/Ex4-UML.png)
