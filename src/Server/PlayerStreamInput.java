package Server;

import DataPattern.PlayerStatePacket;
import Game.Game;

import java.io.IOException;
import java.io.ObjectInputStream;

public class PlayerStreamInput extends Thread {

    ObjectInputStream in;
    Game game;

    public PlayerStreamInput(ObjectInputStream in, Game game) {
        this.in = in;
        this.game = game;
    }
    @Override
    public void run() {
        while(true){
            waitForPlayerInput();
        }
    }

    void waitForPlayerInput(){
        try {
            PlayerStatePacket ReceivedPacket = (PlayerStatePacket) in.readObject();
            game.pullLine(ReceivedPacket.linePullForce, ReceivedPacket.gameState);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
