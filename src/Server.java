import java.net.ServerSocket;

public class Server {

    ServerSocket server;

    public Server(ServerSocket server) {
        this.server = server;
    }


    public ServerSocket getServer(){
        return server;
    }
}
