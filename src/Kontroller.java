import java.util.List;
import java.util.Random;

public  class Kontroller {
    /**
     * A játékban lévő csúcsok listája
     */
    private List<Csucs> csucsok;

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
    /**
     * hozzáad egy csúcsot a csúcsok listájához
     * @param csucs
     */
    public void setCsucsok(Csucs csucs){
        csucsok.add(csucs);
    }

    /**
     * Véletlenszerűen elront véletlenszerű számú pumpát
     */
    public void veletlenPumpaElrontas(){
        Random rand = new Random();
        for (Csucs csucs:csucsok
        ) {
            int breakNumber = rand.nextInt(25);
            if(breakNumber == 7)
                csucs.kontrollerElront();
        }
    }

    /**
     * megnöveli a paraméterben átadott csapat pontjait eggyel
     * @param csapat
     */
    public void pontNovel(String csapat){

    }

    /**
     * mindegyik csúcsban indít egy pumpálást
     */
    public void vizLeptet(){
        for (Csucs csucs:csucsok
        ) {
            csucs.vizetPumpal();
        }
    }

}
