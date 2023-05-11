import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A játék menetét kontrollálja, számolja a két csapat pontjait, számolja a köröket, az akciók után áramoltatja a vizet.
 */
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

    /**
     * Getterek
     * @return
     */
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
     * Setterek
     * @param p
     * @return
     */
    public void setSzereloPontok(int p){szereloPontok=p;}
    public void setSzabotorPontok(int p){szabotorPontok=p;}
    public void setAktJatekos(int j){aktualisJatekos=j;}
    public void setSzerelokSzama(int sz){szerelokSzama=sz;}
    public void setSzabotorokSzama(int sz){szabotorokSzama=sz;}
    public void setAktKor(int k){aktualisKor=k;}



    /**
     * Mindegyik csúcsban indít egy pumpálást
     */
    public void vizLeptet(){
        for (Csucs csucs:csucsok)
            csucs.vizetPumpal();
    }

    /**
     * Létrehozza a játék elemeit, felépítését. Hívja az initPalya(), majd az initJatekosok(int, int) függvényt.
     */
    public void initJatek(){
        initPalya();
        initJatekosok(szerelokSzama, szabotorokSzama);
    }

    /**
     * A játék pályáját hozza létre: a ciszternákat, forrásokat, csöveket, pumpákat és az ezek közötti kapcsolatokat is beállítja.
     */
    public void initPalya(){
        int forrasokszama=6;
        int pumpakszama = 25;
        int ciszternakszama = forrasokszama;

        //források létreozása csövekkel: minden forráshoz egyből kapcsolunk egy csövet is; pontosan annyi cső jön létre mint forrás
        for(int i=0; i<forrasokszama; i++){
            Forras f = new Forras();
            Cso cs = new Cso();
            f.felcsatol(cs);
            cs.addCsucs(f);
            csucsok.add(f);
            csovek.add(cs);
        }

        //pumpák létrehozása, köztük csövekkel
        for(int i=0; i<pumpakszama; i++){
            Pumpa p = new Pumpa();
            //először a forrásokból kilógó csövek lekötése
            if(i<forrasokszama){
                //forrasbol kijövő csövek felcsatolása
                csovek.get(i).addCsucs(p);
                p.felcsatol(csovek.get(i));

                //ezekbe a pumpákba új csövek (amik a fentebbiekbe csatlakoznak majd), mindbe 2
                Cso cs1=new Cso(), cs2= new Cso();
                cs1.addCsucs(p);
                cs2.addCsucs(p);
                p.felcsatol(cs1);
                p.felcsatol(cs2);
                csovek.add(cs1);
                csovek.add(cs2);
            }
            //ha már nincs forrásból lógó cső, akkor jöhetnek az éppen létrejött pumpákhoz új csövek és a forrásokhoz csatlakozó pumpákból a szabad végek lerögzítése
            else{
                Cso cs = new Cso();
                cs.addCsucs(p);
                p.felcsatol(cs);
                csovek.add(cs);

                //itt a lógó csövek rögzítése. Minden pumpához csak 1 lógó csővéget, hogy a többinek is maradjon, ezért kell a break.
                for(Cso csoo: csovek){
                    if(csoo.getNeighbours().size()==1) {
                        csoo.addCsucs(p);
                        p.felcsatol(csoo);
                        break;
                    }
                }
            }
            csucsok.add(p);
        }

        //végül ciszternák létrehozása
        for(int i=0; i<ciszternakszama; i++){
            Ciszterna c = new Ciszterna();
            csovek.get(forrasokszama+i).addCsucs(c);
            c.felcsatol(csovek.get(forrasokszama+i));
            csucsok.add(c);
        }
    }

    /**
     * Játékosokat hoz létre és elhelyezi őket a megfelelő forrásokon
     * @param szerelokSz szerelők száma
     * @param szabotorokSz szabotőrök száma
     */
    public void initJatekosok(int szerelokSz, int szabotorokSz){
        szerelokSzama=szerelokSz;
        szabotorokSzama=szabotorokSz;
        int forrasokszama=6;

        for(int i=0; i<szabotorokSz; i++){
            Jatekos szabotor= new Szerelo();
            jatekosok.add(szabotor);
        }
        for(int i=0; i<szerelokSz; i++){
            Jatekos szerelo= new Szerelo();
            jatekosok.add(szerelo);
        }

        for(int i=0; i<jatekosok.size(); i++) {
            csucsok.get(i % forrasokszama).setJatekosRajta(jatekosok.get(i));
            jatekosok.get(i).setAktMezo(csucsok.get(i % forrasokszama));
        }
    }

    /**
     * Megnöveli a paraméterben átadott csapat pontjait eggyel
     * @param csapat
     */
    public void pontNovel(String csapat){
        switch (csapat) {
            case "szerelok" -> szereloPontok++;
            case "szabotorok" -> szabotorPontok++;
        }
    }

    /**
     * Véletlenszerűen elront pumpákat. Választ egy véletlen számot a csucsok tömb elemszámának 20%-a és 50%-a között
     * és ennyiszer elront egy véletlenszerűen választott (azaz egy indexet választ véletlenszerűen a tömb elemszámának minimuma és maximuma között) pumpát.
     */
    public void veletlenPumpaElrontas(){
        Random r = new Random();
        int alsohatar = (int)(csucsok.size()*0.2);
        int felsohatar = (int)(csucsok.size()*0.5);
        int mennyit_rontsak_el = r.nextInt(felsohatar-alsohatar) + alsohatar;

        while(mennyit_rontsak_el>0) {
            int melyiket = r.nextInt(csucsok.size()-1);
            csucsok.get(melyiket).kontrollerElront();
            mennyit_rontsak_el--;
        }
    }

    /**
     * A csövek normálissá  változásáig hátralévő időt csökkenti.
     * Minden, a csovek listában lévő, csövön meghívja az adott cső stepTime() függvényét.
     */
    public void stepTime(){
        for(Cso cs: csovek){
            cs.stepTime();
        }
    }

    /**
     * Hozzáad egy csövet a csovek listához. A paramétert berakja a lista végére.
     * @param cso
     */
    public void addCso(Cso cso){
        csovek.add(cso);
    }

    /**
     * Hozzáad egy csúcsot a csucsok listájához. A paramétert berakja a lista végére.
     * @param csucs
     */
    public void addCsucs(Csucs csucs){
        csucsok.add(csucs);
    }

    /**
     * Eltávolítja a paraméterként kapott Jatekos-t a jatekosok listából.
     * @param jatekos
     */
    public void removeJatekos(Jatekos jatekos){
        for(int i=0; i<jatekosok.size(); i++){
            if(jatekosok.get(i).equals(jatekos)) {
                jatekosok.remove(i);
                break;
            }
        }
    }

    /**
     * Eltávolítja a paraméterként kapott Cso-t a csovek listából.
     * @param cso
     */
    public void removeCso(Cso cso){
        for(int i=0; i<csovek.size(); i++){
            if(csovek.get(i).equals(cso)) {
                csovek.remove(i);
                break;
            }
        }
    }

    /**
     * Eltávolítja a paraméterként kapott Csucs-ot a csucsok listából.
     * @param csucs
     */
    public void removeCsucs(Csucs csucs){
        for(int i=0; i<csucsok.size(); i++){
            if(csucsok.get(i).equals(csucs)) {
                csucsok.remove(i);
                break;
            }
        }
    }


    /**
     * Kiüríti a csúcsok, csövek és játékosok listáját, visszaállítja a kört, a pontokat és a játékosok számát 0-ra.
     */
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
