package Main.View;

import Main.Model.Szerelo;

import java.awt.*;

public class SzereloView extends JatekosView{
    private Szerelo szerelo;


    SzereloView(Szerelo sz){
        szerelo=sz;
    }
    @Override
    public void draw(Graphics g){
        setY(getY()-8); //a haramoszog pozicioja az egyik csucsat adja meg, ezert kell eltolni

        g.setColor(new Color(109, 28, 28)); //brown
        if(szerelo.getRagados()){
            g.setColor(new Color(230, 243, 86));
        }
        if(getAktiv()){
            g.setColor(new Color(102, 215, 50));
        }

        //ha több játékos van a mezőn, el kell, hogy férjenek
        int offset=szerelo.getAktMezo().getJatekosRajta().indexOf(szerelo);
        setX(getX()+offset*17);
        int[] xpoints = {getX(),getX()-8,getX()+8};
        int[] ypoints = {getY(), getY()+16, getY()+16};
        g.fillPolygon(xpoints, ypoints, 3/*haromszog*/);
    }
    @Override
    public Szerelo getJatekos(){
        return szerelo;
    }
}
