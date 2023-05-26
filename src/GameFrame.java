import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.List;

public class GameFrame extends JFrame {
    private GamePanel m;

    /**
     * Example of how one can create a JTextArea
     */
    private JTextArea input;

    /**
     * Default Ctor
     */
    @SuppressWarnings("unchecked")
    public GameFrame() {
        super("CsipCsap Jateka woooohoo");

        // Induláskor valami init kell ide initGame?
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height);
        InitComponents();
    }

    /**
     * Initialises the components of the main.graph.
     * Creates a border layout north are the menu options and center is the gamepanel.
     * Sets up the action listeners of the menu buttons.
     */
    private void InitComponents(){
        m = new GamePanel();

        this.setLayout(new BorderLayout());
        //example buttons and labels
        JButton butt = new JButton("Step");
        JButton butti = new JButton("Init");
        JButton buttin = new JButton("Execute");
        JButton buttinose = new JButton("Walk");
        JLabel labil = new JLabel("Delete:");
        JButton buttino = new JButton("Vertex");
        JButton buttinos = new JButton("Graph");
        JButton buttinosej = new JButton("Process");
        JLabel labi = new JLabel("BFS:");
        JLabel labilo = new JLabel("Enter n*n Matrix of 0 and 1s:");

        input = new JTextArea(5,10);

        //example action listeners, they use lambdas
        butt.addActionListener((ActionEvent e) -> {
            m.repaint();
        });
        butti.addActionListener((ActionEvent e) -> {
            m.repaint();
        });
        buttin.addActionListener((ActionEvent e) -> {
            m.repaint();
        });
        buttino.addActionListener((ActionEvent e) -> {
            m.repaint();
        });

        buttinos.addActionListener((ActionEvent e) -> {
        });

        buttinose.addActionListener((ActionEvent e) ->{
        });

        buttinosej.addActionListener((ActionEvent e) -> {
        });

        //Add the buttons to the panel on the top
        JPanel pan = new JPanel();
        pan.add(labi);
        pan.add(butti);
        pan.add(butt);
        pan.add(buttin);
        pan.add(buttinose);
        pan.add(labil);
        pan.add(buttino);
        pan.add(buttinos);
        pan.add(labilo);
        pan.add(input);
        pan.add(buttinosej);
        //North are buttons
        this.add(pan, BorderLayout.NORTH);
        //Center is the GamePanel
        this.add(m, BorderLayout.CENTER);
    }
}
