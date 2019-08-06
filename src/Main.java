import interfaces.system.Payroll;
import interfaces.user.UtilsMain;

import static interfaces.user.UtilsMain.readEntries;
import static interfaces.user.Process.processEntries;
public class Main {
    public static void main(String[] args) {
        System.out.println("Inicializando o sistema\n" +
                "\tInicializando configurações:\n");
        Payroll.getDefault().configurations(UtilsMain.getDate());

        int input;
        while (true) {
            UtilsMain.printIntro();
            input = readEntries(0, 10);
            if (input == 10) return;
            else if (!processEntries(input)) {
                System.out.println("A sua operação falhou, o sistema não conlcluiu a sua ação. Por favor " +
                        "realize-a novamente!");
                return;
            }
        }
    }
}