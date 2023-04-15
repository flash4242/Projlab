import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        //ToDo Check if this works
        List<Csucs> csucsok = new ArrayList<>();
        csucsok.add(this);
        csucsok.add(this);
        cs.setSzomszedosCsucs(csucsok);
    }

    /**
     * Létrehoz egy új Pumpa objektumot amit visszaad.
     * @return A létrehozott Pumpa objektum.
     */
    @Override
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
