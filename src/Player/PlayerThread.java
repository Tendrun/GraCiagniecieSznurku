package Player;

import Player.Player;

public class PlayerThread extends Thread {
    Player player;

    public PlayerThread(Player player){
        this.player = player;
    }

    public void connectToServer(){
        player.connectToServer();
    }

    @Override
    public void run() {
        player.startPlaying();
    }
}
