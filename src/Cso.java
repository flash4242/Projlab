import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A csőrendszerben lévő csöveket reprezentáló osztály.
 * Két csúcsot köthet össze, valamint azokról a csúcsokról le lehet csatolni és más csúcsra felcsatolni.
 * Passzív elem tehát víz úgy folyhat rajta, ha egy aktív elem szivattyúz belőle vagy bele pumpál.
 */
public class Cso extends Mezo {

    /**
     * A szomszédos csúcsok listája
     */
    private List<Csucs> szomszedosCsucs;

    /**
     * A cső működés szerinti állapota.
     */
    private boolean rossz;

    /**
     * A cső ideiglenes állapotából hátralevő körök száma.
     */
    private int timeToNormal;

    /**
     * A cső ideiglenes állapota, ami a játékosok mozgását befolyásolhatja.
     */
    private Allapot allapot = Allapot.NORMALIS;

    /**
     * A cső nem lyukaszthatóságát jelzi körök számában mérve.
     */
    private int foltozasiGarancia;

    /**
     * Létrehoz egy Csövet, és inicializálja a szomszedosCsucs listát.
     */
    public Cso() {
        super();
        szomszedosCsucs = new ArrayList<>();
    }

    public void removeNeighbour(Csucs csucs){
        szomszedosCsucs.remove(csucs);
    }

    /**
     * Kap egy játékost és jelzi, hogy a játékos a csőre tudott-e lépni.
     * @param j A játékos, aki érkezik.
     * @return Igazat ad vissza ha a játékos ezen a mezőn marad mozgása végén.
     */
    public boolean jatekostElfogad(Jatekos j) {
        if(jatekosRajta.size() == 0 && szomszedosCsucs.size() == 2){
            if(allapot == Allapot.NORMALIS){
                return true;
            } else if (allapot == Allapot.RAGADOS) {
                j.leragad(true);
                return true;
            } else if (allapot == Allapot.CSUSZOS) {
                Mezo aktMezo = j.getAktMezo();
                aktMezo.jatekostEltavolit(j);
                Random random = new Random();
                int irany = random.nextInt(2);
                szomszedosCsucs.get(irany).jatekostElfogad(j);
                j.setAktMezo(szomszedosCsucs.get(irany));
                return false;
            }
        }
        return false;
    }

    /**
     * A paraméterként kapott pumpát lehelyezi a cső közepén ezzel egy új csövet is létrehozva.
     * @param p A kapott pumpa, amit lerakunk a csőre.
     */
    @Override
    public void targyLerakas(Pumpa p) {
        Cso fele = new Cso();
        Kontroller.getInstance().addCso(fele);
        Kontroller.getInstance().addCsucs(p);

        Csucs szCsucs = this.szomszedosCsucs.get(0);
        szCsucs.csoCsere(this, fele);
        szCsucs.felcsatol(fele);
        p.felcsatol(fele);
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
     * Egy játékos elrontja a csövet.
     */
    @Override
    public void jatekosElront() {
        if(foltozasiGarancia == 0)
            setRossz(true);
    }

    /**
     * A szerelő megjavítja a csövet.
     */
    @Override
    public void szereloJavit() {
        if(rossz){
            setRossz(false);
            Random random = new Random();
            foltozasiGarancia = random.nextInt(3) + 2;
        }
    }

    /**
     * A cső vizet kap. Ha üres, elfogadja, ha van benne víz, nem fogadja el.
     * @return Igazat ad vissza, ha elfogadta a vizet.
     */
    public boolean vizetKap() {
        if(vanViz)
            return false;
        if(szomszedosCsucs.size() != 2 || rossz){
            Kontroller.getInstance().pontNovel("szabotor");
            return true;
        }
        vanViz = true;
        return true;
    }

    /**
     * A csőből vizet szivattyúznak. Ha üres nem ad semmit, ha van benne víz, azt továbbadja.
     * @return Igazat ad vissza, ha volt benne víz.
     */
    public boolean vizetVeszit() {
        if(vanViz){
            vanViz = false;
            return true;
        }
        return false;
    }

    /**
     * A csőnek megváltoztatják az állapotát. CSUSZOS vagy RAGADOS csak abban az esetben lehet, ha eddig NORMALIS volt.
     * @param allapot az állapot, amire változik
     */
    @Override
    public void allapotValtozas(Allapot allapot) {
        if(allapot == Allapot.NORMALIS) {
            this.allapot = Allapot.NORMALIS;
        } else if (this.allapot == Allapot.NORMALIS) {
            if(allapot == Allapot.RAGADOS){
                this.allapot = Allapot.RAGADOS;
            }
            else {
                this.allapot = Allapot.CSUSZOS;
            }
            timeToNormal = 3;
        }
    }

    /**
     * Cső állapotaiból hátralévő idő csökken, ami állapot változást idézhet elő, vagy szabadon engedhet odaragadt játékost.
     */
    public void stepTime(){
        if(timeToNormal == 1){
            allapotValtozas(Allapot.NORMALIS);
            if(jatekosRajta.size() != 0){
                jatekosRajta.get(0).leragad(false);
            }
        }
        if(timeToNormal > 0)
            timeToNormal--;
        if(foltozasiGarancia > 0)
            foltozasiGarancia--;
    }

    /**
     * Beveszi a szomszédos csúcsok listájába a paraméterül kapott csúcsot
     * @param cs A csúcs, ami bekerül a szomszédok listájába
     */
    public void addCsucs(Csucs cs){
        szomszedosCsucs.add(cs);
    }

    /**
     * Eltávolítja a szomszédos csúcsok listájából a paraméterül kapott csúcsot
     * @param cs A csúcs, amit eltávolít a szomszédok listájából
     */
    public void setCsucsToNull(Csucs cs){
        szomszedosCsucs.remove(cs);
    }

    /**
     * Beállítja a szomszédos csúcsok listáját.
     * @param csucsok A beállítandó szomszédos csúcsok listája.
     */
    public void setSzomszedosCsucs(List<Csucs> csucsok){
        szomszedosCsucs = csucsok;
    }

    /**
     * Beállítja a cső állapotát
     * @param r A cső állapota.
     */
    public void setRossz(boolean r){
        Skeleton.startMethod(getClass().getName(), "setRossz()");
        rossz = r;
        Skeleton.endMethod();
    }

    //TODO protohoz getterek??
    public boolean getRossz(){
        return rossz;
    }

    public Allapot getAllapot(){
        return allapot;
    }

    public int getTimeToNormal(){
        return timeToNormal;
    }

    public int getFoltozasiGarancia(){
        return foltozasiGarancia;
    }

    /**
     * Visszaadja a szomszédos csúcsok listáját, Csucs típusú listaként.
     * @return A szomszédos csúcsok listája.
     */
    public List<Csucs> getSzomszedosCsucs() {
        return szomszedosCsucs;
    }

}