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
        Skeleton.startMethod(getClass().getName(), "atallit()");
        bemenetiCso = bemeneti % getSzomszedosCso().size();
        kimenetiCso = kimeneti % getSzomszedosCso().size();
        Skeleton.endMethod();
    }

    /**
     * A pumpa a kimeneti csövébe vizet pumpál, majd a bemeneti csőből szivattyúz.
     */
    @Override
    public void vizetPumpal() {
        Skeleton.startMethod(getClass().getName(), "vizetPumpal()");
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
        Skeleton.endMethod();
    }

    /**
     * Szólunk a pumpának hogy szeretnénk a cs-edik csövét megkapni.
     * Ha a csúcsnak csak egy szomszédos csöve van és nincs rajta senki, akkor visszaadja és lecsatolja a csövet.
     * Különben null-t ad vissza.
     * @param cs A szomszédos csövek közül melyiket szeretnénk megkapni.
     * @return A pumpa által visszaadott cső, amennyiben volt ilyen, egyébként null.
     */
    @Override
    public Cso adjCsovet(int cs){
        Skeleton.startMethod(getClass().getName(), "adjCsovet()");
        if(Skeleton.kerdes("Több mint egy szomszédos csöve van a csúcsnak?") && Skeleton.kerdes("Nem áll senki a szomszédos csövön?")) {
            if (!Skeleton.kerdes("Bemeneti vagy kimeneti csove a pumpanak?")) {
                Cso temp = getSzomszedosCso().get(cs % getSzomszedosCso().size());
                lecsatol(temp);
                Skeleton.endMethod();
                return temp;
            }
        }
        Skeleton.endMethod();
        return null;
    }

    /**
     * A Kontroller elrontja a Pumpát.
     */
    @Override
    public void kontrollerElront() {
        Skeleton.startMethod(getClass().getName(), "kontrollerElront()");
        setRossz(true);
        Skeleton.endMethod();
    }

    /**
     * A szerelő megjavítja a pumpát.
     */
    @Override
    public void szereloJavit() {
        Skeleton.startMethod(getClass().getName(), "szereloJavit()");
        setRossz(false);
        Skeleton.endMethod();
    }

    /**
     * Beállítja a pumpa állapotát.
     * @param r A pumpa állapota.
     */
    public void setRossz(boolean r){
        Skeleton.startMethod(getClass().getName(), "setRossz()");
        rossz = r;
        Skeleton.endMethod();
    }

    /**
     * Beállítja a pumpa bemeneti csövét, az inicializálásban.
     * @param be bemeneti cső index a szomszédos csövekhez.
     */
    public void setBemenetiCso(int be){
        Skeleton.startMethod(getClass().getName(), "setBemenetiCso()");
        bemenetiCso = be;
        Skeleton.endMethod();
    }

    /**
     * Beállítja a pumpa kimeneti csövét, az inicializálásban.
     * @param ki kimeneti cső index a szomszédos csövekhez.
     */
    public void setKimenetiCso(int ki){
        Skeleton.startMethod(getClass().getName(), "setKimenetiCso()");
        kimenetiCso = ki;
        Skeleton.endMethod();
    }
}
