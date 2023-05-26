package Main.View;

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

    public void setX(int x){
        this.x=x;
    }

    public void setY(int y){
        this.y=y;
    }

    public abstract void draw(Graphics g);
    public abstract void calculate();
}
