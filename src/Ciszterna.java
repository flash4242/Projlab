/**
 * A játékban lévő Ciszternákat reprezentáló osztály.
 * Egy Ciszterna egy olyan Csúcs, amelynek mőködése során az összes szomszédos csőből vizet szív be.
 * Ez a víz egyből el is tűnik. És a szerelők pontot kapnak.
 */
public class Ciszterna extends Csucs{
    /**
     * Létrehoz egy Ciszterna objektumot.
     */
    public Ciszterna(){
        super();
    }

    /**
     * Létrehoz egy új Cso objektumot, és beállítja magát a cső mindkét végpontjának.
     */
    public void csoLetrehozasa(){
        Cso cs = new Cso();
        //ToDo Itt sem tudom hogyan mukodik, ezt meg lehet valtoztatni kell
        cs.setSzomszedosCsucs(this, this);
    }

    /**
     * Létrehoz egy új Pumpa objektumot amit visszaad.
     * @return A létrehozott Pumpa objektum.
     */
    public Pumpa pumpaLetrehozasa(){
        return new Pumpa();
    }

    /**
     * A ciszterna minden szomszédos csőből vizet szív be.
     * Ha sikerült vizet szívni, akkor a szerelők pontot kapnak.
     */
    @Override
    public void vizetPumpal() {
        for (Cso c : getSzomszedosCso()){
            boolean teli = c.vizetVeszit();
            if (teli){
                Kontroller.getInstance().pontNovel("szerelo");
            }
        }
    }
}
