package Rest.RestClient.clientResource;

import Rest.shared.Sketchary;

public interface SketcharyGetsketcharyIdResource {

    /**
     * search for user with the ID.
     *
     * @return  {@link Sketchary}
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Get
    Sketchary getsasketchary();

}