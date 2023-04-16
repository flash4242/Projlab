/**
 * A játékban lévő pumpákat reprezentáló osztály.
 * Aktív elem, ami a kimeneti csövén vizet továbbít, bemeneti csövén vizet szivattyúz, valamint vizet tárol.
 */
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
     * @param kimeneti bemeneti cső index a szomszédos csövekhez
     * @param bemeneti kimeneti cső index a szomszédos csövekhez
     */
    @Override
    public void atallit(int kimeneti, int bemeneti) {
        bemenetiCso = bemeneti % getSzomszedosCso().size();
        kimenetiCso = kimeneti % getSzomszedosCso().size();
    }

    /**
     * A pumpa a kimeneti csövébe vizet pumpál, majd a bemeneti csőből szivattyúz.
     */
    @Override
    public void vizetPumpal() {
        if(!Skeleton.kerdes("El van romolva a pumpa?")){
            if(Skeleton.kerdes("Van viz a pumpaban?")){
                if(getSzomszedosCso().get(kimenetiCso).vizetKap()){
                    getSzomszedosCso().get(bemenetiCso).vizetVeszit();
                }
            }
            else{
                getSzomszedosCso().get(bemenetiCso).vizetVeszit();
            }
        }
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
     * A paraméterként kapott Mezőt eltávolítja a szomszedosCso listából, amennyiben ez nem az utolsó cső ami a csúcshoz van kötve.
     * @param m A paraméterként kapott Mező, amit eltávolítunk a listából.
     * @return Igazat ad vissza, ha eltávolítottuk a listából, egyébként hamisat.
     */
    @Override
    public boolean setCsoToNull(Mezo m){
        getSzomszedosCso().remove(m);
        if(!Skeleton.kerdes("Ez az utolso cso ami a csucshoz hozza van kotve?")) {
            if(!Skeleton.kerdes("Ez a cso bemeneti vagy kimeneti csove a pumpanak?")){
                return true;
            }
        }
        return false;
    }

    /**
     * Beállítja a pumpa állapotát.
     * @param r A pumpa állapota.
     */
    public void setRossz(boolean r){
        rossz = r;
    }

    /**
     * Beállítja a pumpa bemeneti csövét, az inicializálásban.
     * @param be bemeneti cső index a szomszédos csövekhez.
     */
    public void setBemenetiCso(int be){
        bemenetiCso = be;
    }

    /**
     * Beállítja a pumpa kimeneti csövét, az inicializálásban.
     * @param ki kimeneti cső index a szomszédos csövekhez.
     */
    public void setKimenetiCso(int ki){
        kimenetiCso = ki;
    }
}
