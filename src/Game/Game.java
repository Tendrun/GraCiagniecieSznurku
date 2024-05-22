package Game;

import DataPattern.GameStatePacket;
import Player.Player;
import Player.UI.*;
import Server.Server;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.net.DatagramPacket;
import java.util.concurrent.atomic.AtomicInteger;

public class Game {
    private final AtomicInteger line = new AtomicInteger(0);
    int winThreshold;
    Server server;
    public Game(int winThreshold, Server server){
        this.winThreshold = winThreshold;
        this.server = server;
    }

    public enum GameState {
        LeftWon, RightWon, GameIsOn
    }

    GameState currentGameState = GameState.GameIsOn;

    public GameStatePacket getGameState(){
        return new DataPattern.GameStatePacket(line.get(), currentGameState);
    }

    public void pullLine(int amount, Player.Team team){
        if(currentGameState == GameState.LeftWon || currentGameState == GameState.RightWon) return;
        if(team == Player.Team.left) line.addAndGet(-amount);
        else if(team == Player.Team.right) line.addAndGet(amount);

        System.out.println("line = " + line);
        server.sendUpdateToPlayers();

        checkWinner();
    }

    void checkWinner(){
        if(line.get() >= winThreshold) {
            currentGameState = GameState.RightWon;
            System.out.println("RIGHT TEAM WINSS !!!!!!!!!!");
        }
        else if(line.get() <= -winThreshold) {
            currentGameState = GameState.LeftWon;
            System.out.println("LEFT TEAM WINSSS !!!!!!!!!");
        }
    }
}
