import java.io.BufferedReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {

    ServerSocket serverSocket;
    Game game;

    public Server(ServerSocket server, Game game) {
        this.serverSocket = server;
        this.game = game;
    }

    public void runServer() {
        while (true){

            try {
                System.out.println("Serwer czeka");
                Socket s = serverSocket.accept();
                ServerPlayerHandler t = new ServerPlayerHandler(s);
                t.start();

                System.out.println("Serwer przyjal gracza");
            }
            catch (IOException e) {
                System.out.println(e);
            }

        }
    }


}
