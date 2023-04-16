import java.util.Scanner;

public class Main {
    public static void main(String args[])
    {
        System.out.println("0: Kilepes");
        for(int i=1; i<21;i++){
            System.out.println(i+": <"+i+". Teszteset>");
        }
        Skeleton skeleton = new Skeleton();
        int input = 1;
        Scanner scan = new Scanner(System.in);
        while (input != 0) {
            System.out.print("Valasszon egy parancsot: ");
            input = scan.nextInt();
            System.out.println("START");
            skeleton.tesztesetValaszto(input);
            System.out.println("END");
        }
        scan.close();
    }
}
