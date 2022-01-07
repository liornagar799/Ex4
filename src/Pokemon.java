import api.DirectedWeightedGraph;
import api.EdgeData;
import api.GeoLocation;
import java.util.Iterator;


public class Pokemon {
    public static double EPS = 0.000001;
    private double value;
    private int id;
    private int type;
    private GeoLocation pos;
    private DirectedWeightedGraph graph;
    private EdgeData edge;
    private Agent WhoEatMe;
    private boolean IsEaten;


//A contracture that initializes the Pokemon with data
    public Pokemon(GeoLocation po, int ty, double v,DirectedWeightedGraph g) {
        type = ty;
        value = v;
        pos = po;
        this.graph=g;
        Iterator<EdgeData> ed= g.edgeIter();
        while (ed.hasNext()) {
            EdgeData e = ed.next();
            int src = e.getSrc();
            int dest = e.getDest();
            EdgeData w= findTheEdge(this.pos,src,dest,graph);
            if (w!=null){
                this.edge=w;
            }
        }
    }

//The function gets a Pokemon position and returns the side it is on
    public static EdgeData findTheEdge(GeoLocation p, int src1, int dest1, DirectedWeightedGraph g) {
        GeoLocation src = g.getNode(src1).getLocation();
        GeoLocation dest = g.getNode(dest1).getLocation();
        boolean ans = false;
        double dist = src.distance(dest);
        double d1 = src.distance(p) + p.distance(dest);
        //Distance check
        if(dist>d1-EPS) {ans = true;}
        if (ans==true){
            //Returns the side that the Pokemon is on
            return g.getEdge(src1,dest1);
        }else{
            return null;
        }
    }

    
    //Function that returns EdgeData
    public EdgeData get_edge() {
        return edge;
    }
    //Function that change the id of the pokemon
    public void set_Id(int e) {
        this.id = e;
    }
    //Function that returns the position of the pokemon
    public GeoLocation getLocation() {
        return pos;
    }
    //Function that returns the id of the pokemon
    public int getId() {
        return this.id;
    }
    //Function that returns the value of the pokemon
    public double getValue() {
        return value;
    }
  //Function that change value if the pokemon eaten by an agent
    public void setIsEaten(boolean isIt) {
        this.IsEaten = isIt;
    }
    //Function that returns if the pokemon eaten by an agent
    public boolean getIsEaten() {
        return IsEaten;
    }
    //A function that updates which agent ate the Pokemon
    public void setWhoEatMe(Agent whoEatMe) {this.WhoEatMe = whoEatMe;}





}
