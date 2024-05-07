package Server;

public class ServerThread extends Thread {

    Server server;

    public ServerThread(Server server){
        this.server = server;
    }

    @Override
    public void run() {
        server.runServer();
    }
}
