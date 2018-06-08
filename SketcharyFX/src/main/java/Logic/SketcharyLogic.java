package Logic;

import Database.Sketchary.ISketcharyRepository;
import Models.Sketchary;

public class SketcharyLogic implements ISketcharyLogic {

    ISketcharyRepository sketcharyRepository;

    public SketcharyLogic(ISketcharyRepository sketcharyRepository) { this.sketcharyRepository = sketcharyRepository; }

    @Override
    public Sketchary getRandomSketchary() {
        return sketcharyRepository.getRandomSketchary();
    }
}
