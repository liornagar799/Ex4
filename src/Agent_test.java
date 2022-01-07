import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphImpl;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class Agent_test {
    DirectedWeightedGraph g = new DirectedWeightedGraphImpl();
    Agent a=new Agent(g,0);


    @Test
    public void getvalue() {
        assertEquals(a.getId(),-1);
    }



}

