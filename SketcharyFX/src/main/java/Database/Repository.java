package Database;

public class Repository<T> implements IRepository<T> {

    public IContext context;

    public IContext getContext() {
        return context;
    }

    protected void setContext(IContext context) {
        this.context = context;
    }

    public Repository(IContext<T> context){
        this.context = context;
    }

    @Override
    public Iterable<T> getAll() {
        return context.getAll();
    }

    @Override
    public void insert(T entity) {

    }

    @Override
    public void update(T entity) {

    }

    @Override
    public T getById(int id) {
        return null;
    }
}
