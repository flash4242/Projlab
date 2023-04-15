import java.util.ArrayList;
import java.util.List;

/**
 * A játékban lévő Csúcsokat reprezentáló absztrakt osztály.
 */
public abstract class Csucs extends Mezo{
    /**
     * A szomszédos csövek listája.
     */
    private List<Cso> szomszedosCso;

    /**
     * Létrehoz egy Csucsot, és inicializálja a szomszedosCso listát.
     */
    public Csucs(){
        super();
        szomszedosCso = new ArrayList<Cso>();
    }

    /**
     * Kap egy játékost és hozzáadja a jatekosRajta listához.
     * @param j A játékos, akit hozzáadunk a listához.
     * @return Igazat ad vissza minden esetben, hogy tájokoztassuk a hívót a hozzáadásról.
     */
    @Override
    public boolean jatekostElfogad(Jatekos j) {
        jatekosRajta.add(j);
        return true;
    }

    /**
     * A paraméterként kapott csövet hozzáadja a szomszedosCso listához. Továbbá beállítja a csőnek magat szomszédnak.
     * @param cs A kapott cső, amit hozzáadunk a listához.
     */
    @Override
    public void targyLerakas(Cso cs){
        cs.setSzomszedosCsucs(this);
        szomszedosCso.add(cs);
    }

    /**
     * A csúcs gyárt egy csövet. Ez üres függvény, mert nem minden csúcs tud csövet gyártani.
     * Azon csúcsoknak, amik tudnak csövet gyártani felül kell írniuk ezt a függvényt.
     */
    public void csoLetrehozasa(){}

    /**
     * A paraméterként kapott Mezőt eltávolítja a szomszedosCso listából, amennyiben ez nem az utolsó cső ami a csúcshoz van kötve.
     * @param m A paraméterként kapott Mező, amit eltávolítunk a listából.
     * @return Igazat ad vissza, ha eltávolítottuk a listából, egyébként hamisat.
     */
    @Override
    public boolean setCsoToNull(Mezo m){
        if(!Skeleton.kerdes("Ez az utolso cso ami a csucshoz hozza van kotve?")) {
            szomszedosCso.remove(m);
            return true;
        }
        return false;
    }

    /**
     * A paraméterként kapott mezőt nem lehet kivenni a Csucs listájából.
     * A hívót tájokoztattjuk azzal, hogy hamisat adunk vissza.
     * @param m A kapott mező, amit kivennénk a listából.
     * @return Mindig hamisat ad vissza.
     */
    @Override
    public boolean setCsucsToNull(Mezo m){
        return false;
    }

    /**
     * A Csucs működését jelző függvény. Ezt minden leszármazottnak meg kell valósítania.
     */
    public abstract void vizetPumpal();

    /**
     * A Kontroller elront egy Csucsot. Ez egy üres függvény, mert a Csucs nem tud elromlani.
     * Azon leszármazottakban amik el tudnak romlani meg kell valósítani
     */
    public void kontrollerElront(){}

    /**
     * A kapott csövet eltávolítja a szomszedosCso listából, és a cső szomszédjából is a csúcsot.
     * @param cs A kapott cső, amit eltávolítunk a listából.
     */
    public void lecsatol(Cso cs){
        szomszedosCso.remove(cs);
        cs.getSzomszedosCsucs().remove(this);
    }

    /**
     * A kapott csövet hozzáadja a szomszedosCso listához, és a cső szomszédjához is a csúcsot.
     * @param cs A kapott cső, amit hozzáadunk a listához.
     */
    public void felcsatol(Cso cs){
        szomszedosCso.add(cs);
        cs.getSzomszedosCsucs().add(this);
    }

    /**
     * Visszaadja a szomszédos csövek listáját, Mezo típusú listaként.
     * @return A szomszédos csövek listája.
     */
    @Override
    public List<? extends Mezo> getNeighbours(){
        return szomszedosCso;
    }

    /**
     * Visszaadja a szomszédos csövek listáját, Cso típusú listaként.
     * @return A szomszédos csövek listája.
     */
    public List<Cso> getSzomszedosCso(){
        return szomszedosCso;
    }
    /**
     * Beállítja a szomszédos csövek listáját.
     * @param szomszedosCso A beállítandó szomszédos csövek listája.
     */
    public void setSzomszedosCso(List<Cso> szomszedosCso){
        this.szomszedosCso = szomszedosCso;
    }
}
