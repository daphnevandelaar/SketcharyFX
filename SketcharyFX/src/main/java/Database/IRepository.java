package Database;

public interface IRepository<T> {
    Iterable<T> getAll();           //equivalent of IEnumerable.. C#
    void insert(T entity);
    void update(T entity);
    T getById(int id);
}
