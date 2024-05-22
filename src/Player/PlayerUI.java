package Player;

import Graphic.RightToLeftProgressBar;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PlayerUI {

    JFrame menuFrame;
    RightToLeftProgressBar barLeftTeam;
    JProgressBar barRightTeam;
    JLabel lineField;
    JPanel panel;
    String playerName;
    Player player;
    public PlayerUI(Player player) {
        this.player = player;

        panel = new JPanel();
        panel.setBackground(Color.GRAY);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setSize(800, 400);
        //this.add(panel, BorderLayout.CENTER);

        JButton button = new JButton("");
        panel.add(button);

        createMenuComponents();
    }

    void createMenuComponents() {
        menuFrame = new JFrame("Main Menu");
        menuFrame.setVisible(true);
        JButton buttonConnectToServer = new JButton("Połącz się z serwerem");
        buttonConnectToServer.setBounds(0,0,95,300);
        buttonConnectToServer.setVisible(true);
        buttonConnectToServer.addActionListener(deleteMenuComponents());
        panel.add(buttonConnectToServer, BorderLayout.EAST);
    }

    ActionListener deleteMenuComponents(){
        System.out.println("PRZYCISK");
        return null;
    }

    void createGameComponents(){

        JLabel leftText = new JLabel("Left Team");
        barLeftTeam = new RightToLeftProgressBar(0,100);
        barLeftTeam.setBounds(50,50,300,50);
        barLeftTeam.setValue(0);
        barLeftTeam.setStringPainted(false);
        barLeftTeam.setForeground(Color.RED);

        JLabel rightText = new JLabel("Right Team");
        barRightTeam = new JProgressBar(0,100);
        barRightTeam.setBounds(50,50,300,50);
        barRightTeam.setValue(0);
        barRightTeam.setStringPainted(false);
        barRightTeam.setForeground(Color.BLUE);


        lineField = new JLabel("Line = " + 0);

        panel.add(leftText, BorderLayout.WEST);
        panel.add(barLeftTeam, BorderLayout.WEST);
        panel.add(barRightTeam, BorderLayout.EAST);
        panel.add(rightText, BorderLayout.EAST);
        panel.add(lineField);
    }

    public void updateUI(int line){
        if (line < 0){
            barLeftTeam.setValue(line * -1);
            barRightTeam.setValue(0);
        } else if (line > 0){
            barRightTeam.setValue(0);
            barRightTeam.setValue(line);
        }
        else if (line == 0){
            barLeftTeam.setValue(0);
            barRightTeam.setValue(0);
        }

        lineField.setText("Line = " + line);
    }
}
