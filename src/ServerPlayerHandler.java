import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerPlayerHandler extends Thread {

    Socket playerSocket;

    public ServerPlayerHandler(Socket playerSocket){
        this.playerSocket = playerSocket;
    }

    @Override
    public void run() {
        waitForPlayerInput();
        waitForPlayerOutput();
    }

    void waitForPlayerInput(){
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(playerSocket.getInputStream()));
            System.out.println(in.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void waitForPlayerOutput(){
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(playerSocket.getInputStream()));
            System.out.println("x" + in.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
