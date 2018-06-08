package rpsdal.repositories;

import model.Entity;
import rpsshared.interfaces.IDataContext;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public abstract class RepositoryBase<T extends Entity> {

    private IDataContext dataContext;

    public IDataContext getDataContext() {
        return dataContext;
    }

    public RepositoryBase(IDataContext dataContext)
    {
        this.dataContext = dataContext;
    }

    public List<T> getAll()
    {
        Type type = ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return (List<T>)getDataContext().getAll(type.getClass());
    }

    public T getSingle(long id)
    {
        Type type = ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return (T)getDataContext().getSingle(id, type.getClass());
    }

    public void add(T item)
    {
        getDataContext().add(item, item.getClass());
    }

    public void update(T item)
    {
        getDataContext().update(item, item.getClass());
    }

    public void remove(T item)
    {
        getDataContext().remove(item, item.getClass());
    }
}
