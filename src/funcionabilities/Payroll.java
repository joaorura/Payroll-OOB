package funcionabilities;

import funcionabilities.functional_aids.payments.ITypePayments;
import funcionabilities.functional_aids.transactions.IMethodsPayments;
import memento.IMemento;
import memento.Restore;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Hashtable;

public class Payroll implements Cloneable{
     private Hashtable<String, Employee> employees;
     private IMemento<Payroll> backup;
     private ArrayList<IMethodsPayments> methodsPayments;
     private ArrayList<ITypePayments> typesPayments;
     private GregorianCalendar actualCalendar;

     private static Payroll pay_default = new Payroll();

     private Payroll() {
          this.employees = new Hashtable<>();
          this.backup = new Restore();
          this.methodsPayments = new ArrayList<>();
          this.typesPayments = new ArrayList<>();
          this.actualCalendar = (GregorianCalendar) GregorianCalendar.getInstance();

          reconfigure();
     }

     private void reconfigure() {

     }

     public long addEmployee() {
          return -1;
     }

     public long removeEmployee() {
          return -1;
     }

     public boolean undo() {
          return false;
     }

     public boolean redo() {
          return false;
     }

     public Payroll clone() throws CloneNotSupportedException {
          Payroll item = (Payroll) super.clone();
          return item;
     }
}