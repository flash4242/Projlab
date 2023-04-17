import java.util.Scanner;

public class Main {
    public static void main(String args[])
    {
        Skeleton skeleton = new Skeleton();
        String[] methodNames= skeleton.getMethodsName();
        System.out.println("0: Kilepes");
        for(int i=1; i<21; i++){
            if (i == 9) {
                System.out.println("91: "+methodNames[9-1]);
                System.out.println("92: "+methodNames[methodNames.length-1]);
            }
            else {
                System.out.println(i + ": "+methodNames[i-1]);
            }
        }

        int input = 1;
        Scanner scan = new Scanner(System.in);
        while (input != 0) {
            System.out.print("Valasszon egy parancsot: ");
            input = scan.nextInt();
            if((input>0&&input<=20)||input==91||input==92) {
                System.out.print("START - ");
                if(input == 91)
                    System.out.println(methodNames[9-1]);
                else if(input==92)
                    System.out.println(methodNames[methodNames.length-1]);
                else
                    System.out.println(methodNames[input-1]);
                skeleton.tesztesetValaszto(input, scan);
                System.out.println("END");
            }
        }
        scan.close();
    }
}
