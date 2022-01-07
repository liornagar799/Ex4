import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphImpl;
import api.GeoLocation;
import api.GeoLocationImpl;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class Pokemon_test {
    DirectedWeightedGraph g = new DirectedWeightedGraphImpl();
    GeoLocation po= new GeoLocationImpl(1.1,2.2,0.0);
    Pokemon pok = new Pokemon(po,5,5.5,g);


    @Test
    public void getvalue() {
        assertEquals(pok.getValue(),5.5);
    }

    @Test
    public void getPos() {
        GeoLocation po= new GeoLocationImpl(1.1,2.2,0.0);
        assertEquals(pok.getLocation().x(),1.1);
        assertEquals(pok.getLocation().y(),2.2);
    }

    @Test
    public void getedge() {
        assertEquals(pok.getedge(),null);
    }

    @Test
    public void getIsEaten(){
        assertEquals(pok.getIsEaten(),false);
    }


}

