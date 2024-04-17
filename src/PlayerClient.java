import java.net.Socket;

public class PlayerClient {

    Socket clientSocket;

    public enum Team {
        left, right;
    }

    Team team;

    PlayerClient(Team team, Socket clientSocket) {
        this.team = team;
        this.clientSocket = clientSocket;
    }

    public void startPlaying(){
        System.out.println("I play");
    }
}
