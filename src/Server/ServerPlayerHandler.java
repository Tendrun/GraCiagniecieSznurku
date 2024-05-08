package Server;

import java.io.*;
import java.net.Socket;

import Game.Game;

public class ServerPlayerHandler {

    Socket playerSocket;
    ObjectInputStream in;
    ObjectOutputStream out;
    Game game;
    ServerStreamOutput playerStreamOutput;
    ServerStreamInput playerStreamInput;

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
        playerStreamOutput = new ServerStreamOutput(out, game);
        playerStreamOutput.start();
        playerStreamInput = new ServerStreamInput(in, game);
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
