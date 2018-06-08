package Database.Sketchary;

import Database.IContext;
import Models.Sketchary;

public interface ISketcharyContext extends IContext<Sketchary> {

    Sketchary getRandomSketchary();

    @Override
    Iterable<Sketchary> getAll();

    @Override
    void insert(Sketchary entity);

    @Override
    void update(Sketchary entity);
}
