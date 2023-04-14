import java.util.Scanner;

public class Skeleton {
    boolean kerdes(String szoveg){
        System.out.println(szoveg);
        Scanner scan = new Scanner(System.in);
        int input;
        input = scan.nextInt();
        return input==0 ? false : true;
    }
    void tesztesetValaszto(int input){
        switch(input){
            case 1: teszt1(); break;
            default: break;
        }
    }
    void teszt1(){
        System.out.println("\t"+"1-es teszteset\n");
    }
}
