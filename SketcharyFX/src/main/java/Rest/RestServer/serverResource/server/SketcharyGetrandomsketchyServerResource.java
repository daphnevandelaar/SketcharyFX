package Rest.RestServer.serverResource.server;

import Factory.SketcharyFactory;
import Logic.ISketcharyLogic;
import Rest.RestServer.serverResource.SketcharyGetrandomsketchyResource;
import Rest.shared.Sketchary;
import org.restlet.data.Status;
import org.restlet.resource.ResourceException;

import java.util.logging.Level;

public class SketcharyGetrandomsketchyServerResource extends AbstractServerResource implements SketcharyGetrandomsketchyResource {

    // Define allowed roles for the method "get".
    private static final String[] get6AllowedGroups = new String[] {"anyone"};
    // Define denied roles for the method "get".
    private static final String[] get6DeniedGroups = new String[] {};

    public Sketchary represent() throws Exception {
       Sketchary result = null;
        checkGroups(get6AllowedGroups, get6DeniedGroups);
        

        try {

            Sketchary sketch = new Sketchary();

            //TODO: get from database
//            ISketcharyLogic sketcharyLogic = SketcharyFactory.manageSketchys();
//            Models.Sketchary msketch = sketcharyLogic.getRandomSketchary();
//            sketch.setSketchary(msketch.getSketchary());
//            sketch.setId(Integer.toString(msketch.getId()));

            sketch.setSketchary("leeuw");
            sketch.setId("1");

            result = sketch;


	    // Initialize here your bean
         } catch (Exception ex) {
            // In a real code, customize handling for each type of exception
            getLogger().log(Level.WARNING, "Error when executing the method", ex);
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL,
                    ex.getMessage(), ex);
        }
    
        return result;
    }


}

