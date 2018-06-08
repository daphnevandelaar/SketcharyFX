package rpsdal.mapper;

import model.Token;

import java.sql.ResultSet;
import java.util.List;

public class TokenDataMapper<T> extends BaseDataMapper<Token> {

    @Override
    public String mapToSqlInternal(Token object) {
        if(object.getEntityId() == 0)
        {
            //new
            return "insert into [tokens] (TokenText, CreationDate, TTL, PlayerId) "
            + "values (' " + object.getTokenText() + "' , '" + object.getCreationDate() + "' , '" + object.getTTL() + "' , '" + object.getPlayerId() +"')";
        }
        else
        {
            //update
            return "update [Tokens] set TokenText=' " + object.getTokenText() + "', TTL='" + object.getTTL() +"' where Id=" + object.getEntityId();
        }
    }

    @Override
    public List<Token> mapFromDatabaseInternal(ResultSet rs) {
        return null;
    }
}
