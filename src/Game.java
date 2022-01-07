

public class Game {

private int Num_of_pokemons;
private int moves;
private int grade;
private int game_level;
private int id;
private int Num_of_agents;

    public Game() {
        this.Num_of_pokemons = -1;
        this.moves = 0;
        this.grade = 0;
        this.id = -1;
        this.Num_of_agents = -1;
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

    public int getagents() {
        return  this.Num_of_agents;
    }

    public int getLeavel() {
        return  this.game_level;
    }

    public int getgrade() {return  this.grade;}

    public void setId(int s) {this.id= s;}

    public void setNum_of_pokemons(int s) {
        this.Num_of_pokemons= s;
    }

    public void setmoves(int s) {
        this.moves= s;
    }

    public void setagents(int s) {this.Num_of_agents = s;}

    public void setgrade(int s) {
        this.grade= s;
    }

    public void setGame_level(int s) {
        this.game_level= s;
    }


}
