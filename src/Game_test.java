import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class Game_test {
    Game g=new Game();


    @Test
    public void getNum_of_pokemons() {
        assertEquals(g.getNum_of_pokemons(),-1);
    }

    @Test
    public void getagents() {
        assertEquals(g.getagents(),-1);
    }

    @Test
    public void getId() {
        assertEquals(g.getId(),-1);
    }

    @Test
    public void getmoves() {
        assertEquals(g.getmoves(),0);
    }

    @Test
    public void getgrade() {
        assertEquals(g.getgrade(),0);
    }





}

