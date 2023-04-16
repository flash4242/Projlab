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
        Skeleton.startMethod(getClass().getName(), "jatekostElfogad()");
        jatekosRajta.add(j);
        Skeleton.endMethod();
        return true;
    }

    /**
     * A paraméterként kapott csövet hozzáadja a szomszedosCso listához. Továbbá beállítja a csőnek magat szomszédnak.
     * @param cs A kapott cső, amit hozzáadunk a listához.
     */
    @Override
    public void targyLerakas(Cso cs){
        Skeleton.startMethod(getClass().getName(), "targyLerakas()");
        felcsatol(cs);
        Skeleton.endMethod();
    }

    /**
     * A csúcs gyárt egy csövet. Ez üres függvény, mert nem minden csúcs tud csövet gyártani.
     * Azon csúcsoknak, amik tudnak csövet gyártani felül kell írniuk ezt a függvényt.
     */
    public void csoLetrehozasa(){
        Skeleton.startMethod(getClass().getName(), "csoLetrehozasa()");
        Skeleton.endMethod();
    }

    /**
     * Szólunk a csúcsnak hogy szeretnénk a cs-edik csövét megkapni.
     * Ha a csúcsnak csak egy szomszédos csöve van és nincs rajta senki, akkor visszaadja és lecsatolja a csövet.
     * Különben null-t ad vissza.
     * @param cs A szomszédos csövek közül melyiket szeretnénk megkapni.
     * @return A csúcs által visszaadott cső, amennyiben volt ilyen, egyébként null.
     */
    @Override
    public Cso adjCsovet(int cs){
        Skeleton.startMethod(getClass().getName(), "adjCsovet()");
        if(Skeleton.kerdes("Több mint egy szomszédos csöve van a csúcsnak?") && Skeleton.kerdes("Nem áll senki a szomszédos csövön?")){
            Cso temp = szomszedosCso.get(cs%szomszedosCso.size());
            lecsatol(temp);
            Skeleton.endMethod();
            return temp;
        }
        else{
            Skeleton.endMethod();
            return null;
        }
    }

    /**
     * A Csucs működését jelző függvény. Ezt minden leszármazottnak meg kell valósítania.
     */
    public abstract void vizetPumpal();

    /**
     * A Kontroller elront egy Csucsot. Ez egy üres függvény, mert a Csucs nem tud elromlani.
     * Azon leszármazottakban amik el tudnak romlani meg kell valósítani
     */
    public void kontrollerElront(){
        Skeleton.startMethod(getClass().getName(), "kontrollerElront()");
        Skeleton.endMethod();
    }

    /**
     * A kapott csövet eltávolítja a szomszedosCso listából, és a cső szomszédjából is a csúcsot.
     * @param cs A kapott cső, amit eltávolítunk a listából.
     */
    public void lecsatol(Cso cs){
        Skeleton.startMethod(getClass().getName(), "lecsatol()");
        szomszedosCso.remove(cs);
        cs.setSzomszedosCsucs(this);
        Skeleton.endMethod();
    }

    /**
     * A kapott csövet hozzáadja a szomszedosCso listához, és a cső szomszédjához is a csúcsot.
     * @param cs A kapott cső, amit hozzáadunk a listához.
     */
    public void felcsatol(Cso cs){
        Skeleton.startMethod(getClass().getName(), "felcsatol()");
        szomszedosCso.add(cs);
        cs.getSzomszedosCsucs().add(this);
        Skeleton.endMethod();
    }

    /**
     * Visszaadja a szomszédos csövek listáját, Mezo típusú listaként.
     * @return A szomszédos csövek listája.
     */
    @Override
    public List<? extends Mezo> getNeighbours(){
        Skeleton.startMethod(getClass().getName(), "getNeighbours()");
        Skeleton.endMethod();
        return szomszedosCso;
    }

    /**
     * Visszaadja a szomszédos csövek listáját, Cso típusú listaként.
     * @return A szomszédos csövek listája.
     */
    public List<Cso> getSzomszedosCso(){
        Skeleton.startMethod(getClass().getName(), "getSzomszedosCso()");
        Skeleton.endMethod();
        return szomszedosCso;
    }
    /**
     * Beállítja a szomszédos csövek listáját.
     * @param szomszedosCso A beállítandó szomszédos csövek listája.
     */
    public void setSzomszedosCso(List<Cso> szomszedosCso){
        Skeleton.startMethod(getClass().getName(), "setSzomszedosCso()");
        this.szomszedosCso = szomszedosCso;
        Skeleton.endMethod();
    }
}
