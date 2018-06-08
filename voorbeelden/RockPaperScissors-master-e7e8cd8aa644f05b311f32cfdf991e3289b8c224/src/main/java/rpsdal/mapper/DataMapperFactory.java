package rpsdal.mapper;

import model.*;

public class DataMapperFactory {

    public static BaseDataMapper getMapper(Class entityType)
    {
        if(entityType.equals(Player.class))
           return new PlayerDataMapper();
        else if(entityType.equals(RoundResult.class))
            return new RoundResultDataMapper();
        else if(entityType.equals(Token.class))
            return new TokenDataMapper();
        return null;
    }
}
