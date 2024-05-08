package DataPattern;

import Game.Game;

import java.io.Serializable;

public class GameStatePacket implements Serializable {
    public int line;
    public Game.GameState currentGameState;

    public GameStatePacket(int line, Game.GameState currentGameState){
        this.line = line;
        this.currentGameState = currentGameState;
    }
}
