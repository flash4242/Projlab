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
        switch (input) {
            case 1 -> teszt1();
            case 2 -> teszt2();
            case 3 -> teszt3();
            case 4 -> teszt4();
            case 5 -> teszt5();
            case 6 -> teszt6();
            case 7 -> teszt7();
            case 8 -> teszt8();
            case 91 -> teszt91();
            case 92 -> teszt92();
            case 10 -> teszt10();
            case 11 -> teszt11();
            case 12 -> teszt12();
            case 13 -> teszt13();
            case 14 -> teszt14();
            case 15 -> teszt15();
            case 16 -> teszt16();
            case 17 -> teszt17();
            case 18 -> teszt18();
            case 19 -> teszt19();
            case 20 -> teszt20();
            default -> {
            }
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
        init = true;
        Szerelo sz = new Szerelo();
        Cso cs = new Cso();
        Pumpa p = new Pumpa();
        List<Cso> cso = new ArrayList<>();
        cso.add(cs);
        p.setSzomszedosCso(cso);
        cs.setSzomszedosCsucs(p);
        sz.setAktMezo(p);
        p.setJatekosRajta(sz);

        init = false;
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
        init = true;
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
        init = false;

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
        init = true;
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

        init = false;
        sz.csovegFelvetele(0);
    }

    /**
     * Teszt: Csővég felvétele ciszternáról
     * Létrehoz egy csövet, egy ciszternát és egy szerelőt, és beállítja a kapcsolatokat.
     * A cs szomszédos csúcsai: c és c
     * A ciszternán lévő játékos: sz
     * A sz aktuális mezője: c
     * A kapcsolatok felallítása után leteszteljük, hogy tudunk-e helyesen felvenni csővégét a ciszternáról.
     */
    void teszt5(){
        Cso cs = new Cso();
        Szerelo sz = new Szerelo();
        Ciszterna c = new Ciszterna();
        sz.setAktMezo(c);
        c.setJatekosRajta(sz);
        List<Cso> csok = new ArrayList<Cso>();
        csok.add(cs);
        c.setSzomszedosCso(csok);
        List<Csucs> csucsok = new ArrayList<Csucs>();
        csucsok.add(c);
        csucsok.add(c);
        cs.setSzomszedosCsucs(csucsok);

        sz.csovegFelvetele(1);
    }

    /**
     * Teszt: Csővég lerakása
     * Létrehoz egy csövet, egy pumpát és egy szerelőt, és beállítja a kapcsolatokat.
     * A cs szomszédos csúcsa: p
     * A pumpán lévő játékos: sz
     * A sz aktuális mezője: p
     * A kapcsolatok felallítása után leteszteljük, hogy tudunk-e helyesen letenni egy csővégét pumpára.
     */
    void teszt6(){
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

        sz.csovegetLerak();
    }

    /**
     * Teszt: Pumpa felvétele ciszternáról
     * Létrehoz egy pumpát, egy ciszternát és egy szerelőt, és beállítja a kapcsolatokat.
     * A p szomszédos csövei:
     * A ciszternán lévő játékos: sz
     * A sz aktuális mezője: c
     * A kapcsolatok felallítása után leteszteljük, hogy tudunk-e helyesen felvenni pumpát a ciszternáról.
     */
    void teszt7(){
        Szerelo sz = new Szerelo();
        Ciszterna c = new Ciszterna();
        sz.setAktMezo(c);
        c.setJatekosRajta(sz);

        sz.pumpaFelvetele();
    }


    /**
     * Teszt: Pumpa lerakása
     * Létrehoz egy csövet, egy pumpát és egy szerelőt, és beállítja a kapcsolatokat.
     * A cs szomszédos csúcsa: p és p
     * A sz aktuális mezője: p
     * A kapcsolatok felallítása után leteszteljük, hogy tudunk-e helyesen letenni egy pumpát csőre.
     */
    void teszt8(){
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


        sz.pumpatLerak();
    }

    /**
     * Teszt: Pumpa javítása
     * Létrehoz egy pumpát és egy szabotőrt, és beállítja a kapcsolatokat.
     * A pumpán lévő játékos: sz
     * A szabotőr aktuális mezője: p
     * A kapcsolatok felallítása után leteszteljük, hogy ki tudunk-e helyesen pumpát javítani.
     */
    void teszt91(){
        Pumpa p = new Pumpa();
        Szerelo sz = new Szerelo();
        sz.setAktMezo(p);
        p.setJatekosRajta(sz);

        sz.mezotJavit();
    }

    /**
     * Teszt: Cső javítása
     * Létrehoz egy csövet és egy szabotőrt, és beállítja a kapcsolatokat.
     * A csövön lévő játékos: sz
     * A szabotőr aktuális mezője: cs
     * A kapcsolatok felallítása után leteszteljük, hogy ki tud-e tudunk-e helyesen csövet javítani.
     */
    void teszt92(){
        Cso cs = new Cso();
        Szerelo sz = new Szerelo();
        sz.setAktMezo(cs);
        cs.setJatekosRajta(sz);

        sz.mezotJavit();
    }


    /**
     * Teszt: Cső kilyukasztása
     * Létrehoz egy csövet és egy szabotőrt, és beállítja a kapcsolatokat.
     * A csövön lévő játékos: sz
     * A szabotőr aktuális mezője: cs
     * A kapcsolatok felallítása után leteszteljük, hogy ki tud-e lyukasztani a szabotőr egy csövet.
     */
    void teszt10(){
        init = true;
        Cso cs = new Cso();
        Szabotor sz = new Szabotor();
        cs.setJatekosRajta(sz);
        sz.setAktMezo(cs);
        init = false;

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
        init = true;
        Cso cs = new Cso();
        Szerelo sz = new Szerelo();
        cs.setJatekosRajta(sz);
        sz.setAktMezo(cs);
        init = false;

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
        init = true;
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

        init = false;
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
        init = true;
        Szerelo sz = new Szerelo();
        Cso cs1 = new Cso();
        Cso cs2 = new Cso();
        sz.setAktMezo(cs1);
        sz.setCsoveg(cs2);
        cs1.setJatekosRajta(sz);

        init = false;
        sz.csovegetLerak();
    }

    /**
     * Teszt: Pumpa felvétele nem ciszternáról
     * Létrehoz egy szerelőt, meg egy csövet, és mindkettőben beállítja, hogy a szerelő a csövön áll
     * Meghívja a szerelő pumpafelvétel függvényét, tesztelve, hogy valóban nem tud-e felvenni pumpát
     * a szerelő
     */
    void teszt14(){
        init = true;
        Szerelo sz = new Szerelo();
        Cso cs = new Cso();
        sz.setAktMezo(cs);
        cs.setJatekosRajta(sz);

        init = false;
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
        init = true;
        Szerelo sz = new Szerelo();
        Pumpa aktmezo = new Pumpa();
        Pumpa p = new Pumpa();
        sz.setAktMezo(aktmezo);
        aktmezo.setJatekosRajta(sz);
        sz.setPumpa(p);

        init = false;
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
        init = true;
        Szabotor sz = new Szabotor();
        Pumpa p = new Pumpa();
        sz.setAktMezo(p);
        p.setJatekosRajta(sz);

        init = false;
        sz.csoKilyukasztasa();
    }

    /**
     * Teszt: Kontroller Csúcsot Elront
     * Létrehozz egy kontrollert (k), egy ciszternát (c), egy pumpát (c) és a kapcsolataikat.
     * Leteszteljük, hogy a kontroller általi pumpaelrontások során a ciszterna valóban nem romlik-e el soha és a pumpa elromlik-e véletlenszerűen
     */
    void teszt17(){
        init = true;
        Kontroller k = new Kontroller();
        Ciszterna c = new Ciszterna();
        Pumpa p = new Pumpa();
        k.setCsucsok(c);
        k.setCsucsok(p);

        init = false;
        k.veletlenPumpaElrontas();
    }

    /**
     * Teszt: Pumpa vizet pumpál
     * Létrehoz egy pumpát és két hozzá kapcsolódó csövet: cs1, cs2.
     * A pumpán beállítjuk a bemeneti és kimeneti csöveket, majd a kontroller meghívja a pumpa
     * vizetPumpal függvényét és leteszteljük, hogy helyesen működik-e a víztovábbítás.
     */
    void teszt18(){
        init = true;
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

        init = false;
        Kontroller.getInstance().setCsucsok(p);
        Kontroller.getInstance().vizLeptet();
    }

    /**
     * Teszt:
     */
    void teszt19(){
        //TODO. megcsinálni
    }

    /**
     * Teszt: Forrás vizet pumpál
     * Létrehoz egy forrást és két hozzá kapcsolódó csövet: cs1, cs2.
     * A kontroller meghívja a forrás vizetPumpal függvényét és leteszteljük,
     * hogy helyesen működik-e a víztovábbítás.
     */
    void teszt20(){
        init = true;
        Cso cs1 = new Cso();
        Cso cs2 = new Cso();
        Forras f = new Forras();
        List<Cso> csovek = new ArrayList<>();
        csovek.add(cs1);
        csovek.add(cs2);
        f.setSzomszedosCso(csovek);
        cs1.setSzomszedosCsucs(f);
        cs2.setSzomszedosCsucs(f);

        init = false;
        Kontroller.getInstance().setCsucsok(f);
        Kontroller.getInstance().vizLeptet();
    }
}
