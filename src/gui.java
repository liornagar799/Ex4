import api.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Iterator;


public class gui extends JFrame implements ActionListener, MouseListener {
    private Client client;



    public gui(Client c) {
        this.client=c;
        init();
    }

    private void init() {
        //set the size of the window
        this.setSize(800, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("The Game of Raz & Lior");
        this.addMouseListener(this);
        setVisible(true);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (this) {
                    }} } });
        thread.start();
    }

    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();

    }


    // this function draw the Graph
    public void paint(Graphics G,DirectedWeightedGraph Graph) {
        HashMap<Integer, Double> get_X = new HashMap<>(Graph.nodeSize());
        HashMap<Integer, Double> get_Y = new HashMap<>(Graph.nodeSize());
        Graphics2D graphics = (Graphics2D) G;
        super.paint(graphics);
        graphics.setStroke(new BasicStroke(2));
        super.paint(graphics);
        Iterator<NodeData> Nodes = Graph.nodeIter();
        while (Nodes.hasNext()) {
            NodeData node_data = Nodes.next();
            GeoLocation geo = node_data.getLocation();
            get_X.put(node_data.getKey(), geo.x());
            get_Y.put(node_data.getKey(), geo.y());
        }
        Normalization(get_X, 70, this.getWidth() - 70);
        Normalization(get_Y, 70, this.getHeight() - 70);
        Iterator<NodeData> nodesIter = Graph.nodeIter();
        while (nodesIter.hasNext()) {
            NodeData node_data = nodesIter.next();
            int key = node_data.getKey();
            int x = get_X.get(key).intValue();
            int y = get_Y.get(key).intValue();
            graphics.setColor(Color.BLACK);
            graphics.fillOval(x, y, 7 * 2, 7 * 2);
            graphics.setColor(Color.BLACK);
            graphics.drawString("" + key, x, y - 10);

        }
        Iterator<EdgeData> edgeIter = Graph.edgeIter();
        while (edgeIter.hasNext()) {
            EdgeData edge_data = edgeIter.next();
            GeoLocation Geo_src = Graph.getNode(edge_data.getSrc()).getLocation();
            GeoLocation Geo_dest = Graph.getNode(edge_data.getDest()).getLocation();
            graphics.setColor(Color.BLACK);
            graphics.drawLine((int) Geo_src.x(), (int) Geo_src.y(), (int) Geo_dest.x(), (int) Geo_dest.y());

        }

        Iterator<EdgeData> edgeIter1 = Graph.edgeIter();
        while (edgeIter1.hasNext()) {
            EdgeData edge1 = edgeIter1.next();
            int x1 = get_X.get(edge1.getSrc()).intValue();
            int y1 = get_Y.get(edge1.getSrc()).intValue();
            int x2 = get_X.get(edge1.getDest()).intValue();
            int y2 = get_Y.get(edge1.getDest()).intValue();
            //draw the edges
            graphics.setColor(Color.gray);
            graphics.drawLine(x1 + 10, y1 + 10, x2 + 10, y2 + 10);
            //draw the direction o
            graphics.setColor(Color.pink);
            graphics.fillOval(x2 + 7, y2 + 7, 7 * 2, 7 * 2);

        }
        //////paint all thr other//////////////
//        drawPokemons(G, Graph);
//        drawAgants(G,Graph);
//        draw_gameDetails(G,Graph);

    }



    //This function normalizes the graph drawing to the screen size
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


    @Override
    public void mouseClicked(MouseEvent arg0) {;}

    @Override
    public void mouseEntered(MouseEvent arg0) {;}

    @Override
    public void mouseExited(MouseEvent arg0) {;}

    @Override
    public void mousePressed(MouseEvent arg0) {;}

    @Override
    public void mouseReleased(MouseEvent arg0) {;}



}
