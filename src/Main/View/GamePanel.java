package Main.View;

import Main.Kontroller;
import Main.Model.*;
import Main.Vec2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A játék kirajzolásáért felelős panel.
 */
public class GamePanel extends JPanel {

    /**
     * A singelton instance-je a GamePanelnek.
     */
    private static GamePanel single_instance = null;

    /**
     * Singleton osztály, hogy csak egyetlen GamePanel objektum létezzen.
     * @return A GamePanel objektum.
     */
    public static synchronized GamePanel getInstance()
    {
        if (single_instance == null)
            single_instance = new GamePanel();

        return single_instance;
    }

    /**
     * A játékosok nézeteit tartalmazó lista.
     */
    List<JatekosView> jatekosok;

    /**
     * A csövek nézeteit tartalmazó lista.
     */
    List<CsoView> csovek;

    /**
     * A csúcsok nézeteit tartalmazó lista.
     */
    List<CsucsView> csucsok;

    /**
     * Default Konstruktora a Game Panelnek.
     */
    private GamePanel(){
        super();
        jatekosok = new ArrayList<>();
        csovek = new ArrayList<>();
        csucsok = new ArrayList<>();

        //TODO EZ ALATT CSAK TESZTELES FOLYT EGY PELDA PALYA, DELETE LATER
        Pumpa p = new Pumpa();
        p.setVanViz(true);
        p.setRossz(true);
        PumpaView pm = new PumpaView(p);
        pm.setX(400);
        pm.setY(200);
        csucsok.add(pm);

        Ciszterna c = new Ciszterna();
        CiszternaView cv = new CiszternaView(c);
        cv.setX(800);
        cv.setY(200);
        csucsok.add(cv);

        Forras f = new Forras();
        ForrasView fv = new ForrasView(f);
        fv.setX(300);
        fv.setY(100);
        csucsok.add(fv);

        Cso cs = new Cso();
        cs.allapotValtozas(Allapot.CSUSZOS);
        p.felcsatol(cs);
        CsoView csv = new CsoView(cs);
        csv.setX1(400);
        csv.setY1(200);
        csv.setX2(800);
        csv.setY2(200);
        csovek.add(csv);

        Cso cs2 = new Cso();
        cs2.allapotValtozas(Allapot.RAGADOS);
        p.felcsatol(cs2);
        CsoView csv2 = new CsoView(cs2);
        csv2.setX1(400);
        csv2.setY1(200);
        csv2.setX2(300);
        csv2.setY2(100);
        csovek.add(csv2);

        p.setBemenetiCso(1);
        c.felcsatol(cs);
        f.felcsatol(cs2);

        //jatekos tesztek
        Szerelo sz1 =new Szerelo();
        sz1.setAktMezo(p);
        p.setJatekosRajta(sz1);
        SzereloView szv= new SzereloView(sz1);
        szv.setX(pm.getX()); //pumpára kerül
        szv.setY(pm.getY());
        jatekosok.add(szv);

        Szerelo sz2 =new Szerelo();
        sz2.setAktMezo(p);
        p.setJatekosRajta(sz2);
        SzereloView szv2= new SzereloView(sz2);
        szv2.setX(pm.getX()); //pumpára kerül
        szv2.setY(pm.getY());
        jatekosok.add(szv2);

        Szabotor sza =new Szabotor();
        sza.setAktMezo(p);
        p.setJatekosRajta(sza);
        SzabotorView szav= new SzabotorView(sza);
        szav.setX(pm.getX()); //pumpára kerül
        szav.setY(pm.getY());
        jatekosok.add(szav);

        pm.calculate();

        Szabotor sza2 =new Szabotor();
        sza2.setAktMezo(cs);
        cs.setJatekosRajta(sza2);
        SzabotorView sza2v= new SzabotorView(sza2);
        sza2v.setX((csv.getX1()+csv.getX2())/2); //pumpára kerül
        sza2v.setY((csv.getY1()+csv.getY2())/2);
        sza2.leragad(true);
        jatekosok.add(sza2v);

        Kontroller.getInstance().addJatekos(sz1);
        Kontroller.getInstance().addJatekos(sz2);
        Kontroller.getInstance().addJatekos(sza);
        Kontroller.getInstance().addJatekos(sza2);

        Kontroller.getInstance().setAktJatekos(2);
    }

    /**
     * Kirajzolja a pályát. Ez a csővek, csúcsok és játékosok kirajzolását jelenti.
     * @param g A grafikus objektum, amire rajzolunk.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (CsoView cs : csovek){
            cs.draw(g);
        }
        for (CsucsView cs : csucsok){
            cs.draw(g);
        }
        for (JatekosView j : jatekosok){
            j.draw(g);
        }
    }

    /**
     * Kiszámolja az összes nézetet és megjeleníti az összes nézetet.
     */
    public void drawAll(){
        calculateAll();
        showAll();
    }

    /**
     * Megjeleníti az összes nézetet.
     */
    public void showAll(){
        this.repaint();
    }

    /**
     * Minden nézetet újraszámol.
     * A csúcsokat egszer, a csöveket kétszer.
     */
    public void calculateAll(){
        for (CsucsView cs : csucsok){
            cs.calculate();
        }
        for (CsoView cs : csovek){
            cs.calculate();
        }
        for (CsoView cs : csovek){
            cs.calculate();
        }
    }

    /**
     * Visszaadja a játékos nézetet a játékos alapján.
     * @param j A játékos, aminek a nézetét keresi.
     * @return A játékos nézete.
     */
    public JatekosView getPlayerViewFromPlayer(Jatekos j){
        for (JatekosView jv : jatekosok){
            if (jv.getJatekos() == j){
                return jv;
            }
        }
        return null;
    }

    /**
     * Visszaadja a csúcs nézetet a csúcs alapján.
     * @param c A csúcs, aminek a nézetét keresi.
     * @return A csúcs nézete.
     */
    public CsucsView getCsucsViewFromCsucs(Csucs c){
        for (CsucsView cv : csucsok){
            if (cv.getCsucs() == c){
                return cv;
            }
        }
        return null;
    }

    /**
     * Visszaadja mindazon játékosokat, akik a csövet cipelik.
     * @param c A cső, aminek a cipelőit keresi.
     * @return A csövet cipelő játékosok listája.
     */
    public List<JatekosView> getPlayerViewFromCso(Cso c){
        List<JatekosView> jatekosok = new ArrayList<>();
        for (JatekosView jv : this.jatekosok){
            if (jv.getJatekos().getCso() == c){
                jatekosok.add(jv);
            }
        }
        return jatekosok;
    }

    /**
     * Visszaadja a megadott csőhöz tartozó CsoView-t.
     * @param c A cső, aminek a CsoView-ját keresi.
     * @return A csőhöz tartozó CsoView.
     */
    public CsoView getCsoViewFromCso(Cso c){
        for (CsoView cv : csovek){
            if (cv.getCso() == c){
                return cv;
            }
        }
        return null;
    }

    /**
     * Kirajzol egy háromszöget a megadott helyre, a megadott irányba.
     * @param g Graphics, amire rajzolni kell.
     * @param from Az egyenlő szárú háromszög rövidebb oldalának a középpontja.
     * @param dir Az egyenlő szárú háromszög rövidebb oldalának a közeppontjából a szembe lévő csúcsba mutató vektor, ezzel arányos lesz a háromszög elnyúlása.
     */
    public void drawTriangle(Graphics g, Vec2 from, Vec2 dir){
        int size = 8;
        Vec2 a;
        Vec2 b;
        Vec2 c;

        a = from.add(dir.multiply(size));
        Vec2 oldal = dir.getPerpendicular().normalize();
        b = from.add(oldal.multiply(size));
        c = from.add(oldal.multiply(-size));

        int x[] = {(int)a.getX(),(int)b.getX(),(int)c.getX()};
        int y[] = {(int)a.getY(),(int)b.getY(),(int)c.getY()};
        g.fillPolygon(x,y,3);
    }
}

