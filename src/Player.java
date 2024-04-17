import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class Player {

    Socket clientSocket;
    int port;
    public enum Team {
        left, right;
    }
    public Team team;

    Player(Team team, Socket clientSocket, int port) {
        this.team = team;
        this.clientSocket = clientSocket;
        this.port = port;
    }

    public void ConnectToServer() {
        try {
            InetAddress inetAddress = InetAddress.getByName("localhost");
            SocketAddress socketAddress = new InetSocketAddress(inetAddress, port);
            clientSocket.connect(socketAddress);
        }
        catch (IOException e){
            System.out.println(e);
        }
    }

    public void startPlaying() {
        System.out.println("I play");
    }
}
