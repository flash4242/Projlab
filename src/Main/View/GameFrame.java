package Main.View;

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

    /**
     * Example of how one can create a JTextField
     */
    private JTextField hova;
    private JTextField ki;
    private JTextField be;
    private JTextField hanyadik;
    private CardLayout card = new CardLayout();
    private JTextField szereloszam;
    private JTextField szabotorszam;
    private JPanel menu;

    /**
     * Default Ctor
     */
    @SuppressWarnings("unchecked")
    public GameFrame() {
        super("CsipCsap Jateka woooohoo");

        // Induláskor valami init kell ide initGame?
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLayout(card);
        InitComponents();
        InitMenu();
        showMenu();
    }

    private void InitMenu(){
        menu = new JPanel();
        JButton newGame = new JButton("Jatek inditasa");
        newGame.addActionListener((ActionEvent e) -> {
            showGame();
        });
        newGame.setPreferredSize(new Dimension(150, 60));
        szereloszam = new JTextField(10);
        szabotorszam = new JTextField(10);

        //pfff ez valami
        GridBagConstraints gbc = new GridBagConstraints();
        GridBagLayout layout = new GridBagLayout();
        menu.setLayout(layout);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        menu.add(newGame, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        menu.add(new JLabel("Szerelok"), gbc);
        gbc.gridx = 1;
        menu.add(szereloszam, gbc);
        gbc.gridy = 8;
        gbc.gridx = 0;
        menu.add(new JLabel("Szabotorok"), gbc);
        gbc.gridx = 1;
        menu.add(szabotorszam, gbc);
        menu.setBackground(Color.GRAY);
        this.add(menu, "menu");
    }

    /**
     *
     */
    private void InitComponents(){

        //kozos
        JLabel kozos = new JLabel("Kozos akciok");
        JButton Mozog = new JButton("Mozog");
        JLabel hova_ = new JLabel("Hova:");
        hova = new JTextField("", 2);
        JButton Atallit = new JButton("Pumpat atallit");
        JLabel ki_ = new JLabel("Ki:");
        ki = new JTextField("", 2);
        JLabel be_ = new JLabel("Be:");
        be = new JTextField("", 2);
        JButton Lyukaszt = new JButton("Csovet lyukaszt");
        JButton Ragaszt = new JButton("Csovet ragasztoz");

        //szerelo
        JLabel szerelo = new JLabel("Szerelo akcioi");
        szerelo.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        JButton Javit = new JButton("Mezot javit");
        Javit.setAlignmentX(JButton.CENTER_ALIGNMENT);
        JButton CsFelvesz = new JButton("Csovet felvesz");
        CsFelvesz.setAlignmentX(JButton.CENTER_ALIGNMENT);
        JLabel hanyadik_ = new JLabel("Hanyadik:");
        hanyadik_.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        hanyadik = new JTextField("", 2);
        hanyadik.setMaximumSize(hanyadik.getPreferredSize());
        hanyadik_.setAlignmentX(JTextField.CENTER_ALIGNMENT);
        JButton CsLerak = new JButton("Csovet lerak");
        CsLerak.setAlignmentX(JButton.CENTER_ALIGNMENT);
        JButton PFelvesz = new JButton("Pumpat felvesz");
        PFelvesz.setAlignmentX(JButton.CENTER_ALIGNMENT);
        JButton PLerak = new JButton("Pumpat lerak");
        PLerak.setAlignmentX(JButton.CENTER_ALIGNMENT);

        //szabotor
        JLabel szabotor = new JLabel("Szabotor akcioi");
        szabotor.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        JButton Csuszosit = new JButton("Csovet csuszosit");
        Csuszosit.setAlignmentX(JButton.CENTER_ALIGNMENT);

        //Mozog.addActionListener((ActionEvent e) -> {
        //});
        //Atallit.addActionListener((ActionEvent e) -> {
        //});
        //Lyukaszt.addActionListener((ActionEvent e) -> {
        //});
        //buttino.addActionListener((ActionEvent e) -> {
        //});
        //buttinos.addActionListener((ActionEvent e) -> {
        //});
        //Ragaszt.addActionListener((ActionEvent e) ->{
        //});
        //buttinosej.addActionListener((ActionEvent e) -> {
        //});

        //egy fő panel ami tartalmazza a játékhoz szükséges paneleket, így lehet váltani közte es a menu között
        JPanel mainPanel = new JPanel(new BorderLayout());

        //Felso panel (kozos gombok)
        JPanel pan = new JPanel();
        pan.add(kozos);
        pan.add(Mozog);
        pan.add(hova_);
        pan.add(hova);
        pan.add(Atallit);
        pan.add(ki_);
        pan.add(ki);
        pan.add(be_);
        pan.add(be);
        pan.add(Lyukaszt);
        pan.add(Ragaszt);

        //left panel (szerelo akciok)
        JPanel left_panel = new JPanel();
        left_panel.setLayout(new BoxLayout(left_panel, BoxLayout.Y_AXIS));
        left_panel.add(szerelo);
        left_panel.add(Javit);
        left_panel.add(Box.createVerticalStrut(15));
        left_panel.add(CsFelvesz);
        left_panel.add(hanyadik_);
        left_panel.add(hanyadik);
        left_panel.add(Box.createVerticalStrut(15));
        left_panel.add(CsLerak);
        left_panel.add(Box.createVerticalStrut(15));
        left_panel.add(PFelvesz);
        left_panel.add(Box.createVerticalStrut(15));
        left_panel.add(PLerak);
        mainPanel.add(left_panel, BorderLayout.WEST);

        //right panel (szabotor akciok)
        JPanel right_panel = new JPanel();
        right_panel.setLayout(new BoxLayout(right_panel, BoxLayout.Y_AXIS));
        right_panel.add(szabotor);
        right_panel.add(Csuszosit);
        mainPanel.add(right_panel, BorderLayout.EAST);

        //North are buttons
        mainPanel.add(pan, BorderLayout.NORTH);
        //Center is the Main.View.GamePanel
        mainPanel.add(GamePanel.getInstance(), BorderLayout.CENTER);

        this.add(mainPanel, "game");
    }

    public void showMenu() {
        this.setSize(500, 500);
        card.show(getContentPane(), "menu");
    }

    public void showGame() {
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height);
        card.show(getContentPane(), "game");
    }
}
