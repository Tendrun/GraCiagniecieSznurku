package DataPattern;

import Game.Game;

import java.io.Serializable;

public class GameStatePacket implements Serializable {
    public int line;
    Game.GameState currentGameState = Game.GameState.GameIsOn;

    public GameStatePacket(int line, Game.GameState currentGameState){
        this.line = line;
        this.currentGameState = currentGameState;
    }
}
