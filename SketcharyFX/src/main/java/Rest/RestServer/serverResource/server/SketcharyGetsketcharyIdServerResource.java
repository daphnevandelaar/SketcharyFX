package Rest.RestServer.serverResource.server;

import Factory.SketcharyFactory;
import Logic.ISketcharyLogic;
import Rest.shared.Sketchary;
import Rest.RestServer.serverResource.SketcharyGetsketcharyIdResource;
import org.restlet.data.Reference;
import org.restlet.data.Status;
import org.restlet.resource.ResourceException;

import java.util.logging.Level;

public class SketcharyGetsketcharyIdServerResource extends AbstractServerResource implements SketcharyGetsketcharyIdResource {

    // Define allowed roles for the method "get".
    private static final String[] get5AllowedGroups = new String[] {"anyone"};
    // Define denied roles for the method "get".
    private static final String[] get5DeniedGroups = new String[] {};

    public Sketchary represent() throws Exception {
       Sketchary result = null;
        checkGroups(get5AllowedGroups, get5DeniedGroups);
        

        try {
	    String idPathVariable = Reference.decode(getAttribute("id"));

            Sketchary sketch = new Sketchary();

//	    ISketcharyLogic sketcharyLogic = SketcharyFactory.manageSketchys();
//	    Models.Sketchary msketch = sketcharyLogic.getRandomSketchary();
//	    sketch.setSketchary(msketch.getSketchary());
//	    sketch.setId(Integer.toString(msketch.getId()));
//
//	    System.out.println(sketch.getSketchary());

            sketch.setSketchary("Verkeerde dit is Sketchy met ID");

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

