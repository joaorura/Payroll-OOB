import java.util.Scanner;

public class Main {
    private final static String start = "\n\nNova interação!"
            + "\n\nQual função você deseja realizar\n"
            + "\t1: Adição de um empregado\n"
            + "\t2: Remoção de um empregado\n"
            + "\t3: Lançar um Cartão de Ponto\n"
            + "\t4: Lançar um Resultado Venda\n"
            + "\t5: Lançar uma taxa de serviço\n"
            + "\t6: Alterar detalhes de um empregado\n"
            + "\t7: Rodar a folha de pagamento para hoje\n"
            + "\t8: Undo ou redo\n"
            + "\t9: Alterar agenda de pagamento de um empregado\n"
            + "\t10: Criação de Novas Agendas de Pagamento\n"
            + "\t11: Listar estado\n"
            + "\t12: Sair do programa";

    private static final Scanner scan = new Scanner(System.in);

    private static void configurations() {

    }

    private static int readEntries(int i, int i1) {
        int read = scan.nextInt();

        if(read < i || read > i1) {
            return readEntries(i, i1);
        }
        else {
            return read;
        }
    }

    private static boolean processEntries(int input) {
        System.out.println("\nInicializando a operação ...\n");
        Payroll pay = Payroll.getDefault();
        int id;
        boolean check = false;
        switch (input) {
            case 1:
                pay.backup(true);
                id = pay.addEmployee();
                if(id == -1) {
                    check = false;
                }
                else {
                    check = true;
                    System.out.println("Add : " + pay.searchEmployee(id).toString());
                }

                break;

            case 2:
                pay.backup(true);
                id = pay.removeEmployee();
                if(id == -1) {
                    check = false;
                }
                else {
                    check = true;
                    System.out.println("Removed: " + pay.searchEmployee(id).toString());
                }

                break;

            case 3:
                pay.backup(true);
                check = pay.processPointCard();
                break;

            case 4:
                pay.backup(true);
                check = pay.processSaleResult();
                break;

            case 5:
                pay.backup(true);
                check = pay.processServiceChange();
                break;

            case 6:
                pay.backup(true);
                check = pay.processEmployeeDetail();
                break;

            case 7:
                pay.backup(true);
                check = pay.runPayrollToday();
                break;

            case 8:
                System.out.println("Você deseja:");
                System.out.println("\t0: Desfazer");
                System.out.println("\t1: Refazer");
                if (readEntries(0, 1) == 0) check = pay.undo();
                else check = pay.redo();

                break;

            case 9:
                pay.backup(true);
                check = pay.setEmployerPaymentSchedule();
                break;

            case 10:
                pay.backup(true);
                check = pay.createEmployerPaymentSchedule();
                break;

            case 11:
                System.out.println("State: \n\t" + pay.toString());
                check = true;
                 break;
        }

        return check;
    }

    public static void main (String[] args) {
        System.out.println("Inicializando o sistema\n" +
                "\tInicializando configurações:\n");
        configurations();

        int input;
        while (true) {
            System.out.println(start);
            input = readEntries(1,12);

                if(input == 12) return;
                else if (!processEntries(input)) {
                    System.out.println("A sua operação falhou, o sistema não conlcluiu a sua ação. Por favor " +
                            "realize-a novamente!");
                }
        }
    }
}
