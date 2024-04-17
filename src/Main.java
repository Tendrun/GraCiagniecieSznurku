public class Main {

    static GameManager gameManager;

    public static void main(String[] args) {

        CreateComponents();
        StartGame();
    }

    static void CreateComponents(){
        gameManager = new GameManager(4444);
        gameManager.createServer();
        gameManager.createPlayers(4);
    }

    static void StartGame(){
        gameManager.startServer();
        gameManager.connectClientsToServer();
    }

}
