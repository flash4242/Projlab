package Main.View;

import Main.Model.Cso;

import java.awt.*;

public class CsoView {
    private Cso cso;
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public int getY1() {
        return y1;
    }

    public int getX1() {
        return x1;
    }

    public int getY2() {
        return y2;
    }

    public int getX2() {
        return x2;
    }

    //default ctor
    CsoView(){
    }

    public Cso getCso(){
        return cso;
    }

    public void draw(Graphics g){
    }

    public void calculate(){

    }
}
