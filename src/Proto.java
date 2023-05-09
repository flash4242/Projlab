import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Proto {
    File inputFile;
    File outputFile;
    List<String[]> parancsok;
    Kontroller kontroller;
    Map<String, Jatekos> jatekosIds;
    Map<String, Szerelo> szereloIds;
    Map<String, Szabotor> szabotorIds;
    Map<String, Mezo> mezoIds;
    Map<String, Cso> csoIds;
    Map<String, Csucs> csucsIds;

    public Proto(String in, String out){
        inputFile = new File(in);
        outputFile = new File(out);
        kontroller = Kontroller.getInstance();
        szereloIds = new HashMap<>();
        szabotorIds = new HashMap<>();
        csoIds = new HashMap<>();
        csucsIds = new HashMap<>();
        try {
            outputFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void beolvas() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        parancsok = new ArrayList<>();
        while(scanner.hasNext()){
            String akt = scanner.next();
            if(!akt.startsWith("#")) {
                parancsok.add(akt.split(" "));
            }
        }
        scanner.close();
    }

    void kiir(String szoveg){
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(outputFile, true);
            fileWriter.write(szoveg);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    boolean checkIdentifier(String id){
        boolean helyes = mezoIds.containsKey(id)||jatekosIds.containsKey(id);
        if(!helyes)
            kiir("hibas_azonosito");
        return helyes;
    }
    boolean checkIdentifier(String id, String tipus){
        boolean helyes = false;
        if(tipus.equals("mezo"))
            helyes= mezoIds.containsKey(id);
        if(tipus.equals("jatekos"))
            helyes= jatekosIds.containsKey(id);
        if(tipus.equals("cso"))
            helyes= csoIds.containsKey(id);
        if(tipus.equals("csucs"))
            helyes= csucsIds.containsKey(id);
        if(tipus.equals("szerelo"))
            helyes = szereloIds.containsKey(id);
        if(tipus.equals("szabotor"))
            helyes = szabotorIds.containsKey(id);
        if(!helyes)
            kiir("hibas_azonosito");
        return helyes;
    }
    boolean checkParamCount(int elvart, String[] akt, boolean legalabb){
        boolean helyes;
        if(legalabb)
            helyes = elvart <= akt.length-1;
        else
            helyes = elvart == akt.length-1;
        if(!helyes)
            kiir("hibas_parameter");
        return helyes;
    }
    boolean checkParamCount(int elvart, String[] akt){
        boolean helyes;
        helyes = elvart == akt.length-1;
        if(!helyes)
            kiir("hibas_parameter");
        return helyes;
    }

    void vegrehajt(){
        for (String[] parancs:parancsok
        ) {
            switch (parancs[0]){
                case "TesztVege":
                    TesztVege(parancs);
                    break;
                case "AllapotAllit":
                    AllapotAllit(parancs);
                    break;
                case "PumpaFelvetel":
                    PumpaFelvetel(parancs);
                    break;
                case "TargyLerakasa":
                    TargyLerakasa(parancs);
                    break;
                case "PumpaJavitasa":
                    PumpaJavitasa(parancs);
                    break;
                case "MezoHozzaadasa":
                    MezoHozzaadasa(parancs);
                    break;
                case "JatekosHozzaadasa":
                    JatekosHozzaadasa(parancs);
                    break;
                case "JatekosEltavolitasa":
                    JatekosEltavolitasa(parancs);
                    break;
                case "MezokOsszekapcsolasa":
                    MezokOsszekapcsolasa(parancs);
                    break;
                case "MezokSzetkapcsolasa":
                    MezokSzetkapcsolasa(parancs);
                    break;
                case "VizAllit":
                    VizAllit(parancs);
                    break;
                case "HibasAllit":
                    HibasAllit(parancs);
                    break;
                case "TargyAllit":
                    TargyAllit(parancs);
                    break;
                case "PumpaBemenetAllit":
                    PumpaBemenetAllit(parancs);
                    break;
                case "PumpaKimenetAllit":
                    PumpaKimenetAllit(parancs);
                    break;
                case "Mozgas":
                    Mozgas(parancs);
                    break;
                case "Lyukaszt":
                    Lyukaszt(parancs);
                    break;
                case "Foltoz":
                    Foltoz(parancs);
                    break;
                case "Ragasztoz":
                    Ragasztoz(parancs);
                    break;
                case "Csuszosit":
                    Csuszosit(parancs);
                    break;
                case "PumpaAllit":
                    PumpaAllit(parancs);
                    break;
                case "CsoFelvetel":
                    CsoFelvetel(parancs);
                    break;
                case "VizLeptet":
                    VizLeptet(parancs);
                    break;
                case "PontNovel":
                    PontNovel(parancs);
                    break;
                case "VeletlenPumpaElrontas":
                    VeletlenPumpaElrontas(parancs);
                    break;
                case "StepTime":
                    StepTime(parancs);
                    break;
                case "KorLeptetese":
                    KorLeptetese(parancs);
                    break;
                case "CsucsVizetPumpal":
                    CsucsVizetPumpal(parancs);
                    break;
                case "JatekosInfo":
                    JatekosInfo(parancs);
                    break;
                case "MezoInfo":
                    MezoInfo(parancs);
                    break;
                case "CsapatInfo":
                    CsapatInfo(parancs);
                    break;
                case "VizInfo":
                    VizInfo(parancs);
                    break;
                default:
                    break;

            }
        }
    }
    void TesztVege(String[] parancs){
        if(checkParamCount(0,parancs)){
            Kontroller.getInstance().reInitialize();
        }
    }
    void AllapotAllit(String[] parancs){
        if(checkParamCount(2,parancs, true)){
            //TODO ide allapot allitast
            //mezoIds.get(parancs[1]).
            if(parancs[2].equals("csuszos")&&parancs.length == 4){
                if(mezoIds.containsKey(parancs[3])){
                    //TODO ide hova csússzont beallitani
                }
                else
                    kiir("hibas_azonosito");
            }
        }
    }
    void PumpaFelvetel(String[] parancs){
        if(checkParamCount(1,parancs,true)){
            if(checkIdentifier(parancs[1],"szerelo")){
                szereloIds.get(parancs[1]).pumpaFelvetele();
                mezoIds.put(parancs.length==3?parancs[2]:"p", szereloIds.get(parancs[1]).getPumpa());
            }
        }
    }
    void TargyLerakasa(String[] parancs){
        if(checkParamCount(1,parancs,true)&&checkIdentifier(parancs[1],"szerelo")){
            if(szereloIds.get(parancs[1]).getPumpa() !=null&&parancs.length == 3){
                szereloIds.get(parancs[1]).pumpatLerak();
                for (Cso cs:kontroller.getCsovek()
                ) {
                    if(!mezoIds.containsValue(cs)) {
                        mezoIds.put(parancs[2], cs);
                        csoIds.put(parancs[2], cs);
                    }
                }
            } else if (szereloIds.get(parancs[1]).getCsoveg() !=null) {
                szereloIds.get(parancs[1]).csovegetLerak();
            }
        }
    }
    void PumpaJavitasa(String[] parancs){
        if(checkParamCount(1,parancs)&&checkIdentifier(parancs[1],"szerelo")){
            szereloIds.get(parancs[1]).mezotJavit();
        }
    }
    void MezoHozzaadasa(String[] parancs){
        if(checkParamCount(2,parancs,true)){
            switch (parancs[2]){
                case "Cso":
                    Cso cso = new Cso();
                    mezoIds.put(parancs[1], cso);
                    csoIds.put(parancs[1], cso);
                    if(parancs.length == 4 && checkIdentifier(parancs[3], "csucs")){
                        cso.addCsucs(csucsIds.get(parancs[3]));
                        csucsIds.get(parancs[3]).addCso(cso);
                    }
                    break;
                case"Pumpa":
                    Pumpa pumpa = new Pumpa();
                    mezoIds.put(parancs[1], pumpa);
                    csucsIds.put(parancs[1], pumpa);
                    if(parancs.length == 4 && checkIdentifier(parancs[3], "cso")){
                        pumpa.addCso(csoIds.get(parancs[3]));
                        csoIds.get(parancs[3]).addCsucs(pumpa);
                    }
                    break;
                case"Ciszterna":
                    Ciszterna ciszterna = new Ciszterna();
                    mezoIds.put(parancs[1], ciszterna);
                    csucsIds.put(parancs[1], ciszterna);
                    if(parancs.length == 4 && checkIdentifier(parancs[3], "cso")){
                        ciszterna.addCso(csoIds.get(parancs[3]));
                        csoIds.get(parancs[3]).addCsucs(ciszterna);
                    }
                    break;
                case"Forras":
                    Forras forras = new Forras();
                    mezoIds.put(parancs[1], forras);
                    csucsIds.put(parancs[1], forras);
                    if(parancs.length == 4 && checkIdentifier(parancs[3], "cso")){
                        forras.addCso(csoIds.get(parancs[3]));
                        csoIds.get(parancs[3]).addCsucs(forras);
                    }
                    break;
                default:
                    kiir("hibas_parameter");
                    break;
            }
        }
    }
    void JatekosHozzaadasa(String[] parancs){
        if(checkParamCount(3,parancs)){
            if(parancs[2].equals("Szerelo")){
                Szerelo szerelo = new Szerelo();
                jatekosIds.put(parancs[1], szerelo);
                szereloIds.put(parancs[1], szerelo);
                if(checkIdentifier(parancs[3], "mezo")){
                    szerelo.setAktMezo(mezoIds.get(parancs[3]));
                    mezoIds.get(parancs[3]).setJatekosRajta(szerelo);
                }
            }
            if(parancs[2].equals("Szabotor")){
                Szabotor szabotor = new Szabotor();
                jatekosIds.put(parancs[1], szabotor);
                szabotorIds.put(parancs[1], szabotor);
                if(checkIdentifier(parancs[3], "mezo")){
                    szabotor.setAktMezo(mezoIds.get(parancs[3]));
                    mezoIds.get(parancs[3]).setJatekosRajta(szabotor);
                }
            }
            else
                kiir("hibas_parameter");
        }
    }
    void JatekosEltavolitasa(String[] parancs){
        if(checkParamCount(1,parancs)&&checkIdentifier(parancs[1],"jatekos")){
            jatekosIds.get(parancs[1]).getAktMezo().setJatekosRajta(null);
            kontroller.removeJatekos(jatekosIds.get(parancs[1]));
            jatekosIds.remove(parancs[1]);
            if(szereloIds.containsKey(parancs[1]))
                szereloIds.remove(parancs[1]);
            if(szabotorIds.containsKey(parancs[1]))
                szabotorIds.remove(parancs[1]);
        }
    }
    void MezokSzetkapcsolasa(String[] parancs){
        if(checkParamCount(2,parancs)&& checkIdentifier(parancs[1],"Mezo")&&checkIdentifier(parancs[2],"Mezo")) {
            if(mezoIds.get(parancs[1]).getNeighbours().contains(mezoIds.get(parancs[2]))&&
                    mezoIds.get(parancs[2]).getNeighbours().contains(mezoIds.get(parancs[1]))){
                if(csoIds.containsKey(parancs[1])&&csucsIds.containsKey(parancs[2])){
                    csoIds.get(parancs[1]).removeNeighbour(csucsIds.get(parancs[2]));
                    csucsIds.get(parancs[2]).removeNeighbour(csoIds.get(parancs[1]));
                }
                if(csoIds.containsKey(parancs[2])&&csucsIds.containsKey(parancs[1])){
                    csoIds.get(parancs[2]).removeNeighbour(csucsIds.get(parancs[1]));
                    csucsIds.get(parancs[1]).removeNeighbour(csoIds.get(parancs[2]));
                }
            }
        }
    }
    void VizAllit(String[] parancs){
        if(checkParamCount(2,parancs)&& checkIdentifier(parancs[1],"Mezo")){
            switch (parancs[2]){
                case "true":
                    mezoIds.get(parancs[1]).setVanViz(true);
                    break;
                case"false":
                    mezoIds.get(parancs[1]).setVanViz(false);
                    break;
                default:
                    break;
                }
        }
    }
    void HibasAllit(String[] parancs){
        if(checkParamCount(2,parancs)&& checkIdentifier(parancs[1],"Mezo")) {
            if(csoIds.containsKey(parancs[1])){
                if(parancs[2].equals("true"))
                    csoIds.get(parancs[1]).setRossz(false);
                if(parancs[2].equals("false"))
                    csoIds.get(parancs[1]).setRossz(true);
            }
            if(csucsIds.containsKey(parancs[1])){
                if(parancs[2].equals("true"))
                    csucsIds.get(parancs[1]).setRossz(false);
                if(parancs[2].equals("false"))
                    csucsIds.get(parancs[1]).setRossz(true);
            }
        }
    }
    void TargyAllit(String[] parancs){

    }
    void PumpaBemenetAllit(String[] parancs){

    }
    void PumpaKimenetAllit(String[] parancs){

    }
    void Mozgas(String[] parancs){

    }
    void Lyukaszt(String[] parancs){

    }
    void Foltoz(String[] parancs){

    }
    void Ragasztoz(String[] parancs){

    }
    void Csuszosit(String[] parancs){

    }
    void PumpaAllit(String[] parancs){

    }
    void CsoFelvetel(String[] parancs){

    }
    void VizLeptet(String[] parancs){

    }
    void PontNovel(String[] parancs){

    }
    void VeletlenPumpaElrontas(String[] parancs){

    }
    void StepTime(String[] parancs){

    }
    void KorLeptetese(String[] parancs){

    }
    void CsucsVizetPumpal(String[] parancs){

    }
    void MezokOsszekapcsolasa(String[] parancs){

    }
    void JatekosInfo(String[] parancs){

    }
    void MezoInfo(String[] parancs){

    }
    void CsapatInfo(String[] parancs){

    }
    void VizInfo(String[] parancs){

    }
}
