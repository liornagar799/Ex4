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


    public static EdgeData findTheEdge(GeoLocation p, int src1, int dest1, DirectedWeightedGraph g) {
        GeoLocation src = g.getNode(src1).getLocation();
        GeoLocation dest = g.getNode(dest1).getLocation();
        boolean ans = false;
        double dist = src.distance(dest);
        double d1 = src.distance(p) + p.distance(dest);
        if(dist>d1-EPS) {ans = true;}
        if (ans==true){
            return g.getEdge(src1,dest1);
        }else{
            return null;
        }
    }

    

    public String toString() {return "F:{v="+value+", t="+type+"}";}

    public EdgeData get_edge() {
        return edge;
    }

    public void set_Id(int e) {
        this.id = e;
    }

    public GeoLocation getLocation() {
        return pos;
    }
    
    public int getId() {
        return this.id;
    }
    
    public double getValue() {
        return value;
    }

    public void setIsEaten(boolean isIt) {
        this.IsEaten = isIt;
    }

    public boolean getIsEaten() {
        return IsEaten;
    }

    public void setWhoEatMe(Agent whoEatMe) {this.WhoEatMe = whoEatMe;}





}
