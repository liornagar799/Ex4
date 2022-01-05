import api.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;


/**
 * myPanel class extends JPanel and implements the GUI of the game.
 *
 */
public class MyPanel  extends JPanel {
    private Client _ar;
    private LinkedList<Pokemon> pokemons;
    private LinkedList<Agent> agents;
    private DirectedWeightedGraph g;
    private Game game1;


    MyPanel(){
        Client _ar = new Client();
        LinkedList<Pokemon> pokemons= new LinkedList<>();
        LinkedList<Agent> agents= new LinkedList<>();
        DirectedWeightedGraph g = new DirectedWeightedGraphImpl();
        Game game1 = new Game();


    }

    public void update(Client ar) {
        this._ar = ar;
        this.agents = new LinkedList<>();
        this.g = new DirectedWeightedGraphImpl();
        this.pokemons = new LinkedList<>();
    }


    public void paint(Graphics gh) {
        int w = this.getWidth();
        int h = this.getHeight();
        gh.clearRect(0, 0, w, h);
        drawGraph(gh);
        drawPokemons(gh);
        drawAgants(gh);
        draw_gameDetails(gh);
        repaint();/////////////////////////check/////////////////////////
    }


    private void drawGraph(Graphics gh) {
        String gg = _ar.getGraph();
//        if (gg!=null && gg.contains("Edges")){
        g= loadgraph(gg,g);//}
        ////////////////drow graph//////////////////////////////////////
        HashMap<Integer, Double> get_X = new HashMap<>(this.g.nodeSize());
        HashMap<Integer, Double> get_Y = new HashMap<>(this.g.nodeSize());
        Graphics2D graphics = (Graphics2D) gh;
        super.paint(graphics);
        graphics.setStroke(new BasicStroke(2));
        super.paint(graphics);
        Iterator<NodeData> Nodes = this.g.nodeIter();
        while (Nodes.hasNext()) {
            NodeData node_data = Nodes.next();
            GeoLocation geo = node_data.getLocation();
            get_X.put(node_data.getKey(), geo.x());
            get_Y.put(node_data.getKey(), geo.y());
        }
        Normalization(get_X, 70, this.getWidth() - 70);
        Normalization(get_Y, 70, this.getHeight() - 70);
        Iterator<NodeData> nodesIter = this.g.nodeIter();
        while (nodesIter.hasNext()) {
            NodeData node_data = nodesIter.next();
            int key = node_data.getKey();
            int x = get_X.get(key).intValue();
            int y = get_Y.get(key).intValue();
            graphics.setColor(Color.PINK);
            graphics.fillOval(x, y, 7 * 2, 7 * 2);
            graphics.setColor(Color.WHITE);
            graphics.drawString("" + key, x, y - 10);

        }
        Iterator<EdgeData> edgeIter = this.g.edgeIter();
        while (edgeIter.hasNext()) {
            EdgeData edge_data = edgeIter.next();
            GeoLocation Geo_src = this.g.getNode(edge_data.getSrc()).getLocation();
            GeoLocation Geo_dest = this.g.getNode(edge_data.getDest()).getLocation();
            graphics.setColor(Color.BLACK);
            graphics.drawLine((int) Geo_src.x(), (int) Geo_src.y(), (int) Geo_dest.x(), (int) Geo_dest.y());

        }

        Iterator<EdgeData> edgeIter1 = this.g.edgeIter();
        while (edgeIter1.hasNext()) {
            EdgeData edge1 = edgeIter1.next();
            int x1 = get_X.get(edge1.getSrc()).intValue();
            int y1 = get_Y.get(edge1.getSrc()).intValue();
            int x2 = get_X.get(edge1.getDest()).intValue();
            int y2 = get_Y.get(edge1.getDest()).intValue();
            //draw the edges
            graphics.setColor(Color.gray);
            graphics.drawLine(x1 + 10, y1 + 10, x2 + 10, y2 + 10);

        }}


    private void Normalization(HashMap<Integer, Double> normalize, double SRCoutput, double DESToutput) {
        double min = Double.MAX_VALUE,max = Double.MIN_VALUE;
        for (Integer key : normalize.keySet()) {
            double cnt = normalize.get(key);
            if (cnt < min) {min = cnt;}
            if (cnt > max) {max = cnt;}
        }
        double current = ((DESToutput - SRCoutput) / (max - min));
        double finalMin = min;
        normalize.replaceAll((k, v) -> SRCoutput + current * (normalize.get(k) - finalMin));

    }

    private void Norm(HashMap<Integer, Double> normalize,HashMap<Integer, Double> cur, double SRCoutput, double DESToutput) {
        double min = Double.MAX_VALUE,max = Double.MIN_VALUE;
        for (Integer key : normalize.keySet()) {
            double cnt = normalize.get(key);
            if (cnt < min) {min = cnt;}
            if (cnt > max) {max = cnt;}
        }
        double current = ((DESToutput - SRCoutput) / (max - min));
        double finalMin = min;
        cur.replaceAll((k, v) -> SRCoutput + current * (cur.get(k) - finalMin));

    }

    private void drawPokemons(Graphics gh) {
            String fs = _ar.getPokemons();
//            if(fs.contains("Pokemons")){
             pokemons=loadPokemons(fs,g,pokemons);
//            }
        HashMap<Integer, Double> get_X = new HashMap<>(this.g.nodeSize());
        HashMap<Integer, Double> get_Y = new HashMap<>(this.g.nodeSize());
        Iterator<NodeData> Nodes = this.g.nodeIter();
        while (Nodes.hasNext()) {
            NodeData node_data = Nodes.next();
            GeoLocation geo = node_data.getLocation();
            get_X.put(node_data.getKey(), geo.x());
            get_Y.put(node_data.getKey(), geo.y());
        }
        int t =this.pokemons.size();
        HashMap<Integer, Double> get_X_pok = new HashMap<>(t);
        HashMap<Integer, Double> get_Y_pok = new HashMap<>(t);
        Iterator<Pokemon> iter = pokemons.iterator();
        while (iter.hasNext()) {
            Pokemon f = iter.next();
            GeoLocation c = f.getLocation();
            get_X_pok.put(f.getId(), c.x()); /// to check///
            get_Y_pok.put(f.getId(), c.y());/// to check///
        }
        Norm(get_X,get_X_pok, 70, this.getWidth() - 70);
        Norm(get_Y,get_Y_pok, 70, this.getHeight() - 70);
        Iterator<Pokemon> itr = pokemons.iterator();
        while(itr.hasNext()) {
            Pokemon f = itr.next();
            int key =f.getId();
            int x = get_X_pok.get(key).intValue();
            int y = get_Y_pok.get(key).intValue();
            gh.setColor(Color.YELLOW);
            gh.fillOval(x, y, 7 * 2, 7 * 2);
            gh.setColor(Color.BLACK);
            gh.drawString("" + key, x, y - 10);

        }
    }



    private void drawAgants(Graphics gh)  {
        String rs = _ar.getAgents();
//        if (rs.contains("Agents")){
         agents=loadAgents(rs,g, agents);
//        }
        HashMap<Integer, Double> get_X = new HashMap<>(this.g.nodeSize());
        HashMap<Integer, Double> get_Y = new HashMap<>(this.g.nodeSize());
        Iterator<NodeData> Nodes = this.g.nodeIter();
        while (Nodes.hasNext()) {
            NodeData node_data = Nodes.next();
            GeoLocation geo = node_data.getLocation();
            get_X.put(node_data.getKey(), geo.x());
            get_Y.put(node_data.getKey(), geo.y());
        }
        int k = this.agents.size();
        HashMap<Integer, Double> get_X_agents = new HashMap<>(k);
        HashMap<Integer, Double> get_Y_agents = new HashMap<>(k);
        Iterator<Agent> iter = agents.iterator();
        while (iter.hasNext()) {
            Agent f = iter.next();
            GeoLocation c = f.getPos();
            get_X_agents.put(f.getId(), c.x()); /// to check///
            get_Y_agents.put(f.getId(), c.y());/// to check///
        }
        Norm(get_X,get_X_agents, 70, this.getWidth() - 70);
        Norm(get_Y,get_Y_agents, 70, this.getHeight() - 70);
        Iterator<Agent> itr = agents.iterator();
        while(itr.hasNext()) {
            Agent f = itr.next();
            int key =f.getId();
            int x = get_X_agents.get(key).intValue();
            int y = get_Y_agents.get(key).intValue();
            gh.setColor(Color.green);
            gh.fillOval(x, y, 7 * 2, 7 * 2);
            gh.setColor(Color.BLACK);
            gh.drawString("" + key, x, y - 10);

        }
    }

//
//
    private void draw_gameDetails (Graphics gh) {
        gh.setColor(Color.WHITE);
        Font font = gh.getFont().deriveFont( 14.0f );
        gh.setFont( font );
        String ss = (_ar. timeToEnd());
        gh.drawString("time left: "+ ss, 10,45);
        String gameDetails = _ar.getInfo();
//        if (gameDetails.contains("GameServer"));
//        {
            game1 = loadgame(gameDetails, game1);
            ////////////////////////////////////////////////////////////////////////
            gh.drawString("Level: " + game1.getLeavel(), 200, 45);
            int numOf_pokemons = game1.getNum_of_pokemons();
            int numOf_agents = game1.getagents();
            int numOf_moves = game1.getmoves();
            int grade = game1.getgrade();
            gh.drawString("number of pokemons: " + numOf_pokemons, 300, 45);
            gh.drawString("number of agents: " + numOf_agents, 500, 45);
            gh.drawString("\nnumber of moves: " + numOf_moves, 700, 45);
            gh.drawString("grade: " + grade, 900, 45);
        }



    public static LinkedList<Agent> loadAgents(String  agentsStr, DirectedWeightedGraph g, LinkedList<Agent> agents) {
        LinkedList<Agent> a= new LinkedList<Agent>();
        JSONObject ob;
        if (!agentsStr.contains("Agents")){
            return agents;
        }
        try {
            ob = new JSONObject(agentsStr);
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
                a.add(p);
            }
        } catch (JSONException e){
            e.printStackTrace();
            System.out.println("___ "+agentsStr);
        }

        return a;
    }

    public static LinkedList<Pokemon> loadPokemons(String  pokemonsStr, DirectedWeightedGraph g, LinkedList<Pokemon> pokemons) {
        LinkedList<Pokemon> a= new LinkedList<Pokemon>();
        JSONObject obj;
        if(!pokemonsStr.contains("Pokemons")){
            return pokemons;
        }
        try {
            obj = new JSONObject(pokemonsStr);
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
                a.add(p);
            }
        } catch (JSONException e){
            e.printStackTrace();
            System.out.println("___ "+pokemonsStr);
        }
        return a;
    }

    public static DirectedWeightedGraph loadgraph(String  graphStr, DirectedWeightedGraph g) {
        DirectedWeightedGraph a= new DirectedWeightedGraphImpl();
        JSONObject obje;
        if(!graphStr.contains("Edges")){
            return g;
        }
        try {
            obje= new JSONObject(graphStr);
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
                a.addNode(curr);}
            for (int i = 0; i < e; ++i) {
                JSONObject ed = edges.getJSONObject(i);
                int src = ed.getInt("src");
                double weight = ed.getDouble("w");
                int dest = ed.getInt("dest");
                a.connect(src, dest, weight);
            }
        } catch (JSONException e){
            e.printStackTrace();
            System.out.println("___ "+graphStr);
        }
        return a;
    }

    public static Game loadgame(String  InfoStr, Game game1) {
        Game a= new Game();
        if(!InfoStr.contains("GameServer")){
            return game1;
        }
        JSONObject object;
        try {
            object = new JSONObject(InfoStr);
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
            a=The_Game;
        } catch (JSONException e){
            e.printStackTrace();
            System.out.println("___ "+ InfoStr);
        }

        return a;
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

}