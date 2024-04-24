package MainProgram;

public class Game {

    int line = 0;
    int threshold = 100;

    public enum GameState {
        LeftWon, RightWon, GameIsOn
    }

    GameState currentGameState = GameState.GameIsOn;

    public void pullLine(int amount, Player.Team team){
        if(team == Player.Team.left) line -= amount;
        else if(team == Player.Team.right) line += amount;



        System.out.println("line = " + line);
        checkWinner();
    }

    void checkWinner(){
        if(line > threshold) {
            currentGameState = GameState.RightWon;
            System.out.println("RIGHT TEAM WINSS !!!!!!!!!!");
        }
        else if(line < -threshold) {
            currentGameState = GameState.LeftWon;
            System.out.println("LEFT TEAM WINSSS !!!!!!!!!");
        }
    }

}
