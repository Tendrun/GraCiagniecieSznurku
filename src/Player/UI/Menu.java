package Player.UI;

import Player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    Player player;
    ActionListener connectToServer;
    String playerName;
    JPanel panel;
    JTextField textField;

    public Menu(Player player, String title, ActionListener connectToServer) {
        super(title);
        this.player = player;
        this.connectToServer = connectToServer;

        //JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);

        //Panel
        panel = new JPanel();
        panel.setBackground(Color.GRAY);
        add(panel);

        //Team option
        String[] teamNames = new String[ Player.Team.values().length];
        for (int i = 0; i < Player.Team.values().length; i++) {
            teamNames[i] = Player.Team.values()[i].toString();
        }
        JComboBox optionPane = new JComboBox(teamNames);
        optionPane.setVisible(true);
        panel.add(optionPane, BorderLayout.EAST);
        player.setPlayerTeam(Player.Team.valueOf(optionPane.getSelectedItem().toString()));
        optionPane.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                player.setPlayerTeam(Player.Team.valueOf(optionPane.getSelectedItem().toString()));
            }
        });



        //Button Rozlacz sie
        JButton buttonConnectToServer = new JButton("Połącz się z serwerem");
        buttonConnectToServer.setBounds(0,0,95,300);
        buttonConnectToServer.setVisible(true);
        buttonConnectToServer.addActionListener(connectToServer);
        panel.add(buttonConnectToServer, BorderLayout.EAST);
    }
}
