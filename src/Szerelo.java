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
        getAktMezo().szereloJavit();
    }

    /**
     *
     * @param cs
     */
    public void csovegFelvetele(int cs){
        if(pumpa==null && csoveg==null && getAktMezo().getNeighbours().get(cs).jatekosRajta.isEmpty()){
            if(getAktMezo().getNeighbours().get(cs).setCsucsToNull(getAktMezo())){
                setCsoveg((Cso) getAktMezo().getNeighbours().get(cs));
            }
        }
    }

    /**
     * Lerakja a nála lévő csövet
     */
    public void csovegetLerak(){
        if(csoveg!=null){
            getAktMezo().targyLerakas(csoveg);
            csoveg=null;
        }
    }

    /**
     * Felveszi a pumpát a mezőről, amin áll
     */
    public void pumpaFelvetele(){
        if(pumpa==null && csoveg==null) {
            pumpa=getAktMezo().pumpaLetrehozasa();
        }
    }

    /**
     * Lerakja a nála lévő pumpát
     */
    public void pumpatLerak(){
        if(pumpa!=null){
            getAktMezo().targyLerakas(pumpa);
            pumpa=null;
        }
    }

}
