package Database.Sketchary;

import Database.IRepository;
import Models.Sketchary;

public interface ISketcharyRepository extends IRepository<Sketchary> {
    Sketchary getRandomSketchary();
}
