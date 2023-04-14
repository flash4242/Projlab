import java.util.ArrayList;
import java.util.List;

/**
 * A játékban levo Csúcsokat reprezentáló absztrakt osztály.
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

    //TODO: Kell ez?
    /**
     * Eltávolítja a paramétkent kapott játekost a játekosRajta listából.
     * @param j A játékos, akit el akarunk távolítani a listaból.
     */
    @Override
    public void jatekostEltavolit(Jatekos j){
        jatekosRajta.remove(j);
    }

    /**
     * A paraméterként kapott csövet hozzáadja a szomszedosCso listához. Továbbá beállítja a csőnek magat szomszédnak.
     * @param cs A kapott cső, amit hozzáadunk a listához.
     */
    @Override
    public void targyLerakas(Cso cs){
        //ToDo Ohm, nem tudom hogy hogyan mukodik a setSzomszedosCsucs. A diagrammon igy van, lehet hogy valtoztatni kell
        cs.setSzomszedosCsucs(this);
        szomszedosCso.add(cs);
    }

    /**
     * A paraméterként kapott Mezőt eltávolítja a szomszedosCso listából.
     * @param m A paraméterként kapott Mező, amit eltávolítunk a listából.
     * @return Igazat ad vissza minden esetben, hogy tájokoztassuk a hívót a törlés sikerességéről.
     */
    @Override
    public boolean setCsoToNull(Mezo m){
        szomszedosCso.remove(m);
        return true;
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
     * Visszaadja a szomszédos csövek listáját, Mezo típusú listaként.
     * @return A szomszédos csövek listája.
     */
    @Override
    public List<? extends Mezo> getNeighbours(){
        return szomszedosCso;
    }

    /**
     * Visszaadja a szomszédos csövek listáját, Cso típusú listaként. A függvény csak a Csucsok számára elérhető.
     * @return A szomszédos csövek listája.
     */
    protected List<Cso> getSzomszedosCso(){
        return szomszedosCso;
    }
}
