package Main.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

/**
 * Displays the game in a panel.
 */
public class GamePanel extends JPanel {

    /**
     * Default ctor.
     */
    public GamePanel(){
        super();
    }

    /**
     * Paints the components.
     * @param g Erre lehet majd rajzolgatni. Ezt kene a Draw() metodusokban atadni( Draw(Graphics g) )
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillOval(20, 40, 100, 100);
    }
}

