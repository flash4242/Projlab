import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Skeleton {
    private static int indent = 0;
    private static boolean init;
    public static void startMethod(String class_, String method){
        if(!init) {
            ++indent;
            for (int i = 0; i < indent; ++i)
                System.out.print("\t");
            System.out.println(class_ + "." + method);
        }
    }
    public static void endMethod(){
        if(!init)
            --indent;
    }
    static boolean kerdes(String szoveg){ //TODO Megvaltoztattam staticra ez igy oki?
        System.out.println(szoveg);
        Scanner scan = new Scanner(System.in);
        int input;
        input = scan.nextInt();
        scan.close(); //TODO Bezartam a scannert.
        return input==0 ? false : true;
    }
    void tesztesetValaszto(int input){
        switch(input){
            case 1: teszt1(); break;
            default: break;
        }
    }
    void teszt0(){
        System.out.println("\t"+"1-es teszteset\n");
    }

    //TODO rename
    void teszt1(){
        init = true;
        Szerelo sz = new Szerelo();
        Cso cs = new Cso();
        Pumpa p = new Pumpa();
        List<Cso> cso = new ArrayList<>();
        cso.add(cs);
        p.setSzomszedosCso(cso);
        cs.setSzomszedosCsucs(p);
        sz.setAktMezo(cs);
        cs.setJatekosRajta(sz);

        init = false;
        sz.mozgas(0);
    }

    void teszt2(){
        Szerelo sz = new Szerelo();
        Cso cs = new Cso();
        Pumpa p = new Pumpa();
        List<Cso> cso = new ArrayList<>();
        cso.add(cs);
        p.setSzomszedosCso(cso);
        cs.setSzomszedosCsucs(p);
        sz.setAktMezo(p);
        p.setJatekosRajta(sz);

        sz.mozgas(2);
    }

    /**
     * Teszt: Pumpa átállítása
     * Létrehoz egy pumpát, 3 csövet és egy szerelőt, és beállítja a kapcsolatokat.
     * A pumpa szomszédos csövei: cs0, cs1, cs2
     * A cs0,cs1,cs2 szomszédos csúcsa: p1
     * A szerelő aktuális mezője: p1
     * A p1-en lévő játékos: sz
     * A kapcsolatok felallítása után leteszteljük, hogy ét tudjuk-e helyesen állítani a pumpát.
     * Először cs0-ból cs1-be pumpálunk, majd cs2-ből cs2-be.
     */
    void teszt3(){
        Pumpa p1 = new Pumpa();
        Cso cs0 = new Cso();
        Cso cs1 = new Cso();
        Cso cs2 = new Cso();
        List<Cso> csok = new ArrayList<Cso>();
        csok.add(cs0);
        csok.add(cs1);
        csok.add(cs2);
        p1.setSzomszedosCso(csok);
        List<Csucs> csucsok = new ArrayList<Csucs>();
        csucsok.add(p1);
        cs0.setSzomszedosCsucs(csucsok);
        cs1.setSzomszedosCsucs(csucsok);
        cs2.setSzomszedosCsucs(csucsok);
        Szerelo sz = new Szerelo();
        sz.setAktMezo(p1);
        p1.setJatekosRajta(sz);

        sz.pumpaAtallitasa(0,1);
        sz.pumpaAtallitasa(2,2);
    }

    /**
     * Teszt: Csővég felvétele pumpáról
     * Létrehoz egy csövet, egy pumpát és egy szerelőt, és beállítja a kapcsolatokat.
     * A pumpa szomszédos csövei: cs
     * A cs szomszédos csúcsai: p és p
     * A pumpán lévő játékos: sz
     * A sz aktuális mezője: p
     * A kapcsolatok felallítása után leteszteljük, hogy tudunk-e helyesen felvenni a csővégét a pumpáról.
     */
    void teszt4(){
        Cso cs = new Cso();
        Szerelo sz = new Szerelo();
        Pumpa p = new Pumpa();
        sz.setAktMezo(p);
        p.setJatekosRajta(sz);
        List<Cso> csok = new ArrayList<Cso>();
        csok.add(cs);
        p.setSzomszedosCso(csok);
        List<Csucs> csucsok = new ArrayList<Csucs>();
        csucsok.add(p);
        csucsok.add(p);
        cs.setSzomszedosCsucs(csucsok);

        sz.csovegFelvetele(0);
    }

    /**
     * Teszt: Cső kilyukasztása
     * Létrehoz egy csövet és egy szabotőrt, és beállítja a kapcsolatokat.
     * A csövön lévő játékos: sz
     * A szabotőr aktuális mezője: cs
     * A kapcsolatok felallítása után leteszteljük, hogy ki tud-e lyukasztani a szabotőr egy csövet.
     */
    void teszt10(){
        Cso cs = new Cso();
        Szabotor sz = new Szabotor();
        cs.setJatekosRajta(sz);
        sz.setAktMezo(cs);

        sz.csoKilyukasztasa();
    }

    /**
     * Teszt: Cső átállítása
     * Létrehoz egy csövet és egy szerelőt, és beállítja a kapcsolatokat.
     * A csövön lévő játékos: sz
     * A szerelő aktuális mezője: cs
     * A kapcsolatok felallítása után leteszteljük, hogy helyesen nem történik-e semmi amikor egy csövet át akarunk állítani.
     */
    void teszt11(){
        Cso cs = new Cso();
        Szerelo sz = new Szerelo();
        cs.setJatekosRajta(sz);
        sz.setAktMezo(cs);

        sz.pumpaAtallitasa(0,1);
    }

    /**
     * Teszt: Csővég felvétele csőről
     * Létrehoz egy csövet, egy pumpát és egy szerelőt, és beállítja a kapcsolatokat.
     * A csövön lévő játékos: sz
     * A szerelő aktuális mezője: cs
     * A cső szomszédos csúcsa: p
     * A pumpa szomszédos csöve: cs
     * A kapcsolatok felallítása után leteszteljük, hogy ha egy csövön állva szeretnénk felvenni egy csővéget, akkor helyesen nem történik-e semmi.
     */
    void teszt12(){
        Szerelo sz = new Szerelo();
        Cso cs = new Cso();
        Pumpa p = new Pumpa();
        sz.setAktMezo(cs);
        cs.setJatekosRajta(sz);
        List<Csucs> csucsok = new ArrayList<Csucs>();
        csucsok.add(p);
        cs.setSzomszedosCsucs(csucsok);
        List<Cso> csok = new ArrayList<Cso>();
        csok.add(cs);
        p.setSzomszedosCso(csok);

        sz.csovegFelvetele(0);
    }

    /**
     * Teszt: Pumpa felvétele nem ciszternáról
     * Létrehoz egy szerelőt, meg egy csövet, és mindkettőben beállítja, hogy a szerelő a csövön áll
     * Meghívja a szerelő pumpafelvétel függvényét, tesztelve, hogy valóban nem tud-e felvenni pumpát
     * a szerelő
     */
    void teszt14(){
        Szerelo sz = new Szerelo();
        Cso cs = new Cso();
        sz.setAktMezo(cs);
        cs.setJatekosRajta(sz);

        sz.pumpaFelvetele();
    }

    /**
     * Teszt: Pumpa lerakása pumpára
     * Létrehoz egy szerelőt, illetve két pumpát, beállítja rajtuk, hogy
     * a szerelő az egyik pumpán áll, és a másikat hordozza
     * Ezután meghívja a szerelő pumpaLerak() függvényét, tesztelve, hogy valóban nem
     * történik-e meg a lerakás
     */
    void teszt15(){
        Szerelo sz = new Szerelo();
        Pumpa aktmezo = new Pumpa();
        Pumpa p = new Pumpa();
        sz.setAktMezo(aktmezo);
        aktmezo.setJatekosRajta(sz);
        sz.setPumpa(p);

        sz.pumpatLerak();
    }

    void teszt18(){
        Cso cs1 = new Cso();
        Cso cs2 = new Cso();
        Pumpa p = new Pumpa();
        List<Cso> csovek = new ArrayList<>();
        csovek.add(cs1);
        csovek.add(cs2);
        p.setSzomszedosCso(csovek);
        cs1.setSzomszedosCsucs(p);
        cs2.setSzomszedosCsucs(p);
        p.setBemenetiCso(0);
        p.setKimenetiCso(1);

        //TODO ide kell kontroller ami meghívja a pumpán a továbbítot?
        p.vizetPumpal();
    }

    void teszt20(){
        Cso cs1 = new Cso();
        Cso cs2 = new Cso();
        Forras f = new Forras();
        List<Cso> csovek = new ArrayList<>();
        csovek.add(cs1);
        csovek.add(cs2);
        f.setSzomszedosCso(csovek);
        cs1.setSzomszedosCsucs(f);
        cs2.setSzomszedosCsucs(f);

        //TODO teszt18-hoz hasonlóan.
        f.vizetPumpal();
    }
}
