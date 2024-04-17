import java.io.IOException;
import java.net.*;

public class GameManager {

    ServerThread serverThread;
    PlayerThread[] playerThreads;
    int port;
    GameManager(int port){
        this.port = port;
    }

    public void createServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Server server = new Server(serverSocket);
            serverThread = new ServerThread(server);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void createPlayers(int amountOfClients) {
        playerThreads = new PlayerThread[amountOfClients];
        for (int i = 0; i < amountOfClients; i++) {
            Player.Team team = i < (float)amountOfClients/2 ? Player.Team.left : Player.Team.right;
            Socket clientSocket = new Socket();
            Player player = new Player(Player.Team.left, clientSocket, port);
            System.out.println("Stworzono gracza " + i + " gra on w druzynie " + team);
            playerThreads[i] = new PlayerThread(player);
        }
    }

    public void startServer(){
        serverThread.start();
    }

    public void connectClientsToServer(){
        for (PlayerThread playerThread : playerThreads) {
            playerThread.start();
        }
    }

}
