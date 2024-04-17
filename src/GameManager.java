import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class GameManager {

    Server server;
    PlayerClient[] playerClients;

    public void createServer(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            server = new Server(serverSocket);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void createClients(int amountOfClients) {

        PlayerClient[] newPlayerClients = new PlayerClient[amountOfClients];

        for (int i = 0; i < amountOfClients; i++) {

            PlayerClient.Team team = i < (float)amountOfClients/2 ? PlayerClient.Team.left : PlayerClient.Team.right;
            Socket clientSocket = new Socket();
            PlayerClient playerClient = new PlayerClient(PlayerClient.Team.left, clientSocket);
            newPlayerClients[i] = playerClient;
        }

        playerClients = Arrays.copyOf(newPlayerClients, amountOfClients);
    }

}
