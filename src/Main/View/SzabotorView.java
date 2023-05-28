package Main.View;

import Main.Kontroller;
import Main.Model.Jatekos;
import Main.Model.Szabotor;

import java.awt.*;

public class SzabotorView extends JatekosView{
    private Szabotor szabotor;

    public SzabotorView(Szabotor sz) {
        szabotor=sz;
    }

    @Override
    public void draw(Graphics g){
        g.setColor(new Color(109, 28, 28)); //brown
        if(szabotor.getRagados()){
            g.setColor(new Color(230, 243, 86));
        }

        int aktIndex = Kontroller.getInstance().getAktualisJatekos();
        Jatekos aktJatekos = Kontroller.getInstance().getJatekosok().get(aktIndex);
        if(aktJatekos == szabotor){
            g.setColor(new Color(102, 215, 50));
        }

        //ha több játékos van a mezőn, el kell, hogy férjenek
        int offset=szabotor.getAktMezo().getJatekosRajta().indexOf(szabotor);
        setX(getX()+offset*17);

        int[] xpoints = {getX()-8,getX()+8,getX()+8, getX()-8};
        int[] ypoints = {getY()-8, getY()-8, getY()+8, getY()+8};
        g.fillPolygon(xpoints, ypoints, 4/*negyszog*/);
    }

    @Override
    public Szabotor getJatekos(){
        return szabotor;
    }
}
