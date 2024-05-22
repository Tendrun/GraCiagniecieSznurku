package Player.UI;

import Game.Game.GameState;
import Player.Player;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerUIMediator {
    Menu menuFrame;
    Game gameFrame;
    Player player;
    public PlayerUIMediator(Player player) {
        this.player = player;
        menuFrame = new Menu(player, "Main Menu", connectToServer());
        gameFrame = new Game(player, "Game", disconnectFromServer(), pullLine());
    }

    public void goToMenu() {
        menuFrame.setVisible(true);
        gameFrame.setVisible(false);
    }

    public void goToGame() {
        menuFrame.setVisible(false);
        gameFrame.setVisible(true);
    }

    public ActionListener connectToServer() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.connectToServer();
            }
        };
    }

    public ActionListener disconnectFromServer() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.disconnectFromServer();
            }
        };
    }

    public ActionListener pullLine() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.sendToServerChanges();
            }
        };
    }

    public void updateUI(int line, GameState gameState){
        gameFrame.updateUI(line, gameState);
    }
}
