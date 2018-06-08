package Database.Sketchary;

import Database.Repository;
import Models.Sketchary;

public class SketcharyRepository extends Repository<Sketchary> implements ISketcharyRepository {

    private ISketcharyContext sketcharyContext;

    public SketcharyRepository(ISketcharyContext context) {
        super(context);
        sketcharyContext = context;
    }

    @Override
    public Sketchary getRandomSketchary() {
        //ISketcharyContext cont = new SketcharySqlContext();
        //return cont.getRandomSketchary();
        return sketcharyContext.getRandomSketchary();
    }
}
