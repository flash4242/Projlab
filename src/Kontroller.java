import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public  class Kontroller {
    private int szereloPontok;
    private int szabotorPontok;
    private int aktualisKor;
    private int szerelokSzama;
    private int szabotorokSzama;
    private int aktualisJatekos;
    private List<Csucs> csucsok = new ArrayList<>();
    private List<Cso> csovek = new ArrayList<>();
    private List<Jatekos> jatekosok = new ArrayList<>();


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
    public int getSzereloPontok(){return szereloPontok;}
    public int getSzabotorPontok(){return szabotorPontok;}
    public int getAktualisKor(){return aktualisKor;}
    public int getSzerelokSzama(){return szerelokSzama;}
    public int getSzabotorokSzama(){return szabotorokSzama;}
    public Jatekos getAktualisJatekos(){return jatekosok.get(aktualisJatekos);}
    public List<Csucs> getCsucsok(){return csucsok;}
    public List<Cso> getCsovek(){return csovek;}
    public List<Jatekos> getJatekosok(){return jatekosok;}




    /**
     * Mindegyik csúcsban indít egy pumpálást
     */
    public void vizLeptet(){
        for (Csucs csucs:csucsok)
            csucs.vizetPumpal();
    }
    public void initJatek(){
        initPalya();
        initJatekosok();
    }
    public void initPalya(){

    }
    public void initJatekosok(){

    }
    /**
     * megnöveli a paraméterben átadott csapat pontjait eggyel
     * @param csapat
     */
    public void pontNovel(String csapat){
        switch (csapat) {
            case "szerelok" -> szereloPontok++;
            case "szabotorok" -> szabotorPontok++;
        }
    }
    /**
     * Véletlenszerűen elront véletlenszerű számú pumpát
     */
    public void veletlenPumpaElrontas(){
        Skeleton.startMethod(getClass().getName(), "veletlenPumpaElrontas()");
        Random rand = new Random();
        for (Csucs csucs:csucsok){
            if(Skeleton.kerdes("Elromoljon-e éppen a pumpa?"))
                csucs.kontrollerElront();
        }
        Skeleton.endMethod();
    }

    public void stepTime(){
        for(Cso cs: csovek){
            cs.stepTime();
        }
    }

    public void addCso(Cso cso){
        csovek.add(cso);
    }

    /**
     * hozzáad egy csúcsot a csúcsok listájához
     * @param csucs
     */
    public void addCsucs(Csucs csucs){
        csucsok.add(csucs);
    }
    public void removeJatekos(Jatekos jatekos){
        for(int i=0; i<jatekosok.size(); i++){
            if(jatekosok.get(i).equals(jatekos)) {
                jatekosok.remove(i);
                break;
            }
        }
    }
    public void removeCso(Cso cso){
        for(int i=0; i<csovek.size(); i++){
            if(csovek.get(i).equals(cso)) {
                csovek.remove(i);
                break;
            }
        }
    }
    public void removeCsucs(Csucs csucs){
        for(int i=0; i<csucsok.size(); i++){
            if(csucsok.get(i).equals(csucs)) {
                csucsok.remove(i);
                break;
            }
        }
    }


    public void reset(){
        csucsok.clear();
        csovek.clear();
        jatekosok.clear();
        aktualisKor=0;
        szerelokSzama=0;
        szabotorokSzama=0;
        szereloPontok=0;
        szabotorPontok=0;
    }
}
