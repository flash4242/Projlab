package Main.View;

import Main.Model.Csucs;

import java.awt.*;

public abstract class CsucsView {
    int x;
    int y;

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setY(int y){
        this.y=y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public abstract void draw(Graphics g);
    public void calculate(){

    }
    public abstract Csucs getCsucs();
}
