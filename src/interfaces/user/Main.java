package interfaces.user;

import interfaces.system.Payroll;

import java.lang.reflect.InvocationTargetException;

import static interfaces.user.UtilsMain.readEntries;

public class Main{
    public static void main (String[] args) {
        System.out.println("Inicializando o sistema\n" +
                "\tInicializando configurações:\n");
        Payroll.getDefault().configurations(UtilsMain.getDate());

        int input;
        while (true) {
            UtilsMain.printIntro();
            input = readEntries(0,11);
                if(input == 11) return;
                else if (!UtilsMain.processEntries(input)) {
                    System.out.println("A sua operação falhou, o sistema não conlcluiu a sua ação. Por favor " +
                            "realize-a novamente!");
                }
        }
    }
}
