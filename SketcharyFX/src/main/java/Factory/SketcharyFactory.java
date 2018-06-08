package Factory;

import Database.Sketchary.SketcharyRepository;
import Database.Sketchary.SketcharySqlContext;
import Logic.ISketcharyLogic;
import Logic.SketcharyLogic;

public class SketcharyFactory {

    public static ISketcharyLogic manageSketchys(){ return new SketcharyLogic(new SketcharyRepository(new SketcharySqlContext())); }
}
