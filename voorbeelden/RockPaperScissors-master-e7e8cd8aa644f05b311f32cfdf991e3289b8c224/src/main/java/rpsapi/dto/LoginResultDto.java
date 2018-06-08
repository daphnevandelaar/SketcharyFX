package rpsapi.dto;

public class LoginResultDto extends BaseResultDto{

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;


}
