package MainProgram;

import java.io.*;
import java.net.Socket;

import DataPattern.GameSendPacket;
import DataPattern.PlayerSendPacket;

public class ServerPlayerHandler extends Thread {

    Socket playerSocket;
    ObjectInputStream in;
    ObjectOutputStream out;
    Game game;

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

    @Override
    public void run() {
        while(true){
            waitForPlayerInput();
            sendToPlayerChanges();
        }
    }

    void waitForPlayerInput(){
        System.out.println("Wait for player input");
        try {
            PlayerSendPacket ReceivedPacket = (PlayerSendPacket) in.readObject();
            game.pullLine(ReceivedPacket.linePullForce, ReceivedPacket.gameState);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    void sendToPlayerChanges() {
        try {
            GameSendPacket gameSendPacket = new GameSendPacket(game.line);
            out.writeObject(gameSendPacket);
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
