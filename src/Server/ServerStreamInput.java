package Server;

import DataPattern.PlayerStatePacket;
import Game.Game;

import java.io.EOFException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;

public class ServerStreamInput extends Thread {

    ObjectInputStream in;
    Game game;
    ServerPlayerHandler serverPlayerHandler;
    public ServerStreamInput(ObjectInputStream in, Game game, ServerPlayerHandler serverPlayerHandler) {
        this.in = in;
        this.game = game;
        this.serverPlayerHandler = serverPlayerHandler;
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
        } catch (InvalidClassException e){
            System.err.println("InvalidClassException :(((");
        } catch (ClassNotFoundException e) {
            System.err.println("PAKIETTTT");
        } catch (IOException e) {
            serverPlayerHandler.disconnectPlayer();
        }
    }
}
