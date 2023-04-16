import java.util.ArrayList;
import java.util.List;

/**
 * A játékban lévő Csöveket reprezentáló osztály.
 */
public class Cso extends Mezo {
    /**
     * A szomszédos csúcsok listája
     */
    private List<Csucs> szomszedosCsucs;

    /**
     * A cső állapota.
     */
    boolean rossz;

    /**
     * Létrehoz egy Csövet, és inicializálja a szomszedosCsucs listát.
     */
    public Cso() {
        super();
        szomszedosCsucs = new ArrayList<>();
    }

    /**
     * Kap egy játékost és ha üres a jatekosRajta lista és kötöttek a csővégei, akkor hozzáadja a jatekosRajta listához.
     *
     * @param j A játékos, aki érkezik.
     * @return Igazat ad vissza ha elfogadja a játékost.
     */
    public boolean jatekostElfogad(Jatekos j) {
        if (Skeleton.kerdes("Allnak az adott csovon?")) {
            return false;
        } else if (Skeleton.kerdes("Szabad-e valamelyik csoveg?")) {
            return false;
        } else {
            jatekosRajta.add(j);
            return true;
        }
    }

    /**
     * A paraméterként kapott pumpát lehelyezi a cső közepén ezzel egy új csövet is létrehozva.
     * @param p A kapott pumpa, amit lerakunk a csőre.
     */
    @Override
    public void targyLerakas(Pumpa p) {
        //csucs amirol lecsatoljuk a csovet
        Csucs szCsucs = this.szomszedosCsucs.get(0);
        //az eredetit átkötjük
        szCsucs.lecsatol(this);
        p.felcsatol(this);
        //az uj csőrész
        Cso fele = new Cso();
        szCsucs.felcsatol(fele);
        p.felcsatol(fele);

    }

    /**
     * A paraméterként kapott Mezőt eltávolítja a szomszedosCsucs listából, amennyiben a szomszedosCsucs lista 2 elemű.
     * @param m A paraméterként kapott Mező, amit eltávolítunk a listából.
     * @return Igazat ad vissza, ha eltávolítottuk a listából, egyébként hamisat.
     */
    public boolean setCsucsToNull(Mezo m) {
        if(!Skeleton.kerdes("Szabad a masik csoveg?")){
            szomszedosCsucs.remove(m);
            return true;
        }
        return false;
    }

    /**
     * A paraméterként kapott mezőt nem lehet kivenni a Cso listájából.
     * A hívót tájokoztattjuk azzal, hogy hamisat adunk vissza.
     * @param m A paraméterként kapott Mező, amit eltávolítunk.
     * @return Mindig hamisat ad vissza.
     */
    public boolean setCsoToNull(Mezo m) {
        return false;
    }

    /**
     * Visszaadja a csővel szomszédos csúcsok listáját, Mezo típusú listaként.
     * @return A szomszédos csúcsok listája.
     */
    @Override
    public List<? extends Mezo> getNeighbours() {
        return szomszedosCsucs;
    }

    /**
     * A szabotőr elrontja a csövet.
     */
    @Override
    public void szabotorElront() {
        setRossz(true);
    }

    /**
     * A szerelő megjavítja a csövet.
     */
    @Override
    public void szereloJavit() {
        setRossz(false);
    }

    /**
     * A cső vizet kap. Ha üres, elfogadja, ha van benne víz, nem fogadja el.
     * @return Igazat ad vissza, ha elfogadta a vizet.
     */
    public boolean vizetKap() {
        if (Skeleton.kerdes("Van a kimeneti csoben viz?")) {
            return false;
        } else if (Skeleton.kerdes("Lyukas a kimeneti cso, vagy szabad a masik vege?")) {
            Kontroller.getInstance().pontNovel("szabotor");
        }
        return true;
    }

    /**
     * A csőből vizet szivattyúznak. Ha üres nem ad semmit, ha van benne víz, azt továbbadja.
     * @return Igazat ad vissza, ha volt benne víz.
     */
    public boolean vizetVeszit() {
        if(Skeleton.kerdes("Van a bemeneti csoben viz?")){
            return true;
        }
        return false;
    }

    /**
     * Visszaadja a szomszédos csúcsok listáját, Csucs típusú listaként.
     * @return A szomszédos csúcsok listája.
     */
    public List<Csucs> getSzomszedosCsucs() {
        Skeleton.startMethod(getClass().getName(), "getSzomszedosCsucs()");
        Skeleton.endMethod();
        return szomszedosCsucs;
    }

    /**
     * Beveszi a szomszédos csúcsok listájába a paraméterül kapott csúcsot
     * @param cs A csúcs, ami bekerül a szomszédok listájába
     */
    public void setSzomszedosCsucs(Csucs cs){
        szomszedosCsucs.add(cs);
    }

    /**
     * Beállítja a szomszédos csúcsok listáját.
     * @param csucsok A beállítandó szomszédos csúcsok listája.
     */
    public void setSzomszedosCsucs(List<Csucs> csucsok){
        szomszedosCsucs = csucsok;
    }

    /** /**
     * Beállítja a cső állapotát
     * @param r A cső állapota.
     */
    public void setRossz(boolean r){
        rossz = r;
    }

}
