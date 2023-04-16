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

        getAktMezo().getNeighbours().get(hova).jatekosElfogad
        setAktMezo(getAktMezo().getNeighbours().get(hova));

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
