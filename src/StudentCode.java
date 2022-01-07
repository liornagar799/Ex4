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
    private static long dt = 365;
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
        Thread clien = new Thread (new StudentCode());

        try {
            client.startConnection("127.0.0.1", 6666);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String InfoStr = client.getInfo();
        thegame= loadgame(InfoStr);
        String graphStr = client.getGraph();
        g=loadgraph(graphStr);
        System.out.println(graphStr);
        String agentsStr = client.getAgents();
        System.out.println(agentsStr);
        String pokemonsStr = client.getPokemons();
        pokemons=loadPokemons(pokemonsStr,g);
        System.out.println(pokemonsStr);
        addAgentToTheGame(pokemons,thegame,client);
        agentsStr = client.getAgents();
        agents=loadAgents(agentsStr,g);
        /////////////////////////////////////
        String isRunningStr = client.isRunning();
        System.out.println(isRunningStr);
        client.start();
        GUI = new MyFrame();
        GUI.setTitle("THE GAME OF LIOR & RAZ");
        GUI.setSize(1000, 700);
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\97252\\IdeaProjects\\Ex4\\src\\image.png");
        GUI.setIconImage(icon);
        GUI.mypanel.setBackground(Color.BLACK);
        GUI.mypanel.update(client,g);
        JButton stop = new JButton("STOP THE GAME");
        GUI.mypanel.add(stop);
        stop.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                GUI.mypanel.setVisible(false);
                GUI.setVisible(false);
                client.stop();


            }
        });
        GUI.setVisible(true);
        GUI.mypanel.setVisible(true);
        int ind=0;
        //||client.timeToEnd()==null||!client.timeToEnd().equals("-1")
//        String s=client.timeToEnd();
//          while (client.isRunning().equals("true")){
//        while (!client.timeToEnd().equals("-1")) {
            while (client.isRunning().equals("true")) {
                moveAgants(client);
                try {
                    if (ind %4== 0) {
                        GUI.repaint();
                    }
                    Thread.sleep(dt);
                    ind++;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(client.timeToEnd()==null){
                   System.exit(0);
                }
//                moveAgants(client);
//                System.out.println(client.getAgents());
//                System.out.println(client.timeToEnd());
            //}
//            s=client.timeToEnd();
//        while (client.isRunning().equals("true")) {
//            moveAgants(client);
//            try {
//                if (ind % 5 == 0) {
//                    GUI.repaint();
//                }
//                Thread.sleep(dt);
//                ind++;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
////            System.out.println(client.getAgents());
////            System.out.println(client.timeToEnd());
//        }

//              while (client.isRunning().equals("true")) {
//                  moveAgants(client);
//                  try {
//                      if (ind % 1 == 0) {
//                          GUI.repaint();
//                      }
//                      Thread.sleep(dt);
//                      ind++;
//                  } catch (Exception e) {
//                      e.printStackTrace();
//                  }
//                  System.out.println(client.getAgents());
//                  System.out.println(client.timeToEnd());
              }              }
    //    }//}
        ///////////////////////////////////////////
//        while (!client.timeToEnd().equals("-1")){
//        while (client.isRunning().equals("true")){// && !client.timeToEnd().equals("-1")) {
////            moveAgants(client);
////            try {
////                if (ind % 100 == 0) {
////                    GUI.repaint();
////                }
////                Thread.sleep(dt);
////                ind++;
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
//        while (client.isRunning().equals("true")) {
//            client.move();
//            System.out.println(client.getAgents());
//            System.out.println(client.timeToEnd());
//        }}
//            Scanner keyboard = new Scanner(System.in);
//            System.out.println("enter the next dest: ");
//            for (int i=0; i<agents.size(); i++) {
//                int next = mod((agents.get(i).getSrc() - 1), g.nodeSize());
//                client.chooseNextEdge("{\"agent_id\":0, \"next_node_id\": " + next + "}");
//                client.move();
//                GUI.repaint();
//            }
////            int next = mod((a.getSrc() - 1),g.nodes.size());
////            System.out.println(client.getAgents());
////            System.out.println(client.timeToEnd());
//        }
//        }
//    public static int mod(int a, int n){
//        return ((a%n)+n)%n;
//    }
//
    synchronized private static void moveAgants(Client client) {
        agents= loadAgents(client.getAgents(), g);
        pokemons = loadPokemons(client.getPokemons(), g);
        DirectedWeightedGraphAlgorithms ga = new DirectedWeightedGraphAlgorithmsImpl();
        ga.init(g);
        for (int i = 0; i < agents.size(); i++) {
            Agent ag = agents.get(i);
            int id = ag.getId();
            int dest = ag.getDest();
            double v = ag.getValue();
            int next = ag.findNearPokpath(pokemons, ga);
            if ((dest == -1) && (ag.on_node())) {
//                 int next = ag.findNearPokpath(pokemons, ga);
                //'{"agent_id":0, "next_node_id":1}'
                String s = "{\"agent_id\":" + id + ", \"next_node_id\":" + next + "}";
                System.out.println( s );
                System.out.println(ag.getDest());
                client.chooseNextEdge(s);
                System.out.println( s );
            }
//            client.move();
        }
        client.move();
    }

    public static LinkedList<Agent> loadAgents(String  agentsStr, DirectedWeightedGraph g) {
        if (agentsStr==null ||!agentsStr.contains("Agents")){
            return agents;
        }
        try {
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

    public static LinkedList<Pokemon> loadPokemons(String  pokemonsStr, DirectedWeightedGraph g) {
        if (pokemonsStr==null ||!pokemonsStr.contains("Pokemons")){
            return pokemons;
        }
        try {
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
                p.set_Id(i);
                pokemons.add(p);
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
        return pokemons;
    }

    public static DirectedWeightedGraph loadgraph(String  graphStr) {
        if(graphStr==null ||!graphStr.contains("Edges")){
            return g;
        }
        try {
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

    public static Game loadgame(String  InfoStr) {
        if(InfoStr==null||!InfoStr.contains("GameServer")){
            return thegame;
        }
        try {
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
            String gr = game.getString("graph");
            The_Game.setgraph(gr);
            int age = game.getInt("agents");
            The_Game.setagents(age);
            thegame=The_Game;
        } catch (JSONException e){
            e.printStackTrace();
        }

        return thegame;
    }

    private static void addAgentToTheGame(LinkedList<Pokemon> pokemons, Game thegame, Client client){
        Comparator<Pokemon> compare = (Pokemon ONE, Pokemon TWO) -> Double.compare(ONE.getValue(), TWO.getValue());
        pokemons.sort(compare);
        int zz=0;
        for (int j=1; j<=thegame.getagents(); j++){
            if(zz<=pokemons.size()) {
                int curr = pokemons.get(zz).get_edge().getSrc();
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