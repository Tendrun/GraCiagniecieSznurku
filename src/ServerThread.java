public class ServerThread extends Thread {

    Server server;

    ServerThread(Server server){
        this.server = server;
    }

    @Override
    public void run() {
        server.runServer();
    }
}
