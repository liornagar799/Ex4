import api.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;


public class StudentCode implements Runnable {
    private static MyFrame GUI;
    private static long time = 100;
    private static Client client;
    private static LinkedList<Pokemon> pokemons;
    private static DirectedWeightedGraph g;
    private static Game thegame;
    private static LinkedList<Agent> agents;

    public static void main(String[] args) {
        client = new Client();
        pokemons= new LinkedList<>();
        agents= new LinkedList<>();
        g =new DirectedWeightedGraphImpl();
        thegame = new Game();
        Thread c = new Thread (new StudentCode());

        //try to start Connection to the server
        try {
            client.startConnection("127.0.0.1", 6666);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // all the load form json str
        String InfoStr = client.getInfo();
        //load the game
        thegame= loadgame(InfoStr);
        String graphStr = client.getGraph();
        //load the graph
        g=loadgraph(graphStr);
        System.out.println(graphStr);
        String agentsStr = client.getAgents();
        System.out.println(agentsStr);
        String pokemonsStr = client.getPokemons();
        //load the pokemons
        pokemons=loadPokemons(pokemonsStr,g);
        System.out.println(pokemonsStr);
        // add the agants to the game
        addAgentToTheGame(pokemons,thegame,client);
        agentsStr = client.getAgents();
        //load the agants
        agents=loadAgents(agentsStr,g);

        /////////////////start the game////////////////////
        String isRunningStr = client.isRunning();
        System.out.println(isRunningStr);
        client.start();
        //new Gui
        GUI = new MyFrame();
        //set a title
        GUI.setTitle("THE GAME OF LIOR & RAZ");
        GUI.setSize(1000, 700);
        //set the image of icon
        Image icon = Toolkit.getDefaultToolkit().getImage("src/image.png ");
        GUI.setIconImage(icon);
        GUI.mypanel.setBackground(Color.BLACK);
        GUI.mypanel.update(client,g);
        //make a button
        JButton stop = new JButton("STOP THE GAME");
        GUI.mypanel.add(stop);
        stop.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //the button stop the game and close the gui
                GUI.mypanel.setVisible(false);
                GUI.setVisible(false);
                client.stop();


            }
        });
        //make the gui visibile
        GUI.setVisible(true);
        GUI.mypanel.setVisible(true);
        //count the time
        int index=0;
        //start the game if client.isRunning()
            while (client.isRunning().equals("true")) {
                //function that moves all the agants
                moveAllTheAgants(client);
                ////restrictive the time we repaint the gui
                try {
                    if (index %4 == 0) {
                        GUI.repaint();
                    }
                    //restrictive the time we call the client
                    Thread.sleep(time);
                    index++;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(client.getAgents());
                System.out.println(client.timeToEnd());
                //if the game is stop close the program
                if (client.timeToEnd() == null) {
                    System.exit(0);
                }
            }
         //if the game is stop close the program
        System.exit(0);
    }

    //pass all the agents and find the next node for each of them
    synchronized private static void moveAllTheAgants(Client client) {
        //load the agants from the client
        agents= loadAgents(client.getAgents(), g);
        //load the pokemons from the client
        pokemons = loadPokemons(client.getPokemons(), g);
        DirectedWeightedGraphAlgorithms ga = new DirectedWeightedGraphAlgorithmsImpl();
        ga.init(g);
        // the loop pass all the agants and tell them where to go
        for (int i = 0; i < agents.size(); i++) {
            Agent agent = agents.get(i);
            int id = agent.getId();
            int dest = agent.getDest();
            //find the next node that the agent need to go
            int next = agent.findNearPokpath(pokemons, ga);
            //send to the client where the agant need to go just if the agant on a node , not moving
            if ((dest == -1) && (agent.on_node())) {
                String s = "{\"agent_id\":" + id + ", \"next_node_id\":" + next + "}";
                client.chooseNextEdge(s);

            }
        }
        //after all the agants know what to to we fall the client to to a move
        client.move();
    }

    //The function will divide the String according to the data contained in it, turn it into numeric data and initialize the agents.
    public static LinkedList<Agent> loadAgents(String  agentsStr, DirectedWeightedGraph g) {
        //if the str agentsStr in not good
        if (agentsStr==null ||!agentsStr.contains("Agents")){
            return agents;
        }
        try {
            //try to load the agants
            agents= new LinkedList<Agent>();
            JSONObject ob = new JSONObject(agentsStr);
            JSONArray agens = ob.getJSONArray("Agents");
            int k = agens.length();
            for (int i = 0; i < k; ++i) {
                JSONObject pok = agens.getJSONObject(i);
                JSONObject pp = pok.getJSONObject("Agent");
                int id = pp.getInt("id");
                double speed = pp.getDouble("speed");
                String pr = pp.getString("pos");
                String[] pos = pr.split(",");
                double x = Double.parseDouble(pos[0]);
                double y = Double.parseDouble(pos[1]);
                double z = Double.parseDouble(pos[2]);
                GeoLocation pip = new GeoLocationImpl(x, y, z);
                int src = pp.getInt("src");
                int dest = pp.getInt("dest");
                double value = pp.getDouble("value");
                Agent p = new Agent(g,src);
                p.setId(id);
                p.setPos(pip);
                p.setSpeed(speed);
                p.setDest(dest);
                p.setValue(value);
                agents.add(p);
            }
        } catch (JSONException e){
            e.printStackTrace();
        }

        return agents;
    }
   //The function will divide the String according to the data contained in it, turn it into numeric data and initialize the pokemons.
    public static LinkedList<Pokemon> loadPokemons(String  pokemonsStr, DirectedWeightedGraph g) {
        //if the str pokemonsStr in not good
        if (pokemonsStr==null ||!pokemonsStr.contains("Pokemons")){
            return pokemons;
        }
        try {
            //try to load the pokemons
            pokemons = new LinkedList<Pokemon>();
            JSONObject obj = new JSONObject(pokemonsStr);
            JSONArray poks = obj.getJSONArray("Pokemons");
            int t = poks.length();
            for (int i = 0; i < t; ++i) {
                JSONObject pok = poks.getJSONObject(i);
                JSONObject pp = pok.getJSONObject("Pokemon");
                int ty = pp.getInt("type");
                double value = pp.getDouble("value");
                String po = pp.getString("pos");
                String[] pos = po.split(",");
                double x = Double.parseDouble(pos[0]);
                double y = Double.parseDouble(pos[1]);
                double z = Double.parseDouble(pos[2]);
                GeoLocation pip = new GeoLocationImpl(x, y, z);
                Pokemon p = new Pokemon(pip,ty,value,g);
                p.setId(i);
                pokemons.add(p);
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
        return pokemons;
    }
//The function will divide the String according to the data contained in it, turn it into numeric data and initialize the graph.
    public static DirectedWeightedGraph loadgraph(String  graphStr) {
        //if the str graphStr in not good
        if(graphStr==null ||!graphStr.contains("Edges")){
            return g;
        }
        try {
            //try to load the graph
            g= new DirectedWeightedGraphImpl();
            JSONObject obje = new JSONObject(graphStr);
            JSONArray edges= obje.getJSONArray("Edges");
            JSONArray nodes = obje.getJSONArray("Nodes");
            int e = edges.length();
            int no = nodes.length();
            for (int i = 0; i < no; ++i) {
                JSONObject node = nodes.getJSONObject(i);
                int  key = node.getInt("id");
                String geo = node.getString("pos");
                String[] pos = geo.split(",");
                double  x = Double.parseDouble(pos[0]);
                double  y = Double.parseDouble(pos[1]);
                double  z = Double.parseDouble(pos[2]);
                GeoLocation geoLocation = new GeoLocationImpl(x,y,z);
                NodeData curr = new NodeDataImpl(geoLocation, key);
                g.addNode(curr);}
            for (int i = 0; i < e; ++i) {
                JSONObject ed = edges.getJSONObject(i);
                int src = ed.getInt("src");
                double weight = ed.getDouble("w");
                int dest = ed.getInt("dest");
                g.connect(src, dest, weight);
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
        return g;
    }

    //The function will divide the String according to the data contained in it, turn it into numeric data and initialize the game.
    public static Game loadgame(String  InfoStr) {
        //if the str InfoStr in not good
        if(InfoStr==null||!InfoStr.contains("GameServer")){
            return thegame;
        }
        try {
            //try to load the thegame
            thegame = new Game();
            JSONObject object = new JSONObject(InfoStr);
            Game The_Game = new Game();
            JSONObject game = object.getJSONObject("GameServer");
            int num = game.getInt("pokemons");
            The_Game.setNum_of_pokemons(num);
            int mov = game.getInt("moves");
            The_Game.setmoves(mov);
            int grad = game.getInt("grade");
            The_Game.setgrade(grad);
            int id1 = game.getInt("id");
            The_Game.setId(id1);
            int level = game.getInt("game_level");
            The_Game.setGame_level(level);
            int age = game.getInt("agents");
            The_Game.setagents(age);
            thegame=The_Game;
        } catch (JSONException e){
            e.printStackTrace();
        }

        return thegame;
    }

    //find the bust plave to set the agant on the graph
    private static void addAgentToTheGame(LinkedList<Pokemon> pokemons, Game thegame, Client client){
        // sort all the pokemons according to the value
        Comparator<Pokemon> compare = (Pokemon first, Pokemon second) -> Double.compare(first.getValue(), second.getValue());
        //do the sort
        pokemons.sort(compare);
        int zz=0;
        // pass all the pos of the pokemons
        for (int j=1; j<=thegame.getagents(); j++){
            if(zz<=pokemons.size()) {
                //pot the egent near the pok pos
                int curr = pokemons.get(zz).getedge().getSrc();
                client.addAgent("{\"id\":" + curr + "}");
                zz++;
            }else{
                client.addAgent("{\"id\":0}");
            }
        }

    }

    @Override
    public void run() {
        ;
    }
}
