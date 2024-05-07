package Player;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import DataPattern.GameStatePacket;
import DataPattern.PlayerStatePacket;

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
    PlayerStatePacket gameUpdateData;
    int msDelay;

    public Player(Team team, Socket clientSocket, int port, int msDelay) {
        this.clientSocket = clientSocket;
        this.port = port;
        this.msDelay = msDelay;
        gameUpdateData = new PlayerStatePacket(linePullForce, team);
    }

    public void connectToServer() {
        try {
            InetAddress inetAddress = InetAddress.getByName("localhost");
            SocketAddress socketAddress = new InetSocketAddress(inetAddress, port);
            clientSocket.connect(socketAddress);
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            out.flush();
            in = new ObjectInputStream (clientSocket.getInputStream());
        }
        catch (IOException e){
            System.out.println(e);
        }
    }

    public void disconnectFromServer(){
        try {
            out.close();
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
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
        try {
            GameStatePacket ReceivedPacket = (GameStatePacket) in.readObject();
            line = ReceivedPacket.line;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
