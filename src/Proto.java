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
    Map<String, Mezo> mezoIds;

    public Proto(String in, String out){
        inputFile = new File(in);
        outputFile = new File(out);
        kontroller = Kontroller.getInstance();
        jatekosIds = new HashMap<>();
        mezoIds = new HashMap<>();
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
            helyes = jatekosIds.containsKey(id);
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
            if(checkIdentifier(parancs[1],"jatekos")){
                jatekosIds.get(parancs[1]).pumpaFelvetele();
                mezoIds.put(parancs.length==3?parancs[2]:"d", jatekosIds.get(parancs[1]).getPumpa());
            }
        }
    }
    void TargyLerakasa(String[] parancs){
        if(checkParamCount(0,parancs,false)){

        }
    }
    void PumpaJavitasa(String[] parancs){
        if(checkParamCount(0,parancs,false)){

        }
    }
    void MezoHozzaadasa(String[] parancs){

    }
    void JatekosHozzaadasa(String[] parancs){

    }
    void JatekosEltavolitasa(String[] parancs){

    }
    void MezokSzetkapcsolasa(String[] parancs){

    }
    void VizAllit(String[] parancs){

    }
    void HibasAllit(String[] parancs){

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
