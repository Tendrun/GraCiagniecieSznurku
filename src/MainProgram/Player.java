package MainProgram;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import DataPattern.GameSendPacket;
import DataPattern.PlayerSendPacket;

public class Player {

    Socket clientSocket;
    ObjectOutputStream out;
    ObjectInputStream in;
    int port;


    public enum Team {
        left, right;
    }
    int linePullForce = 1;
    int line;
    PlayerSendPacket gameUpdateData;

    int msDelay;

    Player(Team team, Socket clientSocket, int port, int msDelay) {
        this.clientSocket = clientSocket;
        this.port = port;
        this.msDelay = msDelay;
        gameUpdateData = new PlayerSendPacket(linePullForce, team);
    }

    public void connectToServer() {
        try {
            InetAddress inetAddress = InetAddress.getByName("localhost");
            SocketAddress socketAddress = new InetSocketAddress(inetAddress, port);
            clientSocket.connect(socketAddress);
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            out.flush();
        }
        catch (IOException e){
            System.out.println(e);
        }
    }


    public void startPlaying() {
        while(true){
            pullLine();
            UpdateBar();
            try {
                Thread.sleep(msDelay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    void pullLine() {
        try {
            out.writeObject(gameUpdateData);
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void UpdateBar(){
        while(true) {
            try {
                in = new ObjectInputStream (clientSocket.getInputStream());
                GameSendPacket ReceivedPacket = (GameSendPacket) in.readObject();
                line = ReceivedPacket.line;
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
