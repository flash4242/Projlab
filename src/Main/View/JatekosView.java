package Main.View;

import Main.Model.Jatekos;

import java.awt.*;

public abstract class JatekosView {
    private int x;
    private int y;

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    public void setX(int x){this.x=x-8;}//ne a mezo kozepere kezdje a rajzolast

    public void setY(int y){
        this.y=y;
    }
    public abstract void draw(Graphics g);
    public abstract Jatekos getJatekos();
}
