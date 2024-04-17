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
    Team team;
    Game game;

    Player(Game game, Team team, Socket clientSocket, int port) {
        this.game = game;
        this.team = team;
        this.clientSocket = clientSocket;
        this.port = port;
    }

    public void connectToServer() {
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
        while (true) {
            if (game.currentGameState == Game.GameState.GameIsOn) game.pullLine(0.01f, team);
            else break;
        }
    }
}
