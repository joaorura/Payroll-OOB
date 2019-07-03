package interfaces.system;

interface IMemento<T> {
    T undo();

    T redo();

    void backup(T rest, boolean type);
}
