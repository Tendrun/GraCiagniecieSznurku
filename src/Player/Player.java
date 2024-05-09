package Player;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import DataPattern.GameStatePacket;
import DataPattern.PlayerStatePacket;
import Game.Game;

public class Player {

    Socket clientSocket;
    ObjectOutputStream out;
    ObjectInputStream in;
    int port;
    Game.GameState gameState;


    public enum Team {
        left, right;
    }
    int linePullForce = 1;
    int line;
    PlayerStatePacket playerStatePacket;
    int msDelay;
    PlayerStreamOutput playerStreamOutput;
    PlayerStreamInput playerStreamInput;

    public Player(Team team, Socket clientSocket, int port, int msDelay) {
        this.clientSocket = clientSocket;
        this.port = port;
        this.msDelay = msDelay;
        playerStatePacket = new PlayerStatePacket(linePullForce, team);
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

    public void startPlaying() {
        sendDataFromServer();
        readDataFromServer();
    }

    void readDataFromServer(){
        playerStreamInput = new PlayerStreamInput(in, this);
        playerStreamInput.start();
    }

    void sendDataFromServer(){
        playerStreamOutput = new PlayerStreamOutput(out, this, msDelay);
        playerStreamOutput.start();
    }

    public PlayerStatePacket getPlayerStatePacket(){
        return playerStatePacket;
    }

    void updatePlayerData(GameStatePacket ReceivedPacket){
        line = ReceivedPacket.line;
        gameState = ReceivedPacket.currentGameState;

        if(gameState == Game.GameState.RightWon || gameState == Game.GameState.LeftWon){
            gameOver();
        }
    }

    void gameOver(){
        disconnectFromServer();
        //playerStreamOutput.interrupt();
        //playerStreamInput.interrupt();
    }

    void disconnectFromServer(){
        try {
            out.close();
            in.close();
        } catch (IOException e) {

        }
    }
}
