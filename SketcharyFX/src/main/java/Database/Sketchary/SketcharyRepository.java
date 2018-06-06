package Database.Sketchary;

import Database.IContext;
import Database.Repository;
import Models.Sketchary;

public class SketcharyRepository extends Repository<Sketchary> implements ISketcharyRepository {

    ISketcharyContext context;
    public SketcharyRepository(IContext<Sketchary> context) {
        super(context);
    }

    @Override
    public Sketchary getRandomSketchary() {
        return context.getRandomSketchary();
    }
}
