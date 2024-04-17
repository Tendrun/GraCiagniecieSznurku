public class PlayerThread extends Thread {

    Player player;

    PlayerThread(Player player){
        this.player = player;
    }

    @Override
    public void run() {
        player.ConnectToServer();
    }
}
