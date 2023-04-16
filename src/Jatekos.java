import java.util.List;
/**
 * A szerelő és szabotőr közös tulajdonságaiért és
 * viselkedéséért felelős absztrakt osztály.
 */
public abstract class Jatekos {
    /**
     * A játékos aktuális mezője
     */
    private Mezo atkmezo;

    public void setAktMezo(Mezo m){
        atkmezo=m;
    }

    public Mezo getAktMezo(){
        return atkmezo;
    }

    /**
     * Egy mezőről egy másik mezőre lép át
     * @param hova hova lép át
     */
    public void mozgas(int hova){
        Skeleton.startMethod(getClass().getName(), "mozgas()");
        List<? extends Mezo> szomszedok =getAktMezo().getNeighbours();
        if(hova<szomszedok.size()){
            Mezo hovamezo =szomszedok.get(hova);
            if(hovamezo.jatekostElfogad(this)) {
                getAktMezo().jatekostEltavolit(this);
                setAktMezo(hovamezo);
            }
        }


        Skeleton.endMethod();
    }

    /**
     * Egy pumpát úgy állít át, hogy
     * megadja melyik csőcsből melyik csőbe folyjon a víz
     * @param bemeneti bemeneti cső
     * @param kimeneti kimeneti cső
     */
    public void pumpaAtallitasa(int bemeneti, int kimeneti){
        Skeleton.startMethod(getClass().getName(), "pumpaAtallitasa()");
        getAktMezo().atallit(bemeneti, kimeneti);
        Skeleton.endMethod();
    }
}
