import java.util.Scanner;

public class Main {
    public static void main(String args[])
    {
        System.out.println("0: Kilepes\n" + // ennek ilyennek kell lennie? (en csinaltam NZ)
                "1: <1. Teszteset>\n" +
                "2: <2. Teszteset>\n" +
                "3: <3. Teszteset>\n" +
                "4: <4. Teszteset>\n" +
                "5: <5. Teszteset>\n" +
                "6: <6. Teszteset>\n" +
                "7: <7. Teszteset>\n" +
                "8: <8. Teszteset>\n" +
                "91: <91. Teszteset>\n" +
                "92: <92. Teszteset>\n" +
                "10: <10. Teszteset>\n" +
                "11: <11. Teszteset>\n" +
                "12: <12. Teszteset>\n" +
                "13: <13. Teszteset>\n" +
                "14: <14. Teszteset>\n" +
                "15: <15. Teszteset>\n" +
                "16: <16. Teszteset>\n" +
                "17: <17. Teszteset>\n" +
                "18: <18. Teszteset>\n" +
                "19: <19. Teszteset>\n" +
                "20: <20. Teszteset>\n");
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
