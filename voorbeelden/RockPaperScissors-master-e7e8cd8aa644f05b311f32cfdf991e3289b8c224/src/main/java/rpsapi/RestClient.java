package rpsapi;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import rpsapi.dto.BaseRequestDto;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


public class RestClient {

    private final String url = "http://localhost:8095/rps";

    private final Gson gson = new Gson();

    private final int NOTDEFINED = -1;

    private  <T> T executeQueryGet(String queryGet, Class<T> clazz) {

        // Build the query for the REST service
        final String query = url + queryGet;
        // Perform the query
        HttpGet httpGet = new HttpGet(query);

        return executeRequest(httpGet, clazz);

    }

    private <T> T executeRequest(HttpUriRequest request, Class<T> clazz)
    {
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request);) {
            HttpEntity entity = response.getEntity();
            final String entityString = EntityUtils.toString(entity);
            T responseObject = gson.fromJson(entityString, clazz);
            return responseObject;
        } catch (IOException | JsonSyntaxException e) {
            return null;
        }
    }

    private <T> T executeQueryPost(BaseRequestDto request, String queryPost , Class<T> clazz) {

        // Build the query for the REST service
        final String query = url + queryPost;

        // Perform the query
        HttpPost httpPost = new HttpPost(query);
        httpPost.addHeader("content-type", "application/json");

        StringEntity params;
        try {
            params = new StringEntity(gson.toJson(request));
            httpPost.setEntity(params);
        } catch (UnsupportedEncodingException ex) {
            //Logger.getLogger(PetStoreClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        return executeRequest(httpPost, clazz);
    }

    private <T> T  executeQueryPut(BaseRequestDto petRequest, String queryPut , Class<T> clazz) {

        // Build the query for the REST service
        final String query = url + queryPut;

        // Perform the query
        HttpPut httpPut = new HttpPut(query);
        httpPut.addHeader("content-type", "application/json");
        StringEntity params;
        try {
            params = new StringEntity(gson.toJson(petRequest));
            httpPut.setEntity(params);
        } catch (UnsupportedEncodingException ex) {
            //Logger.getLogger(PetStoreClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        return executeRequest(httpPut, clazz);
    }

    private <T> T executeQueryDelete(String queryDelete, Class<T> clazz) {

        // Build the query for the REST service
        final String query = url + queryDelete;

        // Perform the query
        HttpDelete httpDelete = new HttpDelete(query);

        return executeRequest(httpDelete, clazz);
    }

}
