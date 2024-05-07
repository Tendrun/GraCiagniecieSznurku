package Game;

import DataPattern.GameStatePacket;
import Player.Player;
import Server.Server;

import java.net.DatagramPacket;

public class Game {
    static int line = 0;
    int winThreshold;
    Server server;

    public Game(int winThreshold){
        this.winThreshold = winThreshold;
    }


    public enum GameState {
        LeftWon, RightWon, GameIsOn
    }

    GameState currentGameState = GameState.GameIsOn;

    public GameStatePacket getGameState(){
        return new DataPattern.GameStatePacket(line, currentGameState);
    }

    public void pullLine(int amount, Player.Team team){
        if(currentGameState == GameState.LeftWon || currentGameState == GameState.RightWon) return;

        if(team == Player.Team.left) line -= amount;
        else if(team == Player.Team.right) line += amount;
        System.out.println("line = " + line);
        System.out.println("amount = " + amount + "Player team" + team);
        checkWinner();
    }

    void checkWinner(){
        if(line > winThreshold) {
            currentGameState = GameState.RightWon;
            System.out.println("RIGHT TEAM WINSS !!!!!!!!!!");
        }
        else if(line < -winThreshold) {
            currentGameState = GameState.LeftWon;
            System.out.println("LEFT TEAM WINSSS !!!!!!!!!");
        }
    }
}
