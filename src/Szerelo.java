/**
 * Szereli a pumpákat és csöveket, célja, hogy minél több víz jusson a ciszternákba, a szerelőt
 * reprezentálja a játékban.
 */
public class Szerelo extends Jatekos{
    /**
     * Szerekőnél lévő csővég
     */
    Cso csoveg;

    public Cso getCsoveg(){
        return csoveg;
    }

    public void setCsoveg(Cso cs) {
        csoveg = cs;
    }

    /**
     * Szerelőnél lévő pupma
     */
    Pumpa pumpa;

    public Szerelo(){
        csoveg=null;
        pumpa=null;
    }
    public Pumpa getPumpa(){
        return pumpa;
    }

    public void setPumpa(Pumpa p){
        pumpa=p;
    }

    /**
     * Megjavitja a mezőt, amin éppen áll
     */
    public void mezotJavit(){
        Skeleton.startMethod(getClass().getName(), "mezotJavit()");
        if(Skeleton.kerdes("Csövön vagy pumpán áll?")) {
            getAktMezo().szereloJavit();
        }
        Skeleton.endMethod();
    }

    /**
     *
     * @param cs
     */
    public void csovegFelvetele(int cs){
        Skeleton.startMethod(getClass().getName(), "csovegFelvetele()");
        if(cs>=0 && cs<getAktMezo().getNeighbours().size()
                && getAktMezo().getNeighbours().get(cs).jatekosRajta.isEmpty()
                && getAktMezo().getNeighbours().get(cs).setCsucsToNull(getAktMezo())
                && getAktMezo().getNeighbours().get(cs).setCsoToNull(getAktMezo().getNeighbours().get(cs))){
            setCsoveg((Cso) getAktMezo().getNeighbours().get(cs));
        }
        else{
            getAktMezo().targyLerakas((Cso) getAktMezo().getNeighbours().get(cs));
        }
        Skeleton.endMethod();
    }

    /**
     * Lerakja a nála lévő csövet
     */
    public void csovegetLerak(){
        Skeleton.startMethod(getClass().getName(), "csovegFelvetele()");
        if(Skeleton.kerdes("Van a szerelőnél cső?")){
            getAktMezo().targyLerakas(csoveg);
            csoveg=null;
        }
        Skeleton.endMethod();
    }

    /**
     * Felveszi a pumpát a mezőről, amin áll
     */
    public void pumpaFelvetele(){
        Skeleton.startMethod(getClass().getName(), "pumpaFelvetele()");
        if(!Skeleton.kerdes("Van a szerelőnél pumpa vagy csővég?")) {
            setPumpa(getAktMezo().pumpaLetrehozasa());
        }
        Skeleton.endMethod();
    }

    /**
     * Lerakja a nála lévő pumpát
     */
    public void pumpatLerak(){
        Skeleton.startMethod(getClass().getName(), "pumpaFelvetele()");
        if(Skeleton.kerdes("Van a szerelőnél pumpa?")){
            getAktMezo().targyLerakas(pumpa);
            pumpa=null;
        }
        Skeleton.endMethod();
    }

}