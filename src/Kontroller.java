import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public  class Kontroller {
    /**
     * A játékban lévő csúcsok listája
     */
    private List<Csucs> csucsok = new ArrayList<>();

    /**
     * A singleton referenciája
     */
    private static Kontroller single_instance = null;

    /**
     * A singletont lekérdező függvény
     * @return
     */
    public static synchronized Kontroller getInstance()
    {
        if (single_instance == null)
            single_instance = new Kontroller();

        return single_instance;
    }

    public void addCso(Cso cso){

    }
    public void addCsucs(Csucs csucs){

    }
    public void removeJatekos(Jatekos jatekos){

    }
    public void removeCso(Cso cso){

    }
    public void removeCsucs(Csucs csucs){

    }
    /**
     * hozzáad egy csúcsot a csúcsok listájához
     * @param csucs
     */
    public void setCsucsok(Csucs csucs){
        Skeleton.startMethod(getClass().getName(), "setCsucsok()");
        csucsok.add(csucs);
        Skeleton.endMethod();
    }
    public void reInitialize(){
        csucsok = new ArrayList<>();
    }

    /**
     * Véletlenszerűen elront véletlenszerű számú pumpát
     */
    public void veletlenPumpaElrontas(){
        Skeleton.startMethod(getClass().getName(), "veletlenPumpaElrontas()");
        Random rand = new Random();
        for (Csucs csucs:csucsok
        ) {
            if(Skeleton.kerdes("Elromoljon-e éppen a pumpa?"))
                csucs.kontrollerElront();
        }
        Skeleton.endMethod();
    }

    /**
     * megnöveli a paraméterben átadott csapat pontjait eggyel
     * @param csapat
     */
    public void pontNovel(String csapat){
        Skeleton.startMethod(getClass().getName(), "pontNovel()");
        Skeleton.endMethod();
    }

    /**
     * mindegyik csúcsban indít egy pumpálást
     */
    public void vizLeptet(){
        Skeleton.startMethod(getClass().getName(), "vizLeptet()");
        for (Csucs csucs:csucsok
        ) {
            csucs.vizetPumpal();
        }
        Skeleton.endMethod();
    }

}
