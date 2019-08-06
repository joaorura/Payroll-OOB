package interfaces.user;

import funcionabilities.Employee;
import funcionabilities.functional_aids.calendar.Calendar;
import interfaces.system.Payroll;

import javax.naming.directory.InvalidAttributesException;
import java.lang.reflect.Method;
import java.util.*;

import static interfaces.user.UtilsMain.readEntries;

class FuncionabilitiesInter {
    public static final Map<Integer, Method> funcionabilities = new HashMap<>();
    private static Payroll pay = null;
    private static int id = -1;

    static {
        Method[] methods = FuncionabilitiesInter.class.getDeclaredMethods();
        Arrays.sort(methods, Comparator.comparing(Method::getName));

        int j = 0;
        for (Method method : methods) {
            if (method.getName().equals("att")) continue;
            funcionabilities.put(j, method);
            j++;
        }
    }

    static void att(int ide) {
        pay = Payroll.getDefault();
        id = ide;
    }
}
