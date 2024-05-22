package Player.UI;

import Graphic.RightToLeftProgressBar;
import Player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerUIMediator {

    JLabel lineField;
    Menu menuFrame;
    Game gameFrame;
    RightToLeftProgressBar barLeftTeam;
    JProgressBar barRightTeam;
    Player player;
    public PlayerUIMediator(Player player) {
        this.player = player;
        menuFrame = new Menu("Main Menu", connectToServer());
        gameFrame = new Game("Game", disconnectFromServer(), pullLine());
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
                System.out.println("PRZYCISK");
                player.connectToServer();
            }
        };
    }

    public ActionListener disconnectFromServer() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("PRZYCISK");
                player.disconnectFromServer();
            }
        };
    }

    public ActionListener pullLine() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("PRZYCISK");
                player.sendToServerChanges();
            }
        };
    }

    public void updateUI(int line){
        gameFrame.updateUI(line);
    }
}
