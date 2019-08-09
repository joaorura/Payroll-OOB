package interfaces.system.memento;

import interfaces.system.Payroll;

class Memento {
    private Payroll savedState;

    Memento(Payroll savedState) throws InstantiationException {
        try {
            this.savedState = savedState.clone();
        } catch (CloneNotSupportedException e) {
            throw new InstantiationException("Error in instatiate Memento because due to failed Payroll clone attempt.");
        }
    }

    Payroll getSavedState() {
        return savedState;
    }
}
