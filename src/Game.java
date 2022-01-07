
public class Game {

    private int Num_of_pokemons;
    private int moves;
    private int grade;
    private int game_level;
    private int id;
    private int Num_of_agents;
    //Empty contracture of a game
    public Game() {
        this.Num_of_pokemons = -1;
        this.moves = 0;
        this.grade = 0;
        this.id = -1;
        this.Num_of_agents = -1;
    }
    //Function that returns the id
    public int getId() {
        return this.id;
    }
    //Function that returns the num of pokemons
    public int getNum_of_pokemons() {
        return  this.Num_of_pokemons;
    }
    //Function that returns the moves of the agent
    public int getmoves() {
        return this.moves;
    }
    //Function that returns the num of agents
    public int getagents() {
        return  this.Num_of_agents;
    }
    //Function that returns the level of the game
    public int getLeavel() {
        return  this.game_level;}

    //Function that returns the grade
    public int getgrade() {return  this.grade;}

    //Function that change id of the game
    public void setId(int s) {this.id= s;}

    //Function that change the num of pokemons
    public void setNum_of_pokemons(int s) {
        this.Num_of_pokemons= s;
    }
    //Function that change the num of moves
    public void setmoves(int s) {
        this.moves= s;
    }
    //Function that change the num of agents
    public void setagents(int s) {this.Num_of_agents = s;}

    //Function that change the grade
    public void setgrade(int s) {
        this.grade= s;
    }

    //Function that change the level of the game
    public void setGame_level(int s) {
        this.game_level= s;
    }


}