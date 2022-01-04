import api.DirectedWeightedGraph;
import api.EdgeData;
import api.GeoLocation;
import api.NodeData;


public class Agent {
    private int id;
    private double value;
    private int src;
    private int dest;
    private double speed;
    private GeoLocation pos;
    ///////////////////////////////////////////////
    private NodeData curr_node;
    private EdgeData curr_edge;
    private DirectedWeightedGraph gg;
    private Pokemon _curr_fruit;


    public Agent(DirectedWeightedGraph g, int start_node) {
        gg = g;
        this.curr_node = gg.getNode(start_node);
        pos = curr_node.getLocation();
        id = -1;
        setSpeed(0);
    }


    public int getId() {
        return id;
    }

    public DirectedWeightedGraph getGraph() {
        return this.gg;
    }

    public double getValue() {
        return value;
    }

//    public int getSrc() {
//        return src;
//    }

//    public int getDest() {
//        return dest;
//    }

    public double getSpeed() {
        return speed;
    }

    public void setId(int i) {
        id = i;
    }

    public void setValue(double val) {
        value = val;
    }

//    public void setSrc(int sr) {
//        src = sr;
//    }

//    public void setDest(int des) {
//        dest = des;
//    }

    public void setSpeed(double spe) {
        speed = spe;
    }


    public void setPos(GeoLocation s) {
        pos = s;
    }

    public GeoLocation getPos() {
        return this.pos;
    }

    public Pokemon get_curr_fruit() {
        return _curr_fruit;

    }

    public void set_curr_fruit(Pokemon curr_fruit) {
        this._curr_fruit = curr_fruit;
    }


//    public boolean load(String file) {
//        try {
//            GsonBuilder builder = new GsonBuilder();
//            builder.registerTypeAdapter(Agent.class, new AgentJsonDeserializer());
//            Gson gson = builder.create();
//            FileReader reader = new FileReader(file);
//            Agent a = gson.fromJson(reader, Agent.class);
//            System.out.println(a.id);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }
//
//    public String toJSON() {
//        String ans = "{\"Agent\":{"
//                + "\"id\":" + this.id + ","
//                + "\"value\":" + this.value + ","
//                + "\"src\":" + this.curr_node.getKey() + ","
//                + "\"dest\":" + this.getDest() + ","
//                + "\"speed\":" + this.getSpeed() + ","
//                + "\"pos\":\"" + pos.toString() + "\""
//                + "}"
//                + "}";
//        return ans;
//    }


    public int getSrc() {
        return this.curr_node.getKey();
    }

    public void setCurrNode(int src) {
        this.curr_node = gg.getNode(src);
    }

    public NodeData getCurrNode(int src) {
        return this.curr_node;
    }

    public boolean isMoving() {
        return this.curr_edge != null;
    }

//    public String toString() {
//        return toJSON();
//    }

    public String toString1() {
        String ans = "" + this.getId() + "," + pos + ", " + isMoving() + "," + this.getValue();
        return ans;
    }


    public int getDest() {
        int ans = -2;
        if (this.curr_edge == null) {
            ans = -1;
        } else {
            ans = this.curr_edge.getDest();
        }
        return ans;
    }


    public void setDest(int dest) {
        this.dest=dest;
    }

    public void setSrc(int src) {
        this.src=src;
    }

    public EdgeData get_curr_edge() {
        return this.curr_edge;
    }

    public void set_curr_edge(int dest) {
        int src = this.curr_node.getKey();
        this.curr_edge = gg.getEdge(src, dest);
    }


}