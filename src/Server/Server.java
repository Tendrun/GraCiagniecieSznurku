package Server;

import DataPattern.GameStatePacket;
import Game.Game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    ServerSocket serverSocket;
    Game game;
    List<ServerPlayerHandler> serverPlayerHandlerList;

    public Server(ServerSocket server, int winThreshold) {
        this.serverSocket = server;
        serverPlayerHandlerList = new ArrayList<ServerPlayerHandler>();
        game = new Game(winThreshold, this);
    }

    public void createGame(){

    }

    public void runServer() {
        while (true){
            try {
                Socket s = serverSocket.accept();
                ServerPlayerHandler serverPlayerHandler = new ServerPlayerHandler(s, game);
                serverPlayerHandler.start();
                serverPlayerHandlerList.add(serverPlayerHandler);
                System.out.println("Serwer przyjal gracza");
            }
            catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public void sendUpdateToPlayers(){
        for (int i = 0; i < serverPlayerHandlerList.size(); i++) {
            serverPlayerHandlerList.get(i).playerStreamOutput.sendToPlayerChanges();
        }
    }

    public void endGame(){

    }
}
