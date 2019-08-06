import interfaces.system.Payroll;
import interfaces.user.UtilsMain;

import static interfaces.user.UtilsMain.readEntries;
import static interfaces.user.problematics.Process.processEntries;
public class Main {
    public static void main(String[] args) {
        System.out.println("Booting the system!\n" +
                "\tInitializing settings!\n");
        Payroll.getDefault().configurations(UtilsMain.getDate());

        int input;
        while (true) {
            UtilsMain.printIntro();
            input = readEntries(0, 10);
            if (input == 10) return;
            else if (!processEntries(input)) {
                System.out.println("Your operation failed, the system does not complete its action. Please" +
                        "do it again!");
                return;
            }
        }
    }
}
