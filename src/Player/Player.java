package Player;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import DataPattern.GameStatePacket;
import DataPattern.PlayerStatePacket;
import Game.Game;

import javax.swing.*;

public class Player {
    Socket clientSocket;
    ObjectOutputStream out;
    ObjectInputStream in;
    int port;
    Game.GameState gameState;

    static PlayerUI playerUI;

    public enum Team {
        left, right;
    }
    Team team;

    int linePullForce = 1;
    int line = 0;
    PlayerStatePacket playerStatePacket;
    int msDelay;
    int msDelayOffset;
    PlayerStreamOutput playerStreamOutput;
    PlayerStreamInput playerStreamInput;

    String playerName;

    public Player(Team team, Socket clientSocket, int port, int msDelay, int msDelayOffset, String playerName) {
        this.playerName = playerName;
        this.clientSocket = clientSocket;
        this.port = port;
        this.msDelay = msDelay;
        this.msDelayOffset = msDelayOffset;
        playerStatePacket = new PlayerStatePacket(linePullForce, team);
        try {
            createUI(this);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String args[]){
        //Player.Team team = i < (float)amountOfPlayers/2 ? Player.Team.left : Player.Team.right;
        Socket clientSocket = new Socket();
        try {
            createUI();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        //System.out.println("Stworzono gracza " + i + " gra on w druzynie " + team);
    }

    public void createUI() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(new Runnable() {
            public void run() {
                playerUI = new PlayerUI(Player.this);
            }
        });
    }

    public void setPlayerName(String playerName){
        this.playerName = playerName;
    }

    public void setPlayerTeam(Team team){
        this.team = team;
        playerStatePacket = new PlayerStatePacket(linePullForce, team);
    }

    public void updateUI(){
        playerUI.updateUI(line);
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
        playerStreamOutput = new PlayerStreamOutput(out, this, msDelay + msDelayOffset);
        playerStreamOutput.start();
    }

    public PlayerStatePacket getPlayerStatePacket(){
        return playerStatePacket;
    }

    void updatePlayerData(GameStatePacket ReceivedPacket){
        line = ReceivedPacket.line;
        gameState = ReceivedPacket.currentGameState;
        updateUI();
        if(gameState == Game.GameState.RightWon || gameState == Game.GameState.LeftWon){
            gameOver();
        }
    }

    void gameOver(){
        disconnectFromServer();
    }

    void disconnectFromServer(){
        System.out.println("Disconnected from server");
        try {
            out.close();
            in.close();
        } catch (IOException e) {

        }
    }
}
