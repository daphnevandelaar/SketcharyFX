package Rest.RestServer.serverResource;

import Rest.shared.Sketchary;
import org.restlet.resource.Get;

public interface SketcharyGetsketcharyIdResource {

    @Get
    Sketchary represent() throws Exception;

}

