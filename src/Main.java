public class Main {

    public static void main(String[] args) {

        GameManager gameManager = new GameManager();
        gameManager.createServer(4444);
        gameManager.createClients(4);
    }

}
