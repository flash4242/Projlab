import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tester {
    private File elvart;
    private File output;
    private int hibaSzam = 0;

    public Tester(String vart, String out){
        elvart = new File(vart);
        output = new File(out);
    }
    public List<String[]> beolvas(File file){
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        List<String[]> szoveg = new ArrayList<>();
        while(scanner.hasNextLine()){
            String akt = scanner.nextLine();
            if(!akt.startsWith("#")) {
                szoveg.add(akt.split(" "));
            }
        }
        scanner.close();
        return szoveg;
    }
    public void Test(String args[]){
        List<String[]> elvartSzoveg = beolvas(elvart);
        List<String[]> kimenet = beolvas(output);
        int csillag = 0;
        if(elvartSzoveg.size()!=kimenet.size()) {
            System.out.println("Eltero hosszu a ket kimenet");
            ++hibaSzam;
        }
        else {
            for (int i = 0; i < elvartSzoveg.size(); ++i) {
                if(args.length == 1 && csillag != Integer.parseInt(args[0])-1){
                    if(elvartSzoveg.get(i)[0].equals("*"))
                        ++csillag;
                }
                else {
                    if (elvartSzoveg.get(i).length != kimenet.get(i).length) {
                        System.out.println("A(z) " + (i + 1) + ". sor eltero hosszu");
                        ++hibaSzam;
                    } else {
                        for (int j = 0; j < elvartSzoveg.get(i).length; ++j) {
                            if (!elvartSzoveg.get(i)[j].equals(kimenet.get(i)[j])) {
                                System.out.println("Hiba a(z) " + (i + 1) + ". sor " + (j + 1) + ". szavaban");
                                ++hibaSzam;
                            }
                        }
                    }
                    if(args.length == 1&&elvartSzoveg.get(i)[0].equals("*"))
                        break;
                }
            }
        }
        if(hibaSzam == 0){
            System.out.println("A tesztek hiba nelkul lefutottak");
        }
    }
}
