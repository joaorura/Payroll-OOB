import java.util.GregorianCalendar;

public class Main {
    private static GregorianCalendar actualCalendar;

    private static void configurations() {
        actualCalendar = (GregorianCalendar) GregorianCalendar.getInstance();
    }

    public static GregorianCalendar getCalendar() {
        return actualCalendar;
    }

    public static void main(String[] args) {
        configurations();
    }
}
