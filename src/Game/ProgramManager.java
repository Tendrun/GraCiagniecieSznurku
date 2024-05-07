package Game;

import Player.PlayerThread;
import Player.Player;
import Server.Server;
import Server.ServerThread;
import java.io.IOException;
import java.net.*;

public class ProgramManager {

    ServerThread serverThread;
    PlayerThread[] playerThreads;
    int port;
    Game game;
    int msDelayPullingLine;

    public ProgramManager(int port, int msDelayPullingLine){
        this.port = port;
        this.msDelayPullingLine = msDelayPullingLine;
    }

    public void createServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Server server = new Server(serverSocket, game);
            serverThread = new ServerThread(server);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void createGame(int winThreshold) {
        game = new Game(winThreshold);
    }

    public void createPlayers(int amountOfPlayers) {
        playerThreads = new PlayerThread[amountOfPlayers];
        for (int i = 0; i < amountOfPlayers; i++) {
            Player.Team team = i < (float)amountOfPlayers/2 ? Player.Team.left : Player.Team.right;
            Socket clientSocket = new Socket();
            Player player = new Player(team, clientSocket, port, msDelayPullingLine);
            System.out.println("Stworzono gracza " + i + " gra on w druzynie " + team);
            playerThreads[i] = new PlayerThread(player);
        }
    }

    public void startServer(){
        serverThread.start();
    }

    public void connectClientsToServer(){
        for (PlayerThread playerThread : playerThreads) {
            playerThread.connectToServer();
        }
    }

    public void startGame(){
        for(PlayerThread playerThread : playerThreads){
            playerThread.start();
        }
    }

}
