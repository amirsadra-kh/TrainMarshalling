import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);

        LineFactory L = new LineFactory();
        HashMap<Integer, Line> lines = new HashMap<>();

        lines.put(0, L.makeLine("DGCDG",10));
        lines.put(1, L.makeLine("ACCACC",10));
        lines.put(2, L.makeLine("CDGADGA",10));
        lines.put(3, L.makeLine("DGDGADG",10));
        lines.put(4, L.makeLine("AADGAD",10));
        lines.put(5, L.makeLine("ACGDCGD", 10));

        //System.out.println(lines);

        Marshaller m = new Marshaller(lines,'C');
        m.marshallTrains();
    }
}
