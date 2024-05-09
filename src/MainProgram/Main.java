package MainProgram;

import Game.ProgramManager;

public class Main {

    static ProgramManager gameManager;

    public static void main(String[] args) {
        CreateComponents();
        StartGame();
    }

    static void CreateComponents(){
        gameManager = new ProgramManager(4444, 1000);
        gameManager.createServer();
        gameManager.createPlayers(222);
    }

    static void StartGame(){
        gameManager.startServer();
        gameManager.connectClientsToServer();
        gameManager.startGame();
    }

}
