import java.util.ArrayList;
import java.util.List;

/**
 * A játékban lévő Csöveket reprezentáló osztály.
 * Egy cső két csúcsot köt össze és nincs rajta elágazás.
 * Ha lyukas kifolyik rajta a víz ezzel a szabotőrök pontjait növeli.
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
        Skeleton.startMethod(getClass().getName(), "jatekostElfogad()");
        if (Skeleton.kerdes("Allnak az adott csovon?")) {
            Skeleton.endMethod();
            return false;
        } else if (Skeleton.kerdes("Szabad-e valamelyik csoveg?")) {
            Skeleton.endMethod();
            return false;
        } else {
            jatekosRajta.add(j);
            Skeleton.endMethod();
            return true;
        }
    }

    /**
     * A paraméterként kapott pumpát lehelyezi a cső közepén ezzel egy új csövet is létrehozva.
     * @param p A kapott pumpa, amit lerakunk a csőre.
     */
    @Override
    public void targyLerakas(Pumpa p) {
        Skeleton.startMethod(getClass().getName(), "targyLerakas()");
        Csucs szCsucs = this.szomszedosCsucs.get(0);

        szCsucs.lecsatol(this);
        p.felcsatol(this);

        Cso fele = new Cso();
        szCsucs.felcsatol(fele);
        p.felcsatol(fele);
        Skeleton.endMethod();
    }

    /**
     * Visszaadja a csővel szomszédos csúcsok listáját, Mezo típusú listaként.
     * @return A szomszédos csúcsok listája.
     */
    @Override
    public List<? extends Mezo> getNeighbours() {
        Skeleton.startMethod(getClass().getName(), "getNeighbours()");
        Skeleton.endMethod();
        return szomszedosCsucs;
    }

    /**
     * A szabotőr elrontja a csövet.
     */
    @Override
    public void szabotorElront() {
        Skeleton.startMethod(getClass().getName(), "szabotorElront()");
        setRossz(true);
        Skeleton.endMethod();
    }

    /**
     * A szerelő megjavítja a csövet.
     */
    @Override
    public void szereloJavit() {
        Skeleton.startMethod(getClass().getName(), "szereloJavit()");
        setRossz(false);
        Skeleton.endMethod();
    }

    /**
     * A cső vizet kap. Ha üres, elfogadja, ha van benne víz, nem fogadja el.
     * @return Igazat ad vissza, ha elfogadta a vizet.
     */
    public boolean vizetKap() {
        Skeleton.startMethod(getClass().getName(), "vizetKap()");
        if (Skeleton.kerdes("Van a kimeneti csoben viz?")) {
            Skeleton.endMethod();
            return false;
        } else if (Skeleton.kerdes("Lyukas a kimeneti cso, vagy szabad a masik vege?")) {
            Kontroller.getInstance().pontNovel("szabotor");
        }
        Skeleton.endMethod();
        return true;
    }

    /**
     * A csőből vizet szivattyúznak. Ha üres nem ad semmit, ha van benne víz, azt továbbadja.
     * @return Igazat ad vissza, ha volt benne víz.
     */
    public boolean vizetVeszit() {
        Skeleton.startMethod(getClass().getName(), "vizetVeszit()");
        if(Skeleton.kerdes("Van a bemeneti csoben viz?")){
            Skeleton.endMethod();
            return true;
        }
        Skeleton.endMethod();
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
        Skeleton.startMethod(getClass().getName(), "setSzomszedosCsucs()");
        szomszedosCsucs.add(cs);
        Skeleton.endMethod();
    }

    /**
     * Beállítja a szomszédos csúcsok listáját.
     * @param csucsok A beállítandó szomszédos csúcsok listája.
     */
    public void setSzomszedosCsucs(List<Csucs> csucsok){
        Skeleton.startMethod(getClass().getName(), "setSzomszedosCsucs()");
        szomszedosCsucs = csucsok;
        Skeleton.endMethod();
    }

    /** /**
     * Beállítja a cső állapotát
     * @param r A cső állapota.
     */
    public void setRossz(boolean r){
        Skeleton.startMethod(getClass().getName(), "setRossz()");
        rossz = r;
        Skeleton.endMethod();
    }

}