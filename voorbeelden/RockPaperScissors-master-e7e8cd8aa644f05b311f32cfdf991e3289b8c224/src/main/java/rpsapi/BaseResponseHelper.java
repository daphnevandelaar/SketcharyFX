package rpsapi;

import com.google.gson.Gson;
import rpsapi.dto.BaseResultDto;
import rpsapi.dto.LoginResultDto;

public class BaseResponseHelper {

    private static final Gson gson = new Gson();

    public static String getErrorResponseString()
    {
        BaseResultDto response = new BaseResultDto();
        response.setSuccess(false);
        String output = gson.toJson(response);
        return output;
    }

    public static String getLoginResultDtoResponseString(String token)
    {
        LoginResultDto response = new LoginResultDto();
        response.setSuccess(true);
        response.setToken(token);
        String output = gson.toJson(response);
        return output;
    }

}
