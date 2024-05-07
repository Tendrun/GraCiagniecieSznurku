package Server;

import java.io.*;
import java.net.Socket;
import DataPattern.GameStatePacket;
import DataPattern.PlayerStatePacket;
import Game.Game;

public class ServerPlayerHandler {

    Socket playerSocket;
    ObjectInputStream in;
    ObjectOutputStream out;
    Game game;
    PlayerStreamOutput playerStreamOutput;
    PlayerStreamInput playerStreamInput;

    public ServerPlayerHandler(Socket playerSocket, Game game){
        this.playerSocket = playerSocket;
        this.game = game;
        try {
            in = new ObjectInputStream(playerSocket.getInputStream());
            out = new ObjectOutputStream(playerSocket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void start(){
        playerStreamOutput = new PlayerStreamOutput(out, game);
        playerStreamOutput.start();
        playerStreamInput = new PlayerStreamInput(in, game);
        playerStreamInput.start();
    }

    public void disconnectPlayer(){
        playerStreamOutput.interrupt();
        playerStreamInput.interrupt();
        try {
            in.close();
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
