package Server;

import DataPattern.PlayerStatePacket;
import Game.Game;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ServerStreamInput extends Thread {

    ObjectInputStream in;
    Game game;

    public ServerStreamInput(ObjectInputStream in, Game game) {
        this.in = in;
        this.game = game;
    }
    @Override
    public void run() {
        waitForPlayerInput();
    }

    void waitForPlayerInput(){
        try {
            while(true) {
                PlayerStatePacket ReceivedPacket = (PlayerStatePacket) in.readObject();
                game.pullLine(ReceivedPacket.linePullForce, ReceivedPacket.gameState);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (IOException e){
            try {
                in.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
