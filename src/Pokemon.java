import api.DirectedWeightedGraph;
import api.EdgeData;
import api.GeoLocation;

import java.util.Iterator;


public class Pokemon {
    public static final double EPS1 = 0.001, EPS2=EPS1*EPS1;
    private double value;
    private int id;
    private int type;
    private GeoLocation pos;
    private DirectedWeightedGraph graph;
    //////////////////////////////
    private boolean lockedIn;
    private EdgeData edge;
    private boolean isEaten;
    private Agent eatenBy;


    public Pokemon(GeoLocation p, int t, double v,DirectedWeightedGraph g) {
        type = t;
        value = v;
        lockedIn = false;
        pos = p;
        this.graph=g;
        Iterator<EdgeData> ed= g.edgeIter();
        while (ed.hasNext()) {
            EdgeData e = ed.next();
            int s = e.getSrc();
            int d = e.getDest();
            EdgeData w=isOnEdge(this.pos,s,d,graph);
            if (w!=null){
                this.edge=w;
            }
        }
    }


    public static EdgeData isOnEdge(GeoLocation p, GeoLocation src, GeoLocation dest,DirectedWeightedGraph g,int s, int d ) {
        boolean ans = false;
        double dist = src.distance(dest);
        double d1 = src.distance(p) + p.distance(dest);
        if(dist>d1-EPS2) {ans = true;}
        if (ans==true){
            return g.getEdge(s,d);
        }else{
            return null;
        }

    }


    public static EdgeData isOnEdge(GeoLocation p, int s, int d, DirectedWeightedGraph g) {
        GeoLocation src = g.getNode(s).getLocation();
        GeoLocation dest = g.getNode(d).getLocation();
        return isOnEdge(p,src,dest,g,s,d);
    }






//    public boolean load(String file) {
//        try {
//            GsonBuilder builder = new GsonBuilder();
//            builder.registerTypeAdapter(Pokemon.class, new PokemonJsonDeserializer());
//            Gson gson = builder.create();
//            FileReader reader = new FileReader(file);
//            Pokemon a = gson.fromJson(reader, (Type) Pokemon.class);
//            System.out.println(a.getType());
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }

    public String toString() {return "F:{v="+value+", t="+type+"}";}

    public EdgeData get_edge() {
        return edge;
    }

    public void set_edge(EdgeData _edge) {
        this.edge = _edge;
    }
    public void set_Pos(GeoLocation p) {
        this.pos=p;
    }
    public void set_Type(int e) {
        this.type = e;
    }
    public void set_Id(int e) {
        this.id = e;
    }
    public void set_Value(double e) {
        this.value = e;
    }

    public GeoLocation getLocation() {
        return pos;
    }

    public int getType() {
        return type;
    }

    public int getId() {
        return this.id;
    }
    public double getValue() {
        return value;
    }

    public void setIsEaten(boolean isIt) {
        this.isEaten = isIt;
    }

    public boolean getIsEaten() {
        return isEaten;
    }
    public Agent getEatenBy() {
        return eatenBy;
    }

    public void setEatenBy(Agent eatenBy) {
        this.eatenBy = eatenBy;
    }

    public void setLockedIn(boolean c){ this.lockedIn = c;}
    public boolean isLockedIn(){ return lockedIn;}



}