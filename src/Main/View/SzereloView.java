package Main.View;

import Main.Kontroller;
import Main.Model.Jatekos;
import Main.Model.Szerelo;

import java.awt.*;

public class SzereloView extends JatekosView{
    private Szerelo szerelo;


    public SzereloView(Szerelo sz){
        szerelo=sz;
    }
    @Override
    public void draw(Graphics g){
        //setY(getY()-8); //a haramoszog pozicioja az egyik csucsat adja meg, ezert kell eltolni
            setX(getX()+15); // valamiért arrébb van a csúcs mint kéne

        g.setColor(new Color(109, 28, 28)); //brown
        if(szerelo.getRagados()){
            g.setColor(new Color(230, 243, 86));
        }

        int aktIndex = Kontroller.getInstance().getAktualisJatekos();
        Jatekos aktJatekos = Kontroller.getInstance().getJatekosok().get(aktIndex);
        if(aktJatekos == szerelo){
            g.setColor(new Color(102, 215, 50));
        }

        //ha több játékos van a mezőn, el kell, hogy férjenek
        //int offset=szerelo.getAktMezo().getJatekosRajta().indexOf(szerelo);
        //setX(getX()+offset*17);
        int[] xpoints = {getX(),(int)(getX()-9),(int)(getX()+9)};
        int[] ypoints = {getY()-9, getY()+9, getY()+9};
        g.fillPolygon(xpoints, ypoints, 3/*haromszog*/);
    }
    @Override
    public Szerelo getJatekos(){
        return szerelo;
    }
}
