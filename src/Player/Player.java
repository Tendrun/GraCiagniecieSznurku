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
import Player.UI.PlayerUIMediator;

import javax.swing.*;

public class Player {
    Socket clientSocket;
    ObjectOutputStream out;
    ObjectInputStream in;
    int port;
    Game.GameState gameState = null;
    static PlayerUIMediator playerUI;
    public enum Team {
        left("left"),
        right("right");

        private final String teamName;

        Team(String teamName) {
            this.teamName = teamName;
        }

        @Override
        public String toString() {
            return teamName;
        }
    }
    Team team;
    int linePullForce = 1;
    int line = 0;
    PlayerStatePacket playerStatePacket;
    PlayerStreamInput playerStreamInput;


    public Player() {
        port = 4444;
        clientSocket = new Socket();
        try {
            createUI();
        } catch (InterruptedException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public void createUI() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(new Runnable() {
            public void run() {
                playerUI = new PlayerUIMediator(Player.this);
            }
        });
        playerUI.goToMenu();
    }

    public void setPlayerTeam(Team team){
        this.team = team;
        playerStatePacket = new PlayerStatePacket(linePullForce, team);
    }

    public void updateUI(){
        playerUI.updateUI(line, gameState);
    }
    public void connectToServer() {
        try {
            clientSocket = new Socket();
            InetAddress inetAddress = InetAddress.getByName("localhost");
            SocketAddress socketAddress = new InetSocketAddress(inetAddress, port);
            clientSocket.connect(socketAddress);
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            out.flush();
            in = new ObjectInputStream (clientSocket.getInputStream());
            playerUI.goToGame();
            readDataFromServer();
        }
        catch (IOException e){
            System.out.println(e);
        }
    }

    public void sendToServerChanges() {
        try {
            out.writeObject(getPlayerStatePacket());
            out.flush();
        } catch (InvalidClassException e){
            System.err.println("InvalidClassException :(((");
        } catch (IOException e) {
            System.err.println("IOException");
            disconnectFromServer();
        }
    }


    void readDataFromServer(){
        playerStreamInput = new PlayerStreamInput(in, this);
        playerStreamInput.start();
    }

    public PlayerStatePacket getPlayerStatePacket(){
        return playerStatePacket;
    }

    void updatePlayerData(GameStatePacket ReceivedPacket){
        line = ReceivedPacket.line;
        gameState = ReceivedPacket.currentGameState;
        updateUI();
        if(gameState == Game.GameState.RightWon || gameState == Game.GameState.LeftWon){
            //gameOver();
        }
    }

    void gameOver(){
        disconnectFromServer();
    }

    public void disconnectFromServer(){
        System.out.println("Disconnected from server");
        try {
            out.close();
            in.close();
            playerUI.goToMenu();
        } catch (IOException e) {

        }
    }
}
