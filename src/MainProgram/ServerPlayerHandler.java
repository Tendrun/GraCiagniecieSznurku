package MainProgram;

import java.io.*;
import java.net.Socket;

import DataPattern.GameSendPacket;
import DataPattern.PlayerSendPacket;

public class ServerPlayerHandler extends Thread {

    Socket playerSocket;
    int line;
    ObjectInputStream in;
    ObjectOutputStream out;

    public ServerPlayerHandler(Socket playerSocket, int line){
        this.playerSocket = playerSocket;
        this.line = line;
        try {
            in = new ObjectInputStream(playerSocket.getInputStream());
            out = new ObjectOutputStream(playerSocket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        waitForPlayerInput();`
        sendToPlayerChanges();
    }

    void waitForPlayerInput(){
        System.out.println("Wait for player input");
        try {
            PlayerSendPacket ReceivedPacket = (PlayerSendPacket) in.readObject();
            System.out.println(ReceivedPacket);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    void sendToPlayerChanges() {
        try {
            GameSendPacket gameSendPacket = new GameSendPacket(line);
            out.writeObject(gameSendPacket);
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
