

public class Game {
private int Num_of_pokemons;
private boolean is_logged;
private int moves;
private int grade;
private int game_level;
private int max_level;
private int id;
private String graph;
private int agents;

    public Game() {
        this.Num_of_pokemons = -1;
        this.moves = 0;
        this.grade = 0;
        this.id = -1;
        this.graph = "";
        this.agents = -1;
    }

    public int getId() {
        return this.id;
    }
    public int getNum_of_pokemons() {
        return  this.Num_of_pokemons;
    }
    public int getmoves() {
        return this.moves;
    }
    public String getgraph() {
        return  this.graph;
    }
    public int getagents() {
        return  this.agents;
    }
    public int getLeavel() {
        return  this.game_level;
    }

    public int getgrade() {
        return  this.grade;
    }


    public void setId(int s) {
        this.id= s;
    }
    public void setNum_of_pokemons(int s) {
        this.Num_of_pokemons= s;
    }
    public void setmoves(int s) {
        this.moves= s;
    }
    public void setagents(int s) {this.agents= s;}
    public void setgrade(int s) {
        this.grade= s;
    }
    public void setgraph(String s) {
        this.graph= s;
    }
}
