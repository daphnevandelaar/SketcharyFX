package Database;

public interface IContext<T> {
    Iterable<T> getAll();           //equivalent of IEnumerable.. C#
    void insert(T entity);
    void update(T entity);
}
