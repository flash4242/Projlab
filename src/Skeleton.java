import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Skeleton {
    private static int indent=0;
    private static boolean init;
    static void startMethod(Object object, String method){
        if(!init) {
            ++indent;
            for (int i = 0; i < indent; ++i)
                System.out.print("\t");
            System.out.println(object.getClass().getName() + "." + method);
        }
    }
    static void endMethod(){
        if(!init)
            --indent;
    }
    static boolean kerdes(String szoveg){
        System.out.println(szoveg+"\n0: nem  1: igen");
        Scanner scan = new Scanner(System.in);
        int input;
        input = scan.nextInt();
        scan.close();
        return input==0 ? false : true;
    }
    void tesztesetValaszto(int input){
        switch(input){
            case 1: teszt(); break;
            case 2: teszt2(); break;
            case 3: teszt3(); break;
            case 4: teszt4(); break;
            //case 5: teszt5(); break;
            //case 6: teszt6(); break;
            //case 7: teszt7(); break;
            //case 8: teszt8(); break;
            //case 9: teszt9(); break;
            case 10: teszt10(); break;
            case 11: teszt11(); break;
            case 12: teszt12(); break;
            case 13: teszt13(); break;
            case 14: teszt14(); break;
            case 15: teszt15(); break;
            case 16: teszt16(); break;
            case 17: teszt17(); break;
            case 18: teszt18(); break;
            //case 19: teszt19(); break;
            case 20: teszt20(); break;
            default: break;
        }
    }

    /**
     * Teszt: Pumpára mozgás
     * Létrehoz egy pumpát, egy csövet és egy szerelőt és beállítja a kapcsolatokat.
     * A pumpa szomszédos csöve: cs
     * A cs szomszédos csúcsa: p
     * cs-n álló játékos: sz
     * A kapcsolatok felállítása után teszteljük, hogy helyesen mozog-e a szerelő a pumpára.
     */
    void teszt1(){
        Szerelo sz = new Szerelo();
        Cso cs = new Cso();
        Pumpa p = new Pumpa();
        List<Cso> cso = new ArrayList<>();
        cso.add(cs);
        p.setSzomszedosCso(cso);
        cs.setSzomszedosCsucs(p);
        sz.setAktMezo(cs);
        cs.setJatekosRajta(sz);

        sz.mozgas(2);
    }

    /**
     * Teszt: Csőre mozgás
     * Létrehoz egy pumpát, egy csövet és egy szerelőt és beállítja a kapcsolatokat.
     * A pumpa szomszédos csöve: cs
     * A cs szomszédos csúcsa: p
     * p-n álló játékos: sz
     * A kapcsolatok felállítása után teszteljük, hogy helyesen mozog-e a szerelő a csőre.
     */
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
     * Teszt: Csővég Lerakása Csőre
     * Létrehoz egy szerelőt, két csövet, beállítja a kapcsolatokat.
     * A szerelő, aki lerakja a kezében lévő csövet: sz
     * A szerelő aktuális mezője: cs1
     * A szerelő kezében lévő cső: cs2
     * A kapcsolatok felallítása után leteszteljük, hogy, ha egy csövön állva szeretnénk letenni egy csővéget, akkor helyesen nem történik-e semmi.
     */
    void teszt13(){
        Szerelo sz = new Szerelo();
        Cso cs1 = new Cso();
        Cso cs2 = new Cso();
        sz.setAktMezo(cs1);
        sz.setCsoveg(cs2);
        cs1.setJatekosRajta(sz);

        sz.csovegetLerak();
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


    /**
     * Teszt: Pumpa Kilyukasztása
     * Létrehoz egy szabotőrt, egy pumpát és a kapcsolatokat.
     * A szabotőr aki pumpát akar lyukasztani: sz
     * A lyukasztani kívánt pumpa: p
     * Az sz szabotőr a p pumpán áll
     * A kapcsolatok felallítása után leteszteljük, hogy, ha egy pumpán állva szeretnénk szabotőrként azt kilyukasztani, akkor helyesen nem történik-e semmi.
     */
    void teszt16(){
        Szabotor sz = new Szabotor();
        Pumpa p = new Pumpa();
        sz.setAktMezo(p);
        p.setJatekosRajta(sz);

        sz.csoKilyukasztasa();
    }

    /**
     * Teszt: Kontroller Csúcsot Elront
     * Létrehozz egy kontrollert (k), egy ciszternát (c), egy pumpát (c) és a kapcsolataikat.
     * Leteszteljük, hogy a kontroller általi pumpaelrontások során a ciszterna valóban nem romlik-e el soha és a pumpa elromlik-e véletlenszerűen
     */
    void teszt17(){
        Kontroller k = new Kontroller();
        Ciszterna c = new Ciszterna();
        Pumpa p = new Pumpa();
        k.setCsucsok(c);
        k.setCsucsok(p);

        k.veletlenPumpaElrontas();
    }

    /**
     * Teszt: Pumpa vizet pumpál
     * Létrehoz egy pumpát és két hozzá kapcsolódó csövet: cs1, cs2.
     * A pumpán beállítjuk a bemeneti és kimeneti csöveket, majd a kontroller meghívja a pumpa
     * vizetPumpal függvényét és leteszteljük, hogy helyesen működik-e a víztovábbítás.
     */
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

        Kontroller.getInstance().setCsucsok(p);
        Kontroller.getInstance().vizLeptet();
    }

    /**
     * Teszt: Forrás vizet pumpál
     * Létrehoz egy forrást és két hozzá kapcsolódó csövet: cs1, cs2.
     * A kontroller meghívja a forrás vizetPumpal függvényét és leteszteljük,
     * hogy helyesen működik-e a víztovábbítás.
     */
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

        Kontroller.getInstance().setCsucsok(f);
        Kontroller.getInstance().vizLeptet();
    }
}
