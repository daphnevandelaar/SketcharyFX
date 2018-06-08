package rpsshared.interfaces;

import model.Entity;
import model.Player;
import model.RoundResult;

import java.util.List;

public interface IDataContext {

    <T> T getSingle(long id, Class<T> returnType);
    <T> List<T> getAll(Class<T> returnType);
    <T> void add(Entity obj, Class<T> returnType);
    <T> void update(Entity obj, Class<T> returnType);
    <T> void remove(Entity obj, Class<T> returnType);

}
