package Player;

import Graphic.RightToLeftProgressBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PlayerUI extends JFrame {
    RightToLeftProgressBar barLeftTeam;
    JProgressBar barRightTeam;
    JLabel lineField;

    String playerName;
    public PlayerUI(String playerName) {
        this.playerName = playerName;
        createComponents();
    }

    void createComponents(){
        setTitle(playerName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLayout(new BorderLayout());

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

        JPanel panel = new JPanel();

        panel.setBackground(Color.GRAY);
        panel.add(leftText, BorderLayout.WEST);
        panel.add(barLeftTeam, BorderLayout.WEST);
        panel.add(barRightTeam, BorderLayout.EAST);
        panel.add(rightText, BorderLayout.EAST);
        panel.add(lineField);

        this.add(panel);
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
