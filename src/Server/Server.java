package Server;

import DataPattern.GameStatePacket;
import Game.Game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {
    ServerSocket serverSocket;
    Game game;
    Map<Integer, ServerPlayerHandler> serverPlayerHandlerMap;

    public Server(int winThreshold) {
        try {
            serverSocket = new ServerSocket(4444);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        serverPlayerHandlerMap = new HashMap<Integer, ServerPlayerHandler>();
        game = new Game(winThreshold, this);
    }

    public void runServer() {
        while (true){
            try {
                Socket s = serverSocket.accept();
                int key = findFreeKey();
                ServerPlayerHandler serverPlayerHandler = new ServerPlayerHandler(s, game, key, this);
                serverPlayerHandler.start();
                serverPlayerHandlerMap.put(key, serverPlayerHandler);
                sendUpdateToPlayers();
                System.out.println("Serwer przyjal gracza");
            }
            catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public int findFreeKey() {
        int key = 0;
        while (serverPlayerHandlerMap.containsKey(key)) {
            key++;
        }
        return key;
    }

    public void closePlayerHandler(int index) {
        serverPlayerHandlerMap.remove(index);
    }

    public void sendUpdateToPlayers(){
        for (int i = 0; i < serverPlayerHandlerMap.size(); i++) {
            try {
                serverPlayerHandlerMap.get(i).playerStreamOutput.sendToPlayerChanges();
            }
            catch (NullPointerException e) {
                closePlayerHandler(i);
            }
        }
    }
}
