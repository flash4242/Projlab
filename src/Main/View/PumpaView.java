package Main.View;

import Main.Model.Cso;
import Main.Model.Csucs;
import Main.Model.Pumpa;

import java.awt.*;
import java.util.List;

public class PumpaView extends CsucsView{
    private Pumpa pumpa;

    private int radius = 40;

    public PumpaView(Pumpa p){
        pumpa = p;
    }
    @Override
    public void draw(Graphics g){
        int widthOfRossz = 10;
        if (pumpa.getRossz()){
            g.setColor(Color.RED);
            g.fillOval(x-radius-widthOfRossz/2,y-radius-widthOfRossz/2,2*radius+widthOfRossz,2*radius+widthOfRossz);
        }
        if(pumpa.getVanViz()){
            g.setColor(Color.BLUE);
        }
        else{
            g.setColor(Color.BLACK);
        }
        g.fillOval(x-radius,y-radius,2*radius,2*radius);

        g.setColor(Color.WHITE);
        //Kirajzolja a szomszedos csovek szamat.
        List<Cso> szomszedoscsovek = pumpa.getSzomszedosCso();
        for (Cso c : szomszedoscsovek){

        }
    }

    @Override
    public void calculate(){
    }

    @Override
    public Csucs getCsucs(){
        return pumpa;
    }
}
