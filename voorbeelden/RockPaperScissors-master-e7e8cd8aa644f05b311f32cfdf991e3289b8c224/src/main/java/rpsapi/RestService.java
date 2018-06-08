package rpsapi;

import rpsapi.dto.LoginDto;
import rpsdal.SqlDataContext;
import rpsdal.repositories.IPlayerRepository;
import rpsdal.repositories.ITokenRepository;
import rpsdal.repositories.PlayerRepository;
import rpsdal.repositories.TokenRepository;
import rpsshared.interfaces.IDataContext;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/rps")
public class RestService {

    private IPlayerRepository playerRepository;
    private IDataContext dataContext;
    private ITokenRepository tokenRepository;

    public RestService()
    {
        dataContext = new SqlDataContext();
        playerRepository = new PlayerRepository(dataContext);
        tokenRepository = new TokenRepository(dataContext);
    }

    @POST
    @Path("/login")
    @Consumes("application/json")
    @Produces("application/json")
    public Response login(LoginDto loginRequest)
    {
        if(loginRequest == null)
        {
            return Response.status(400).entity(BaseResponseHelper.getErrorResponseString()).build();
        }

        String token = playerRepository.login(tokenRepository, loginRequest.getUserName(), loginRequest.getHashedPassword());
        if(token.equals(""))
        {
            //TODO log failed login requests
            return Response.status(401).entity(BaseResponseHelper.getErrorResponseString()).build();
        }

        return  Response.status(200).entity(BaseResponseHelper.getLoginResultDtoResponseString(token)).build();

    }
}
