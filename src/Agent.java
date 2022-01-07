import api.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


public class Agent {
    private int id;
    private double value;
    private int src;
    private int dest;
    private double speed;
    private GeoLocation pos;
    private NodeData node;
    private EdgeData edge;
    private DirectedWeightedGraph g;
    private Pokemon whoIeat;
    private Pokemon pokemon;

    //A contracture that initializes the Agent with data
    public Agent(DirectedWeightedGraph g, int node) {
        this.g = g;
        this.node = g.getNode(node);
        this.speed = 0;
        this.src = -1;
        this.dest = -1;
        pos = this.node.getLocation();
        id = -1;
    }

    //Function that returns the id of the Agent
    public int getId() {
        return id;
    }

    //Function that returns the id of the Graph
    public DirectedWeightedGraph getGraph() {
        return this.g;
    }

    //Function that returns the value of the Agent
    public double getValue() {
        return value;
    }

    //Function that change the id of the Agent
    public void setId(int i) {
        id = i;
    }

    //Function that change the value of the Agent
    public void setValue(double val) {
        value = val;
    }

    //Function that change the speed of the Agent
    public void setSpeed(double speed) {
        speed = speed;
    }

    //Function that change the pos of the Agent
    public void setPos(GeoLocation p) {
        pos = p;
    }

    //Function that return the pos of the Agent
    public GeoLocation getPos() {
        return this.pos;
    }

    //Function that return the pokmen that the Agent eaten
    public void setWhoIeat(Pokemon pok) {
        this.whoIeat = pok;
    }

    //Function that return the src of the Agent
    public int getSrc() {
        return this.node.getKey();
    }

    //Function that return the dest of the Agent 
    public int getDest() {
        if (this.edge == null) { return -1;}
        else { return this.edge.getDest();}
    }

    //Function that change the dest of the Agent 
    public void setDest(int dest) {
        int src = this.node.getKey();
        this.edge = g.getEdge(src, dest);
        this.dest = dest;
    }

    //Function that change the src of the Agent
    public void setSrc(int src) {
        this.src = src;
    }

    //Function that change pokemon
    public void setPokemon(Pokemon pok) {
        this.pokemon = pok;
    }

    //Function that return if the agent is on node and not moved
    public boolean on_node() {
        Iterator<NodeData> it = g.nodeIter();
        while ((it.hasNext())) {
            NodeData t = it.next();
            if (t.getLocation().x() == this.pos.x() && t.getLocation().y() == this.pos.y()) {
                return true;

            }
        }
        return false;
    }

    // calculate the paths for the pokemons and return the next node for the agent
    public int findNearPokpath(LinkedList<Pokemon> pokemons, DirectedWeightedGraphAlgorithms g) {
        Pokemon cnt=null;
        if (pokemons.contains(this.pokemon)){
            cnt=this.pokemon;
        }
        int NextNode = 0;
        double min = Double.MAX_VALUE;
        int current;

        //A common use case of the filter() method is processing collections.
        //we used this web https://www.baeldung.com/java-stream-filter-lambda
        List<Pokemon> cnt_list = pokemons.stream().filter(p -> !p.getIsEaten()).collect(Collectors.toList());
        int sizeList= cnt_list.size();
        if(cnt != null){
            cnt_list.add(this.pokemon);
        }
        //loop on the all the Pokemons
        for (int i = 0; i <sizeList; i++) {
            current = cnt_list.get(i).getedge().getSrc();
            double theGrade = cnt_list.get(i).getValue();
            if (current == this.getSrc()) {
                current = cnt_list.get(i).getedge().getDest();

                //If the server added a Pokemon on the side the agent is already on
                for(int j=0; j<pokemons.size(); j++) {
                    int src = cnt_list.get(i).getedge().getSrc();
                    int dest = pokemons.get(j).getedge().getDest();
                    if (src==dest) {
                        this.setPokemon(pokemons.get(j));
                        pokemons.get(j).setWhoEatMe(this);
                        int newSrc= pokemons.get(j).getedge().getSrc();
                        return newSrc;
                    }
                }
                this.setPokemon(cnt_list.get(i));
                this.setWhoIeat(cnt_list.get(i));
                return current;
            }


            //If there is more than one Pokemon on Edge
            for (int j = 0; j< pokemons.size(); j++) {
                if (pokemons.get(j).equals(cnt_list.get(i))==false) {
                    //creating variables for the src and dest
                    int srcPokemons=pokemons.get(j).getedge().getSrc();
                    int srcList =cnt_list.get(i).getedge().getSrc();
                    int destPokemons=pokemons.get(j).getedge().getDest();
                    int destList =cnt_list.get(i).getedge().getDest();
                    if (srcList== srcPokemons && destList==destPokemons){
                        //sum the grade
                        theGrade += pokemons.get(j).getValue();
                        if (pokemons.get(j).getIsEaten()) {
                            cnt_list.get(i).setWhoEatMe(this);
                        }
                        else {
                            pokemons.get(j).setIsEaten(true);
                        }}}}
            //list for the short path
            List<NodeData> shortPath = g.shortestPath(this.getSrc(), current);
            double distance = g.getGraph().getNode(current).getWeight()+cnt_list.get(i).getedge().getWeight();
            double calculate = distance/theGrade;
            //saving the minimum for move the agent to there
            if (calculate < min) {
                min = calculate;
                cnt = cnt_list.get(i);
                NextNode = shortPath.get(1).getKey();
            }
        }
        int indx=pokemons.indexOf(cnt);
        if(indx==-1){
            indx=0;
        }
        //changing the variables for the eating of the pokemons
        pokemons.get(indx).setIsEaten(true);
        pokemons.get(indx).setWhoEatMe(this);
        this.setPokemon(pokemons.get(indx));
        this.pokemon = pokemons.get(indx);
        //return the next node for the agents
        return NextNode;
    }

}
