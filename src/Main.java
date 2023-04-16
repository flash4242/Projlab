import java.util.Scanner;

public class Main {
    public static void main(String args[])
    {
        System.out.println("0: Kilepes\n" +
                "1: <1. Teszteset>\n" +
                "2: <2. Teszteset>\n" +
                "3: <3. Teszteset>\n");
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
