package Player.UI;

import Player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    Player player;
    ActionListener connectToServer;
    String playerName;

    JPanel panel;
    JTextField textField;

    public Menu(String title, ActionListener connectToServer) {
        super(title);
        this.connectToServer = connectToServer;

        //JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);

        //Panel
        panel = new JPanel();
        panel.setBackground(Color.GRAY);
        add(panel);

        //Button Rozlacz sie
        JButton buttonConnectToServer = new JButton("Połącz się z serwerem");
        buttonConnectToServer.setBounds(0,0,95,300);
        buttonConnectToServer.setVisible(true);
        buttonConnectToServer.addActionListener(connectToServer);
        panel.add(buttonConnectToServer, BorderLayout.EAST);
    }
}
