package Database;

import Models.User;

public abstract class SqlContext<T> extends DbHandler implements IContext<T> {
    public abstract Iterable<T> getAll();
    public abstract void insert(T entity);
    public abstract void update(T entity);
}
