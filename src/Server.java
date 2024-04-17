import java.io.IOException;
import java.net.ServerSocket;

public class Server {

    ServerSocket serverSocket;

    public Server(ServerSocket server) {
        this.serverSocket = server;
    }

    public void runServer() {
        while (true){

            try {
                System.out.println("Serwer czeka");
                serverSocket.accept();
                System.out.println("Serwer przyjal gracza");
            }
            catch (IOException e) {
                System.out.println(e);
            }

        }
    }

}
