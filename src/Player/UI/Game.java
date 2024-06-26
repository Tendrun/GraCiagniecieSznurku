package Player.UI;

import Graphic.RightToLeftProgressBar;
import Player.Player;

import Game.Game.GameState;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Game extends JFrame {
    JLabel lineField;
    RightToLeftProgressBar barLeftTeam;
    JProgressBar barRightTeam;
    Player player;
    JPanel panel;
    ActionListener pullLine;
    ActionListener disconnectFromServer;
    JLabel gameStateField;

    public Game(Player player, String title, ActionListener disconnectFromServer, ActionListener pullLine) {
        super(title);

        this.player = player;
        this.disconnectFromServer = disconnectFromServer;
        this.pullLine = pullLine;

        //JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);

        //Panel
        panel = new JPanel();
        panel.setBackground(Color.GRAY);
        add(panel);

        //Left Progress Bar
        JLabel leftText = new JLabel("Left Team");
        barLeftTeam = new RightToLeftProgressBar(0,100);
        barLeftTeam.setBounds(50,50,300,50);
        barLeftTeam.setValue(0);
        barLeftTeam.setStringPainted(false);
        barLeftTeam.setForeground(Color.RED);

        //Right Progress Bar
        JLabel rightText = new JLabel("Right Team");
        barRightTeam = new JProgressBar(0,100);
        barRightTeam.setBounds(50,50,300,50);
        barRightTeam.setValue(0);
        barRightTeam.setStringPainted(false);
        barRightTeam.setForeground(Color.BLUE);

        //disconnect button
        JButton buttonConnectToServer = new JButton("Rozłącz się");
        buttonConnectToServer.setBounds(0,0,95,300);
        buttonConnectToServer.setVisible(true);
        buttonConnectToServer.addActionListener(disconnectFromServer);
        panel.add(buttonConnectToServer, BorderLayout.EAST);


        //pull line
        JButton buttonPullLine = new JButton("Ciagnij line");
        buttonPullLine.setBounds(0,0,95,300);
        buttonPullLine.setVisible(true);
        buttonPullLine.addActionListener(pullLine);
        panel.add(buttonPullLine, BorderLayout.EAST);

        lineField = new JLabel("Line = " + 0);

        //Game state
        gameStateField = new JLabel("");
        panel.add(gameStateField, BorderLayout.CENTER);
        gameStateField.setVisible(true);


        panel.add(leftText, BorderLayout.WEST);
        panel.add(barLeftTeam, BorderLayout.WEST);
        panel.add(barRightTeam, BorderLayout.EAST);
        panel.add(rightText, BorderLayout.EAST);
        panel.add(lineField);
    }

    public void updateUI(int line, GameState gameState){
        if (line < 0){
            barLeftTeam.setValue(line * -1);
            barRightTeam.setValue(0);
        } else if (line > 0){
            barLeftTeam.setValue(0);
            barRightTeam.setValue(line);
        }
        else if (line == 0){
            barLeftTeam.setValue(0);
            barRightTeam.setValue(0);
        }
        if(gameState == GameState.LeftWon){
            gameStateField.setText("LEFT SIDE HAS WON");
        }

        else if(gameState == GameState.RightWon){
            gameStateField.setText("Right SIDE HAS WON");
        }

        System.out.println("Game " + gameState);
        lineField.setText("Line = " + line);
    }
}
