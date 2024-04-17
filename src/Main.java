public class Main {

    static GameManager gameManager;

    public static void main(String[] args) {

        CreateComponents();
        StartGame();
    }

    static void CreateComponents(){
        gameManager = new GameManager(4444);
        gameManager.createGame();
        gameManager.createServer();
        gameManager.createPlayers(12);
    }

    static void StartGame(){
        gameManager.startServer();
        gameManager.connectClientsToServer();
        gameManager.startGame();
    }

}
