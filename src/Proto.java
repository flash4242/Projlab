import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Proto {
    private File inputFile;
    private File outputFile;
    private List<String[]> parancsok;
    private Kontroller kontroller;
    private Map<String, Jatekos> jatekosIds;
    private Map<String, Szerelo> szereloIds;
    private Map<String, Szabotor> szabotorIds;
    private Map<String, Mezo> mezoIds;
    private Map<String, Cso> csoIds;
    private Map<String, Csucs> csucsIds;

    public Proto(String in, String out){
        inputFile = new File(in);
        if(new File(out).exists())
            new File(out).delete();
        outputFile = new File(out);
        try {
            outputFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        kontroller = Kontroller.getInstance();
        szereloIds = new HashMap<>();
        szabotorIds = new HashMap<>();
        jatekosIds = new HashMap<>();
        csoIds = new HashMap<>();
        csucsIds = new HashMap<>();
        mezoIds = new HashMap<>();
    }
    public void delete(){
        outputFile.delete();
    }

    public void beolvas() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        parancsok = new ArrayList<>();
        while(scanner.hasNextLine()){
            String akt = scanner.nextLine();
            if(!akt.startsWith("#")) {
                parancsok.add(akt.split(" "));
            }
        }
        scanner.close();
    }

    public void kiir(String szoveg){
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(outputFile, true);
            fileWriter.write(szoveg);
            fileWriter.write("\n");
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> String getID(Map<String, T> map, T object){
        if(object == null)
            return "null";
        for (Map.Entry<String, T> entry : map.entrySet()) {
            if (Objects.equals(object, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    public boolean checkIdentifier(String id){
        boolean helyes = mezoIds.containsKey(id)||jatekosIds.containsKey(id);
        if(!helyes)
            kiir("hibas_azonosito");
        return helyes;
    }
    public boolean checkIdentifier(String id, String tipus){
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
    public boolean checkParamCount(int elvart, String[] akt, int max){
        boolean helyes;
            helyes = elvart <= akt.length-1 && akt.length-1 <= max;
        if(!helyes)
            kiir("hibas_parameter");
        return helyes;
    }
    public boolean checkParamCount(int elvart, String[] akt){
        boolean helyes;
        helyes = elvart == akt.length-1;
        if(!helyes)
            kiir("hibas_parameter");
        return helyes;
    }

    public void vegrehajt(){
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
    public void TesztVege(String[] parancs){
        if(checkParamCount(0,parancs)){
            Kontroller.getInstance().reset();
        }
    }
    public void AllapotAllit(String[] parancs){
        if(checkParamCount(2,parancs, 3)){
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
    public void PumpaFelvetel(String[] parancs){
        if(checkParamCount(1,parancs,2)){
            if(checkIdentifier(parancs[1],"szerelo")){
                szereloIds.get(parancs[1]).pumpaFelvetele();
                mezoIds.put(parancs.length==3?parancs[2]:"p", szereloIds.get(parancs[1]).getPumpa());
            }
        }
    }
    public void TargyLerakasa(String[] parancs){
        if(checkParamCount(1,parancs,2)&&checkIdentifier(parancs[1],"szerelo")){
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
        if(checkParamCount(2,parancs,3)){
            switch (parancs[2]){
                case "Cso":
                    Cso cso = new Cso();
                    mezoIds.put(parancs[1], cso);
                    csoIds.put(parancs[1], cso);
                    kontroller.addCso(cso);
                    if(parancs.length == 4 && checkIdentifier(parancs[3], "csucs")){
                        cso.addCsucs(csucsIds.get(parancs[3]));
                        csucsIds.get(parancs[3]).addCso(cso);
                    }
                    break;
                case"Pumpa":
                    Pumpa pumpa = new Pumpa();
                    mezoIds.put(parancs[1], pumpa);
                    csucsIds.put(parancs[1], pumpa);
                    kontroller.addCsucs(pumpa);
                    if(parancs.length == 4 && checkIdentifier(parancs[3], "cso")){
                        pumpa.addCso(csoIds.get(parancs[3]));
                        csoIds.get(parancs[3]).addCsucs(pumpa);
                    }
                    break;
                case"Ciszterna":
                    Ciszterna ciszterna = new Ciszterna();
                    mezoIds.put(parancs[1], ciszterna);
                    csucsIds.put(parancs[1], ciszterna);
                    kontroller.addCsucs(ciszterna);
                    if(parancs.length == 4 && checkIdentifier(parancs[3], "cso")){
                        ciszterna.addCso(csoIds.get(parancs[3]));
                        csoIds.get(parancs[3]).addCsucs(ciszterna);
                    }
                    break;
                case"Forras":
                    Forras forras = new Forras();
                    mezoIds.put(parancs[1], forras);
                    csucsIds.put(parancs[1], forras);
                    kontroller.addCsucs(forras);
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
            else if(parancs[2].equals("Szabotor")){
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
        if(checkParamCount(2,parancs)&& checkIdentifier(parancs[1],"mezo")&&checkIdentifier(parancs[2],"mezo")) {
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
        if(checkParamCount(2,parancs)&& checkIdentifier(parancs[1],"mezo")){
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
        if(checkParamCount(2,parancs)&& checkIdentifier(parancs[1],"mezo")) {
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
        if(checkParamCount(1,parancs,3)&& checkIdentifier(parancs[1],"szerelo")) {
            if(parancs.length == 2){
                if(szereloIds.get(parancs[1]).getCsoveg() != null)
                    kontroller.removeCso(szereloIds.get(parancs[1]).getCsoveg());
                if(szereloIds.get(parancs[1]).getPumpa() != null)
                    kontroller.removeCsucs(szereloIds.get(parancs[1]).getPumpa());
                szereloIds.get(parancs[1]).setCsoveg(null);
                szereloIds.get(parancs[1]).setPumpa(null);
            }
            if(parancs.length == 4){
                if(parancs[2].equals("Pumpa")) {
                    Pumpa pumpa = new Pumpa();
                    kontroller.addCsucs(pumpa);
                    csucsIds.put(parancs[3], pumpa);
                    mezoIds.put(parancs[3], pumpa);
                    szereloIds.get(parancs[1]).setPumpa(pumpa);
                }
                if(parancs[2].equals("Cso")) {
                    Cso cso = new Cso();
                    kontroller.addCso(cso);
                    csoIds.put(parancs[3], cso);
                    mezoIds.put(parancs[3], cso);
                    szereloIds.get(parancs[1]).setCsoveg(cso);
                }
            }
        }
    }
    void PumpaBemenetAllit(String[] parancs){
        if(checkParamCount(1,parancs,2)&& checkIdentifier(parancs[1],"csucs")) {
            if(parancs.length == 2){
                csucsIds.get(parancs[1]).setBemenetiCso(null);
            }
            if(parancs.length == 3 && csucsIds.get(parancs[1]).getNeighbours().contains(csoIds.get(parancs[2]))){
                csucsIds.get(parancs[1]).setBemenetiCso(csoIds.get(parancs[2]));
            }
        }
    }
    void PumpaKimenetAllit(String[] parancs){
        if(checkParamCount(1,parancs,2)&& checkIdentifier(parancs[1],"csucs")) {
            if(parancs.length == 2){
                csucsIds.get(parancs[1]).setKimenetiCso(null);
            }
            if(parancs.length == 3 && csucsIds.get(parancs[1]).getNeighbours().contains(csoIds.get(parancs[2]))){
                csucsIds.get(parancs[1]).setKimenetiCso(csoIds.get(parancs[2]));
            }
        }
    }
    void Mozgas(String[] parancs){
        if(checkParamCount(2,parancs)&& checkIdentifier(parancs[1],"jatekos")&&
                checkIdentifier(parancs[2],"mezo")) {
            jatekosIds.get(parancs[1]).mozgas(
                    jatekosIds.get(parancs[1]).getAktMezo().getNeighbours().indexOf(mezoIds.get(parancs[2]))
            );
        }
    }
    void Lyukaszt(String[] parancs){
        if(checkParamCount(1,parancs)&& checkIdentifier(parancs[1],"jatekos")) {
            jatekosIds.get(parancs[1]).csoKilyukasztasa();
        }
    }
    void Foltoz(String[] parancs){
        if(checkParamCount(1,parancs)&& checkIdentifier(parancs[1],"szerelo")) {
            szereloIds.get(parancs[1]).mezotJavit();
        }
    }
    void Ragasztoz(String[] parancs){
        if(checkParamCount(1,parancs)&& checkIdentifier(parancs[1],"jatekos")) {
            jatekosIds.get(parancs[1]).beragasztoz();
        }
    }
    void Csuszosit(String[] parancs){
        if(checkParamCount(1,parancs)&& checkIdentifier(parancs[1],"szabotor")) {
            szabotorIds.get(parancs[1]).csuszosit();
        }
    }
    void PumpaAllit(String[] parancs){
        if(checkParamCount(3,parancs)&& checkIdentifier(parancs[1],"jatekos")) {
            List<? extends Mezo> m = jatekosIds.get(parancs[1]).getAktMezo().getNeighbours();
            if(m.contains(mezoIds.get(parancs[2])) && m.contains(mezoIds.get(parancs[3])))
                jatekosIds.get(parancs[1]).pumpaAtallitasa(m.indexOf(mezoIds.get(parancs[2])), m.indexOf(mezoIds.get(parancs[3])));
        }
    }
    void CsoFelvetel(String[] parancs){
        if(checkParamCount(2,parancs)&& checkIdentifier(parancs[1],"szerelo")&& checkIdentifier(parancs[2],"cso")) {
            if(szereloIds.get(parancs[1]).getAktMezo().getNeighbours().contains(csoIds.get(parancs[2]))){
                szereloIds.get(parancs[1]).csovegFelvetele(szereloIds.get(parancs[1]).getAktMezo().getNeighbours().indexOf(csoIds.get(parancs[2])));
            }
        }
    }
    void VizLeptet(String[] parancs){
        if(checkParamCount(0,parancs)) {
            kontroller.vizLeptet();
        }
    }
    void PontNovel(String[] parancs){
        if(checkParamCount(1,parancs)) {
            kontroller.pontNovel(parancs[1]);
        }
    }
    void VeletlenPumpaElrontas(String[] parancs){
        if(checkParamCount(0,parancs, 1)) {
            if(parancs.length == 2){
                for (Csucs cs:csucsIds.values()
                     ) {
                    if(parancs[1].equals("elront"))
                        cs.setRossz(true);
                    else if(parancs[1].equals("hagy"))
                        cs.setRossz(false);
                    else
                        kiir("hibas_parameter");
                }
            }
            else {
                kontroller.veletlenPumpaElrontas();
            }
        }
    }
    void StepTime(String[] parancs){
        if(checkParamCount(0,parancs)) {
            kontroller.stepTime();
        }
    }
    void KorLeptetese(String[] parancs){
        if(checkParamCount(0,parancs)) {
            kontroller.vizLeptet();
            kontroller.stepTime();
        }
    }
    void CsucsVizetPumpal(String[] parancs){
        if(checkParamCount(1,parancs)) {
            csucsIds.get(parancs[1]).vizetPumpal();
        }
    }
    void MezokOsszekapcsolasa(String[] parancs){
        if(checkParamCount(2,parancs)&& checkIdentifier(parancs[1],"mezo")&&checkIdentifier(parancs[2],"mezo")) {
                if(csoIds.containsKey(parancs[1])&&csucsIds.containsKey(parancs[2])){
                    csoIds.get(parancs[1]).addCsucs(csucsIds.get(parancs[2]));
                    csucsIds.get(parancs[2]).addCso(csoIds.get(parancs[1]));
                }
                if(csoIds.containsKey(parancs[2])&&csucsIds.containsKey(parancs[1])){
                    csoIds.get(parancs[2]).addCsucs(csucsIds.get(parancs[1]));
                    csucsIds.get(parancs[1]).addCso(csoIds.get(parancs[2]));
                }
        }
    }
    void JatekosInfo(String[] parancs){
        if(checkParamCount(0,parancs,1)&&checkIdentifier(parancs[1], "jatekos")) {
            if(parancs.length == 1){
                jatekosIds.forEach((key, value)->{
                    String aktmezo = getID(mezoIds, value.getAktMezo());
                    String ragados = value.getRagados()?"true":"false";
                    if(szereloIds.containsKey(key)){
                        Szerelo sz = (Szerelo)value;
                        String targy;
                        if(sz.getPumpa() != null)
                            targy = getID(csucsIds, sz.getPumpa());
                        else if(sz.getCsoveg()!= null)
                            targy = getID(csoIds, sz.getCsoveg());
                        else
                            targy = "null";
                        kiir(key+" "+"Szerelo"+" "+aktmezo+" "+ragados+" "+targy);
                    }
                    else{
                        kiir(key+" "+"Szabotor"+" "+aktmezo+" "+ragados);
                    }
                });

            }
            else{
                String aktmezo = getID(mezoIds, jatekosIds.get(parancs[1]).getAktMezo());
                String ragados = jatekosIds.get(parancs[1]).getRagados()?"true":"false";
                if(szereloIds.containsKey(parancs[1])){
                    Szerelo sz = (Szerelo)jatekosIds.get(parancs[1]);
                    String targy;
                    if(sz.getPumpa() != null)
                        targy = getID(csucsIds, sz.getPumpa());
                    else if(sz.getCsoveg()!= null)
                        targy = getID(csoIds, sz.getCsoveg());
                    else
                        targy = "null";
                    kiir(parancs[1]+" "+"Szerelo"+" "+aktmezo+" "+ragados+" "+targy);
                }
                else{
                    kiir(parancs[1]+" "+"Szabotor"+" "+aktmezo+" "+ragados);
                }
            }
        }
    }
    void MezoInfo(String[] parancs){
        if(checkParamCount(0,parancs,1)) {
            if(parancs.length == 1){
                mezoIds.forEach((key, value)->{
                    String vanViz = value.vanViz?"true":"false";
                    StringBuilder csoSzomszedok = new StringBuilder();
                    if(value.getNeighbours().size()==2){
                        csoSzomszedok.append(" ").append(getID(mezoIds, value.getNeighbours().get(0)));
                        csoSzomszedok.append(" ").append(getID(mezoIds, value.getNeighbours().get(1)));
                    }
                    else if(value.getNeighbours().size()==1) {
                        csoSzomszedok.append(" ").append(getID(mezoIds, value.getNeighbours().get(0))).append(" null");
                    }
                    else
                        csoSzomszedok.append(" null null");
                    String csSz = String.valueOf(csoSzomszedok);
                    StringBuilder csucsSzomszedok = new StringBuilder();
                    for (Mezo m:value.getNeighbours()
                    ) {
                        csucsSzomszedok.append(" ").append(getID(mezoIds, m));
                    }
                    String sz = String.valueOf(csucsSzomszedok);
                    if(csoIds.containsKey(key)){
                        Cso cso = (Cso)value;
                        String jatekos = getID(jatekosIds, cso.getJatekosRajta().get(0));
                        String rossz = cso.getRossz()?"true":"false";
                        String foltGar = Integer.toString(cso.getFoltozasiGarancia());
                        String allapot = "";
                        switch (cso.getAllapot()){
                            case NORMALIS:
                                allapot = "normalis";
                                break;
                            case CSUSZOS:
                                allapot = "csuszos";
                                break;
                            case RAGADOS:
                                allapot = "ragados";
                                break;
                            default:
                                break;
                        }
                        String tTNormal = Integer.toString(cso.getTimeToNormal());
                        kiir(key+" "+"Cso"+" "+jatekos+" "+vanViz+" "+rossz+" "+foltGar+" "+allapot+" "+tTNormal+csSz);
                    }
                    if(csucsIds.containsKey(key)){
                        if(value.getClass()==Pumpa.class){
                            Pumpa p = (Pumpa) value;
                            String jatekos = getID(jatekosIds, p.getJatekosRajta().get(0));
                            String rossz = p.getRossz()?"true":"false";
                            String be = getID(mezoIds, p.getNeighbours().get(p.getBemenetiCso()));
                            String ki = getID(mezoIds, p.getNeighbours().get(p.getKimenetiCso()));
                            kiir(key+" "+"Pumpa"+" "+jatekos+" "+vanViz+" "+rossz+" "+be+" "+ki+sz);
                        }
                        if(value.getClass()==Ciszterna.class){
                            Ciszterna c = (Ciszterna) value;
                            String jatekos = getID(jatekosIds, c.getJatekosRajta().get(0));
                            kiir(key+" "+"Ciszterna"+" "+jatekos+sz);
                        }
                        if(value.getClass()==Forras.class){
                            Forras forras = (Forras) value;
                            String jatekos = getID(jatekosIds, forras.getJatekosRajta().get(0));
                            kiir(key+" "+"Forras"+" "+jatekos+sz);
                        }
                    }
                });

            }
            else if(checkIdentifier(parancs[1], "mezo")){
                String vanViz = mezoIds.get(parancs[1]).vanViz?"true":"false";
                StringBuilder csoSzomszedok = new StringBuilder();
                if(mezoIds.get(parancs[1]).getNeighbours().size()==2){
                    csoSzomszedok.append(" ").append(getID(mezoIds, mezoIds.get(parancs[1]).getNeighbours().get(0)));
                    csoSzomszedok.append(" ").append(getID(mezoIds, mezoIds.get(parancs[1]).getNeighbours().get(1)));
                }
                else if(mezoIds.get(parancs[1]).getNeighbours().size()==1) {
                    csoSzomszedok.append(" ").append(getID(mezoIds, mezoIds.get(parancs[1]).getNeighbours().get(0))).append(" null");
                }
                else
                    csoSzomszedok.append(" null null");
                String csSz = String.valueOf(csoSzomszedok);
                StringBuilder csucsSzomszedok = new StringBuilder();
                for (Mezo m:mezoIds.get(parancs[1]).getNeighbours()
                ) {
                    csucsSzomszedok.append(" ").append(getID(mezoIds, m));
                }
                String sz = String.valueOf(csucsSzomszedok);

                if(csoIds.containsKey(parancs[1])){
                    Cso cso = (Cso)mezoIds.get(parancs[1]);
                    String jatekos = getID(jatekosIds, cso.getJatekosRajta().size()==0?null:cso.getJatekosRajta().get(0));
                    String rossz = cso.getRossz()?"true":"false";
                    String foltGar = Integer.toString(cso.getFoltozasiGarancia());
                    String allapot = "";
                    switch (cso.getAllapot()){
                        case NORMALIS:
                            allapot = "normalis";
                            break;
                        case CSUSZOS:
                            allapot = "csuszos";
                            break;
                        case RAGADOS:
                            allapot = "ragados";
                            break;
                        default:
                            break;
                    }
                    String tTNormal = Integer.toString(cso.getTimeToNormal());
                    kiir(parancs[1]+" "+"Cso"+" "+jatekos+" "+vanViz+" "+rossz+" "+foltGar+" "+allapot+" "+tTNormal+csSz);
                }
                if(csucsIds.containsKey(parancs[1])){
                    if(mezoIds.get(parancs[1]).getClass()==Pumpa.class){
                        Pumpa p = (Pumpa) mezoIds.get(parancs[1]);
                        String jatekos = getID(jatekosIds, p.getJatekosRajta().get(0));
                        String rossz = p.getRossz()?"true":"false";
                        String be;
                        if(p.getBemenetiCso() == -1)
                            be = "null";
                        else
                            be= getID(mezoIds, p.getNeighbours().get(p.getBemenetiCso()));
                        String ki;
                        if(p.getKimenetiCso() == -1)
                            ki = "null";
                        else
                            ki = getID(mezoIds, p.getNeighbours().get(p.getKimenetiCso()));
                        kiir(parancs[1]+" "+"Pumpa"+" "+jatekos+" "+vanViz+" "+rossz+" "+be+" "+ki+sz);
                    }
                    if(mezoIds.get(parancs[1]).getClass()==Ciszterna.class){
                        Ciszterna c = (Ciszterna) mezoIds.get(parancs[1]);
                        String jatekos = getID(jatekosIds, c.getJatekosRajta().get(0));
                        kiir(parancs[1]+" "+"Ciszterna"+" "+jatekos+sz);
                    }
                    if(mezoIds.get(parancs[1]).getClass()==Forras.class){
                        Forras forras = (Forras) mezoIds.get(parancs[1]);
                        String jatekos = getID(jatekosIds, forras.getJatekosRajta().get(0));
                        kiir(parancs[1]+" "+"Forras"+" "+jatekos+sz);
                    }
                }
            }
        }
    }
    void CsapatInfo(String[] parancs){
        if(checkParamCount(0,parancs,1)){
            if(parancs.length == 1){
                kiir("szabotor"+" "+kontroller.getSzabotorPontok());
                kiir("szerelo"+" "+kontroller.getSzereloPontok());
            }
            else{
                kiir(parancs[1]+" "+(parancs[1].equals("szabotor")?kontroller.getSzabotorPontok():kontroller.getSzereloPontok()));
            }
        }
    }
    void VizInfo(String[] parancs){
        if(checkParamCount(0,parancs)){
            StringBuilder sztring = new StringBuilder();
            mezoIds.forEach((key, value)->{
                if(value.getVanViz()){
                   sztring.append(key).append(" ");
                }
            });
            kiir(String.valueOf(sztring));
        }
    }
}
