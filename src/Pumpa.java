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
    private boolean rossz;

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
        bemenetiCso = bemeneti % szomszedosCso.size();
        kimenetiCso = kimeneti % szomszedosCso.size();
    }

    /**
     * A pumpa a kimeneti csövébe vizet pumpál, majd a bemeneti csőből szivattyúz.
     */
    @Override
    public void vizetPumpal() {
        if(!rossz){
            if(vanViz){
                if(szomszedosCso.get(kimenetiCso).vizetKap()){
                    vanViz = szomszedosCso.get(bemenetiCso).vizetVeszit();
                }
            }
            else{
                vanViz = szomszedosCso.get(bemenetiCso).vizetVeszit();
            }
        }
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
        int k = cs % szomszedosCso.size();
        if(kimenetiCso != k && bemenetiCso != k) {
            Cso temp = szomszedosCso.get(k);
            if (temp.getJatekosRajta().size() == 0) {
                if(k<bemenetiCso) bemenetiCso--;
                if(k<kimenetiCso) kimenetiCso--;
                lecsatol(temp);
                return temp;
            }
        }
        return null;
    }

    /**
     * Kicseréli a cs1 csövet a cs2 csőre. Figyel arra, hogy ugyanoda rakja a cs2 csövet a listában, ahonnan a cs1-et kivette
     * @param cs1 A lecsatolandó cső.
     * @param cs2 A felcsatolandó cső.
     */
    @Override
    public void csoCsere(Cso cs1, Cso cs2){
        int hova = szomszedosCso.indexOf(cs1);
        lecsatol(cs1);
        szomszedosCso.add(hova, cs2);
        cs2.addCsucs(this);
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
     * Beállítja a pumpa állapotát.
     * @param r A pumpa állapota.
     */
    public void setRossz(boolean r){
        rossz = r;
    }
    public void setBemenetiCso(Cso cso){
        if(cso == null)
            bemenetiCso = -1;
        bemenetiCso = szomszedosCso.indexOf(cso);
    }
    public void setKimenetiCso(Cso cso){
        if(cso == null)
            kimenetiCso = -1;
        kimenetiCso = szomszedosCso.indexOf(cso);
    }
    /**
     * Visszaadja, hogy rossz-e a pumpa.
     * @return igaz, ha rossz a pumpa, hamis ha nem rossz.
     */
    public boolean getRossz(){
        return rossz;
    }

    /**
     * Beállítja a pumpa bemeneti csövét, az inicializálásban.
     * @param be bemeneti cső index a szomszédos csövekhez.
     */
    public void setBemenetiCso(int be){
        bemenetiCso = be;
    }

    /**
     * Visszaadja a pumpa bemeneti csövének indexét.
     * @return A bemeneti cső indexe.
     */
    public int getBemenetiCso(){
        return bemenetiCso;
    }

    /**
     * Visszaadja a pumpa kimeneti csövének indexét.
     * @return A kimeneti cső indexe.
     */
    public int getKimenetiCso(){
        return kimenetiCso;
    }

    /**
     * Beállítja a pumpa kimeneti csövét, az inicializálásban.
     * @param ki kimeneti cső index a szomszédos csövekhez.
     */
    public void setKimenetiCso(int ki){
        kimenetiCso = ki;
    }
}
