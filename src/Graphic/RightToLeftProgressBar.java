package Graphic;

import javax.swing.*;
import java.awt.*;

public class RightToLeftProgressBar extends JProgressBar {

    public RightToLeftProgressBar(int min, int max) {
        super(min, max);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.scale(-1, 1); // Flips over y-axis
        g2d.translate(-getWidth(), 0); // Moves back to old position.
        super.paintComponent(g2d);
    }
}
