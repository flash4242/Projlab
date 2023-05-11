import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tester {
    private File input;
    private File output;

    public Tester(String in, String out){
        input = new File(in);
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
    public void Test(){
        List<String[]> bemenet = beolvas(input);
        List<String[]> kimenet = beolvas(output);
        for(int i = 0; i< bemenet.size();++i){

        }
    }
}
