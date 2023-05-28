package Main.View;

import Main.Kontroller;
import Main.Model.Jatekos;

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
import java.util.function.BiConsumer;

public class GameFrame extends JFrame {

    /**
     * Example of how one can create a JTextField
     */
    private JTextField hova, ki, be, hanyadik;

    private JButton Mozog, Atallit, Lyukaszt, Ragaszt, Javit, CsFelvesz, CsLerak, PFelvesz, PLerak, Csuszosit;
    private CardLayout card = new CardLayout();
    private JTextField szereloszam, szabotorszam;
    private JPanel menu;

    /**
     * Default Ctor
     */
    @SuppressWarnings("unchecked")
    public GameFrame() {
        super("CsipCsap Jateka woooohoo");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLayout(card);
        InitComponents();
        InitMenu();
        showMenu();
    }

    private void InitMenu(){
        menu = new JPanel();
        JButton newGame = new JButton("Játék indítása");
        newGame.addActionListener((ActionEvent e) -> {
            //TODO Kontrollert setupolni kell és alap pálya inicializálás
            Kontroller.getInstance().setSzerelokSzama(Integer.parseInt(szereloszam.getText()));
            Kontroller.getInstance().setSzabotorokSzama(Integer.parseInt(szabotorszam.getText()));
            Kontroller.getInstance().initJatek();
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
        JLabel kozos = new JLabel("Közös akciók");
        Mozog = new JButton("Mozog");
        JLabel hova_ = new JLabel("Hova:");
        hova = new JTextField("", 2);
        Atallit = new JButton("Pumpát átállít");
        JLabel ki_ = new JLabel("Ki:");
        ki = new JTextField("", 2);
        JLabel be_ = new JLabel("Be:");
        be = new JTextField("", 2);
        Lyukaszt = new JButton("Csövet lyukaszt");
        Ragaszt = new JButton("Csövet ragasztóz");

        //szerelo
        JLabel szerelo = new JLabel("Szerelő akciói");
        szerelo.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        Javit = new JButton("Mezőt javít");
        Javit.setAlignmentX(JButton.CENTER_ALIGNMENT);
        CsFelvesz = new JButton("Csövet felvesz");
        CsFelvesz.setAlignmentX(JButton.CENTER_ALIGNMENT);
        JLabel hanyadik_ = new JLabel("Hanyadik:");
        hanyadik_.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        hanyadik = new JTextField("", 2);
        hanyadik.setMaximumSize(hanyadik.getPreferredSize());
        hanyadik_.setAlignmentX(JTextField.CENTER_ALIGNMENT);
        CsLerak = new JButton("Csövet lerak");
        CsLerak.setAlignmentX(JButton.CENTER_ALIGNMENT);
        PFelvesz = new JButton("Pumpát felvesz");
        PFelvesz.setAlignmentX(JButton.CENTER_ALIGNMENT);
        PLerak = new JButton("Pumpát lerak");
        PLerak.setAlignmentX(JButton.CENTER_ALIGNMENT);

        //szabotor
        JLabel szabotor = new JLabel("Szabotőr akciói");
        szabotor.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        Csuszosit = new JButton("Csövet csuszósít");
        Csuszosit.setAlignmentX(JButton.CENTER_ALIGNMENT);

        //gombok lenyomásának lekezelése
        AddLambdas();

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

    /**
     * Ne töröld!!
     */
    //public void AddLambdas(){
    //    Mozog.addActionListener((ActionEvent e) -> {
    //        int akt = Kontroller.getInstance().getAktualisJatekos();
    //        List<Jatekos> jatekosok = Kontroller.getInstance().getJatekosok();
    //        jatekosok.get(akt).mozgas(Integer.parseInt(hova.getText()));
    //        Kontroller.getInstance().stepKor();
    //        if(akt == jatekosok.size()-1){
    //            Kontroller.getInstance().stepTime();
    //        }
    //        Kontroller.getInstance().setAktJatekos((akt+1)% jatekosok.size());
    //    });
    //    Atallit.addActionListener((ActionEvent e) -> {
    //        int akt = Kontroller.getInstance().getAktualisJatekos();
    //        List<Jatekos> jatekosok = Kontroller.getInstance().getJatekosok();
    //        jatekosok.get(akt).pumpaAtallitasa(Integer.parseInt(be.getText()), Integer.parseInt(ki.getText()));
    //        Kontroller.getInstance().stepKor();
    //        if(akt == jatekosok.size()-1){
    //            Kontroller.getInstance().stepTime();
    //        }
    //        Kontroller.getInstance().setAktJatekos((akt+1)% jatekosok.size());
    //    });
    //    Lyukaszt.addActionListener((ActionEvent e) -> {
    //        int akt = Kontroller.getInstance().getAktualisJatekos();
    //        List<Jatekos> jatekosok = Kontroller.getInstance().getJatekosok();
    //        jatekosok.get(akt).csoKilyukasztasa();
    //        Kontroller.getInstance().stepKor();
    //        if(akt == jatekosok.size()-1){
    //            Kontroller.getInstance().stepTime();
    //        }
    //        Kontroller.getInstance().setAktJatekos((akt+1)% jatekosok.size());
    //    });
    //    Ragaszt.addActionListener((ActionEvent e) -> {
    //        int akt = Kontroller.getInstance().getAktualisJatekos();
    //        List<Jatekos> jatekosok = Kontroller.getInstance().getJatekosok();
    //        jatekosok.get(akt).beragasztoz();
    //        Kontroller.getInstance().stepKor();
    //        if(akt == jatekosok.size()-1){
    //            Kontroller.getInstance().stepTime();
    //        }
    //        Kontroller.getInstance().setAktJatekos((akt+1)% jatekosok.size());
    //    });
    //    Javit.addActionListener((ActionEvent e) -> {
    //        int akt = Kontroller.getInstance().getAktualisJatekos();
    //        List<Jatekos> jatekosok = Kontroller.getInstance().getJatekosok();
    //        jatekosok.get(akt).mezotJavit();
    //        Kontroller.getInstance().stepKor();
    //        if(akt == jatekosok.size()-1){
    //            Kontroller.getInstance().stepTime();
    //        }
    //        Kontroller.getInstance().setAktJatekos((akt+1)% jatekosok.size());
    //    });
    //    CsFelvesz.addActionListener((ActionEvent e) -> {
    //        int akt = Kontroller.getInstance().getAktualisJatekos();
    //        List<Jatekos> jatekosok = Kontroller.getInstance().getJatekosok();
    //        jatekosok.get(akt).csovegFelvetele(Integer.parseInt(hanyadik.getText()));
    //        Kontroller.getInstance().stepKor();
    //        if(akt == jatekosok.size()-1){
    //            Kontroller.getInstance().stepTime();
    //        }
    //        Kontroller.getInstance().setAktJatekos((akt+1)% jatekosok.size());
    //    });
    //    CsLerak.addActionListener((ActionEvent e) -> {
    //        int akt = Kontroller.getInstance().getAktualisJatekos();
    //        List<Jatekos> jatekosok = Kontroller.getInstance().getJatekosok();
    //        jatekosok.get(akt).csovegetLerak();
    //        Kontroller.getInstance().stepKor();
    //        if(akt == jatekosok.size()-1){
    //            Kontroller.getInstance().stepTime();
    //        }
    //        Kontroller.getInstance().setAktJatekos((akt+1)% jatekosok.size());
    //    });
    //    PFelvesz.addActionListener((ActionEvent e) -> {
    //        int akt = Kontroller.getInstance().getAktualisJatekos();
    //        List<Jatekos> jatekosok = Kontroller.getInstance().getJatekosok();
    //        jatekosok.get(akt).pumpaFelvetele();
    //        Kontroller.getInstance().stepKor();
    //        if(akt == jatekosok.size()-1){
    //            Kontroller.getInstance().stepTime();
    //        }
    //        Kontroller.getInstance().setAktJatekos((akt+1)% jatekosok.size());
    //    });
    //    PLerak.addActionListener((ActionEvent e) -> {
    //        int akt = Kontroller.getInstance().getAktualisJatekos();
    //        List<Jatekos> jatekosok = Kontroller.getInstance().getJatekosok();
    //        jatekosok.get(akt).pumpatLerak();
    //        Kontroller.getInstance().stepKor();
    //        if(akt == jatekosok.size()-1){
    //            Kontroller.getInstance().stepTime();
    //        }
    //        Kontroller.getInstance().setAktJatekos((akt+1)% jatekosok.size());
    //    });
    //    Csuszosit.addActionListener((ActionEvent e) -> {
    //        int akt = Kontroller.getInstance().getAktualisJatekos();
    //        List<Jatekos> jatekosok = Kontroller.getInstance().getJatekosok();
    //        jatekosok.get(akt).csuszosit();
    //        Kontroller.getInstance().stepKor();
    //        if(akt == jatekosok.size()-1){
    //            Kontroller.getInstance().stepTime();
    //        }
    //        Kontroller.getInstance().setAktJatekos((akt+1)% jatekosok.size());
    //    });
    //}

    /**
     * Na ez nem tudom, hogy jól működik-e úgyhogy fentebb benthagytam még a kódismétléses verziót is
     */
    public void AddLambdas() {
        addActionListenerToButton(Mozog, hova, (jatekos, value) -> {
            if(!value.isEmpty())
                jatekos.mozgas(Integer.parseInt(value)-1);
            else
                jatekos.csovegFelvetele(0);
        });
        Atallit.addActionListener((ActionEvent e) -> {
                    int akt = Kontroller.getInstance().getAktualisJatekos();
                    List<Jatekos> jatekosok = Kontroller.getInstance().getJatekosok();
                    jatekosok.get(akt).pumpaAtallitasa(Integer.parseInt(be.getText())-1, Integer.parseInt(ki.getText())-1);
                    Kontroller.getInstance().stepKor();
                    if(akt == jatekosok.size()-1){
                        Kontroller.getInstance().stepTime();
                    }
                    Kontroller.getInstance().setAktJatekos((akt+1)% jatekosok.size());
                });
        /*addActionListenerToButton(Atallit, be.getText() + "," + ki.getText(), (jatekos, value) -> {
            //ha esetleg a textfield-ekben nincs szoveg, akkor alapértékek vannak a lambdaban
            String[] values = value.split(",");
            if(!values[0].isEmpty() && !values[1].isEmpty())
                jatekos.pumpaAtallitasa(Integer.parseInt(values[0])-1, Integer.parseInt(values[1])-1);
            else
                jatekos.pumpaAtallitasa(0, 0);
        });*/
        addActionListenerToButton(Lyukaszt, null, (jatekos, value) -> {
            jatekos.csoKilyukasztasa();
        });
        addActionListenerToButton(Ragaszt, null, (jatekos, value) -> {
            jatekos.beragasztoz();
        });
        addActionListenerToButton(Javit, null, (jatekos, value) -> {
            jatekos.mezotJavit();
        });
        addActionListenerToButton(CsFelvesz, hanyadik, (jatekos, value) -> {
            if(!value.isEmpty())
                jatekos.csovegFelvetele(Integer.parseInt(value)-1);
            else
                jatekos.csovegFelvetele(0);
        });
        addActionListenerToButton(CsLerak, null, (jatekos, value) -> {
            jatekos.csovegetLerak();
        });
        addActionListenerToButton(PFelvesz, null, (jatekos, value) -> {
            jatekos.pumpaFelvetele();
        });
        addActionListenerToButton(PLerak, null, (jatekos, value) -> {
            jatekos.pumpatLerak();
        });
        addActionListenerToButton(Csuszosit, null, (jatekos, value) -> {
            jatekos.csuszosit();
        });
    }

    private void addActionListenerToButton(JButton button, JTextField wherefrom, BiConsumer<Jatekos, String> action) {
        button.addActionListener((ActionEvent e) -> {
            int akt = Kontroller.getInstance().getAktualisJatekos();
            List<Jatekos> jatekosok = Kontroller.getInstance().getJatekosok();
            Jatekos jatekos = jatekosok.get(akt);

            String value = wherefrom.getText();

            if (value != null) {
                action.accept(jatekos, value);
            } else {
                action.accept(jatekos, null);
            }

            Kontroller.getInstance().stepKor();

            if (akt == jatekosok.size() - 1) {
                Kontroller.getInstance().stepTime();
            }

            Kontroller.getInstance().setAktJatekos((akt + 1) % jatekosok.size());
        });
    }
}
