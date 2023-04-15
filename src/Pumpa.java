public class Pumpa extends Csucs{

    /**
     * Index a szomszedosCso listához. A bemeneti csövet jelzi.
     */
    private int bemenetiCso;

    /**
     * Index a szomszedosCso listához. A kimeneti csövet jelzi.
     */
    private int kimenetiCso;

    /**
     * A pumpa állapota
     */
    boolean rossz;

    /**
     * Létrehoz egy Pumpa objektumot
     */
    public Pumpa() { super(); }

    /**
     * Átállítja a pumpa bemeneti és kimeneti csövét.
     * @param kimeneti bemeneti cső index a szomszédos mezőkhöz
     * @param bemeneti kimeneti cső index a szomszédos mezőkhöz
     */
    @Override
    public void atallit(int kimeneti, int bemeneti) {
        //TODO
    }

    /**
     * A pumpa a kimeneti csövébe vizet pumpál, majd a bemeneti csőből szivattyúz.
     */
    @Override
    public void vizetPumpal() {
        //TODO
    }

    /**
     * A Kontroller elrontja a Pumpát.
     */
    @Override
    public void kontrollerElront() {
        setRossz(true);
    }

    /**
     * A szerelő megjavítja a pumpát.
     */
    @Override
    public void szereloJavit() {
        setRossz(false);
    }

    /**
     * Beállítja a pumpa állapotát
     * @param r A pumpa állapota.
     */
    public void setRossz(boolean r){
        rossz = r;
    }
}
