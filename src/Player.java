import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class Player {

    Socket clientSocket;
    PrintWriter out;
    BufferedReader in;
    int port;
    public enum Team {
        left, right;
    }
    Team team;

    Player(Team team, Socket clientSocket, int port) {
        this.team = team;
        this.clientSocket = clientSocket;
        this.port = port;
    }

    public void connectToServer() {
        try {
            InetAddress inetAddress = InetAddress.getByName("localhost");
            SocketAddress socketAddress = new InetSocketAddress(inetAddress, port);
            clientSocket.connect(socketAddress);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        }
        catch (IOException e){
            System.out.println(e);
        }
    }

    public void startPlaying() {
        out.println("Pull: 1");
    }
}
