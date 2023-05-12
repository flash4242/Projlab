public class Main {
    public static void main(String args[])
    {
        Proto proto = new Proto("in.txt", "out.txt");
        proto.beolvas();
        proto.vegrehajt(args);
        Tester tester = new Tester("vart.txt", "out.txt");
        tester.Test(args);
    }
}
