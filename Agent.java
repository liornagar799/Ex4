import api.*;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;


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
    private long _sg_dt;
    private Pokemon gointTo;


    public Agent(DirectedWeightedGraph g, int start_node) {
        gg = g;
        this.curr_node = g.getNode(start_node);
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

    public double getSpeed() {
        return speed;
    }

    public void setId(int i) {
        id = i;
    }

    public void setValue(double val) {
        value = val;
    }


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

    public int getDest() {
        int ans = -2;
        if (this.curr_edge == null) {
            ans = -1;
        } else {
            ans = this.curr_edge.getDest();
        }
        return ans;
    }


    public boolean setDest(int dest) {
        boolean ans = false;
        int src = this.curr_node.getKey();
        this.curr_edge = gg.getEdge(src, dest);
        this.dest=dest;
        if (curr_edge != null) {
            ans = true;
        } else {
            curr_edge = null;
        }
        return ans;
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

    public long get_sg_dt() {
        return _sg_dt;
    }

        public void setGointTo (Pokemon p ){
        this.gointTo = p;
    }

 public boolean on_node (){
     Iterator<NodeData> it = gg.nodeIter();
     while ((it.hasNext())){
       NodeData t = it.next();
       if (t.getLocation().x()==this.pos.x() &&t.getLocation().y()==this.pos.y()){
           System.out.println("_________על קטדקוד__________");
           return true;

       }
     }
     System.out.println("_________על צלע__________");
     return false;
 }

    public int findNearPokpath(List<Pokemon> poks, DirectedWeightedGraphAlgorithms ga) {
        Pokemon chosen = poks.contains(gointTo) ? gointTo : null;
        int tempNextNode = 0;
        int nextNode;
        double minEff = Double.MAX_VALUE;
        double tempEff;
        double combinedValue = 0;
        double currDist;
        int nn;

        List<Pokemon> filteredList;
        filteredList = poks.stream().filter(p -> !p.getIsEaten()).collect(Collectors.toList());
        if(this.gointTo != null && poks.contains(this.gointTo)){
            filteredList.add(this.gointTo);
        }

        for (int i = 0; i <filteredList.size(); i++) {
            nn = filteredList.get(i).get_edge().getSrc();
            combinedValue = filteredList.get(i).getValue();
            if (filteredList.get(i).get_edge().getSrc() == this.getSrc()) {
                nn = filteredList.get(i).get_edge().getDest();

                //If we see some consecutive edges that on each one there is a pokemon that on our opposite way.
                //In this case we run to the last edge and then run backwards.
                for(int j=0; j<poks.size(); j++) {
                    if (poks.get(j).get_edge().getDest() == filteredList.get(i).get_edge().getSrc()) {
                        this.setGointTo(poks.get(j));
                        poks.get(j).setEatenBy(this);
                        return poks.get(j).get_edge().getSrc();
                    }
                }
                this.set_curr_fruit(filteredList.get(i));
                this.setGointTo(filteredList.get(i));
                this.setDest(nn);
                return nn;
            }


            //If there is two ot more pokemon's on the same edge, the trip will be more valuable.
            //moreover, we will update flags if there is another node at this edge.
            for (int j = 0; j< poks.size(); j++) {
                if (!poks.get(j).equals(filteredList.get(i))) {
                    if (poks.get(j).get_edge().getSrc() == filteredList.get(i).get_edge().getSrc()
                            && poks.get(j).get_edge().getDest() == filteredList.get(i).get_edge().getDest()) {
                        combinedValue += poks.get(j).getValue();
                        if (poks.get(j).getIsEaten()) {
                            filteredList.get(i).setEatenBy(this);
                        }
                        else {
                            poks.get(j).setIsEaten(true);
                        }
                    }
                }
            }
            List<NodeData> path = ga.shortestPath(this.getSrc(), nn);
            for (int k=0; k<path.size();k++){
                System.out.print(path.get(k).getKey()+"==");
            }
            System.out.println();
            currDist = filteredList.get(i).get_edge().getWeight();


            tempEff = (currDist/combinedValue) ;
            if (tempEff < minEff) {
                minEff = tempEff;
                chosen = filteredList.get(i);
                tempNextNode = path.get(1).getKey();
            }
        }

        int chosenIndex = poks.indexOf(chosen);

        if(chosenIndex == -1){
            chosenIndex =  0;
        }

        //If this agent has a target but he find a different one.
        //We need to change flags.
        if (!poks.get(chosenIndex).equals(this.gointTo)) {
            if (this.gointTo != null && poks.contains(this.gointTo)) {
                int l = poks.indexOf(this.gointTo);
                poks.get(l).setIsEaten(false);
                poks.get(l).setEatenBy(null);
            }
        }
        //Update flags.
        poks.get(chosenIndex).setIsEaten(true);
        poks.get(chosenIndex).setEatenBy(this);
        this.setGointTo(poks.get(chosenIndex));
        this.gointTo = poks.get(chosenIndex);

        nextNode = tempNextNode;
        this.setDest(nextNode);
        if(nextNode == 0 || nextNode == 22){
            System.out.println(this.get_sg_dt());
        }
        System.out.println(nextNode);
        return nextNode;
    }

    public int getNextNode() {
        int ans;
        if (this.curr_edge == null) {
            ans = -1;
        } else {
            ans = this.curr_edge.getDest();
        }
        return ans;
    }



}