package Main.View;

import Main.Kontroller;
import Main.Model.Allapot;
import Main.Model.Cso;
import Main.Model.Jatekos;
import Main.Vec2;

import java.awt.*;
import java.util.List;
import java.util.Random;

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

    public void setY1(int y1){
        this.y1=y1;
    }

    public void setX1(int x1){
        this.x1=x1;
    }

    public void setY2(int y2){
        this.y2=y2;
    }

    public void setX2(int x2){
        this.x2=x2;
    }

    //default ctor
    CsoView(){
    }

    public CsoView(Cso cs){
        cso = cs;
    }

    public Cso getCso(){
        return cso;
    }

    /**
     * Segédfüggvény a cső kirajzolásához, kiszámol és visszaad egy téglalapot a megadott méretben,
     * a cső koordinátáira illeszkedve
     * @param size
     * a négyszög viszonylagos mérete
     * @return
     * visszaadja a négyszöget
     */
    private Polygon getPolygon(int size){
        Vec2 vektor1 = new Vec2(x1, y1);
        Vec2 vektor2 = new Vec2(x2, y2);
        Vec2 n = (vektor1.substract(vektor2)).getPerpendicular().normalize();
        Vec2 vonalCsucs1 = vektor1.add(n.multiply(size));
        Vec2 vonalCsucs2 = vektor1.add(n.multiply(-size));
        Vec2 vonalCsucs3 = vektor2.add(n.multiply(size));
        Vec2 vonalCsucs4 = vektor2.add(n.multiply(-size));
        int[] x_ek = {(int) vonalCsucs1.getX(), (int) vonalCsucs3.getX(), (int) vonalCsucs4.getX(), (int) vonalCsucs2.getX()};
        int[] y_ok = {(int) vonalCsucs1.getY(), (int) vonalCsucs3.getY(), (int) vonalCsucs4.getY(), (int) vonalCsucs2.getY()};
        Polygon polygon = new Polygon(x_ek, y_ok, 4);
        return polygon;
    }

    /**
     * Kirajzolja a csövet, lekérdezi a megjelenített cső adatait, majd annak megfelelő
     * színű és vastagságú téglalapot rajzol ki a cső két vége közé
     * @param g
     * a Graphics objektum, aminek a segítségével kirajzolunk
     */
    public void draw(Graphics g){
        if(cso.getAllapot() !=Allapot.NORMALIS) {
            if (cso.getAllapot() == Allapot.CSUSZOS) {
                g.setColor(Color.MAGENTA);
            } else if (cso.getAllapot() == Allapot.RAGADOS) {
                g.setColor(Color.YELLOW);
            }
            g.fillPolygon(getPolygon(4));
        }
        g.setColor(Color.BLACK);
        if(cso.getRossz()) {
            g.setColor(Color.RED);
        } else if (cso.getVanViz()) {
            g.setColor(Color.blue);
        }
        g.fillPolygon(getPolygon(2));
    }

    /**
     * Lekérdezi, kiszámítja a cső két végének koordinátáit, és eltárolja azokat,
     * illetve a rajta lévő játékosnak megadja a koordinátáit ahol áll
     */
    public void calculate(){
        switch (cso.getSzomszedosCsucs().size()){
            case 0:

                  //Ha a cső mindkét vége fel van véve, akkor a végeket hordozó játékosoktól kérdezi le
                  //a koordinátákat

                List<JatekosView> jatekosLista =GamePanel.getInstance().getPlayerViewFromCso(cso);
                x1 = jatekosLista.get(0).getX();
                y1 = jatekosLista.get(0).getY();
                x2 = jatekosLista.get(1).getX();
                y2 = jatekosLista.get(1).getY();
                break;
            case 1:

                //Ha a csőnek egy szomszédja van, akkor az egyik végét az azt hordozó játékostól,
                //a másikat pedig a szomszédos csúcstól kérdezi le

                List<JatekosView> jatekosLista1 =GamePanel.getInstance().getPlayerViewFromCso(cso);
                x1 = jatekosLista1.get(0).getX();
                y1 = jatekosLista1.get(0).getY();
                CsucsView csucs = GamePanel.getInstance().getCsucsViewFromCsucs(cso.getSzomszedosCsucs().get(0));
                x2 = csucs.x;
                y2 = csucs.y;
                break;
            default:

                // Ha a csőnek két szomszédja van, akkor a két szomszédjától kérdezi le a csővégek koordinátáit

                CsucsView csucs1 = GamePanel.getInstance().getCsucsViewFromCsucs(cso.getSzomszedosCsucs().get(0));
                x1 = csucs1.x;
                y1 = csucs1.y;
                CsucsView csucs2 = GamePanel.getInstance().getCsucsViewFromCsucs(cso.getSzomszedosCsucs().get(1));
                x2 = csucs2.x;
                y2 = csucs2.y;
                if(x1 == x2 && y1 == y2){
                    x1 = x1-60;
                }
                break;
        }

        //Ha áll játékos a csövön, akkor a játékosnak beállítjuk koordinátának a cső közepét
        List<Jatekos> jatekosList = cso.getJatekosRajta();
        if(!jatekosList.isEmpty()){
            JatekosView player = GamePanel.getInstance().getPlayerViewFromPlayer(jatekosList.get(0));
            player.setX(x1/2+x2/2);
            player.setY(y1/2+y2+2);
        }
    }
}
