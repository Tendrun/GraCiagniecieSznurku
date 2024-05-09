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
        playerStreamOutput = new ServerStreamOutput(out, game, this);
        //playerStreamOutput.start();
        playerStreamInput = new ServerStreamInput(in, game, this);
        playerStreamInput.start();
    }

    public void disconnectPlayer(){
        try {
            in.close();
            out.close();
        } catch (IOException e) {
            System.out.println("Player disconnected");
        }
        //playerStreamOutput.interrupt();
        playerStreamInput.interrupt();
    }
}
