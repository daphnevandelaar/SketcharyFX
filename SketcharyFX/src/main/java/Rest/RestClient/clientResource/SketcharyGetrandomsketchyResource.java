package Rest.RestClient.clientResource;

import Rest.shared.Sketchary;

public interface SketcharyGetrandomsketchyResource {

    /**
     * gets random sketchy out db.
     *
     * @return  {@link Sketchary}
     * @throws org.restlet.resource.ResourceException if the call to the API fails
     */
    @org.restlet.resource.Get
    Sketchary getSketcharyGetrandomsketchy();

}