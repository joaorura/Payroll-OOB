package memento;

public interface IMemento<T> {
    T undo();

    T redo();

    boolean backup(T rest, boolean type);
}
