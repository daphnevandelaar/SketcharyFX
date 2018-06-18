package Logic.SketchyChecker;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CheckAlgorithmTest {

    @Test
    public void checkSketchy() {
        boolean test = CheckAlgorithm.checkSketchy("leeuw", "dit is een leeuw");
        Assert.assertTrue(test);
    }
    @Test
    public void checkSketchyWithTypo(){
        boolean test = CheckAlgorithm.checkSketchy("plant", "dit is een planr");
        Assert.assertTrue(test);
    }
    @Test
    public void checkSketchyNotGuessed() {
        boolean test = CheckAlgorithm.checkSketchy("plant", "dit is een sketch");
        Assert.assertFalse(test);
    }
}