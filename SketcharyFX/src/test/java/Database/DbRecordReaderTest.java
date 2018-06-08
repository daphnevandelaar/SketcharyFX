package Database;

import Models.User;
import org.junit.Assert;

import static org.junit.Assert.*;

public class DbRecordReaderTest {

    @org.junit.Test
    public void defineUserFromDbRecord() {
        User user = new User(2, "blacky", "123", 4, 115);

        User user2 = DbRecordReader.defineUserFromDbRecord("(blacky,123,115,4,2)");
        Assert.assertEquals(user.getUsername(), user2.getUsername());
        Assert.assertEquals(user.getId(), user2.getId());
        Assert.assertEquals(user.getExpPoints(), user2.getExpPoints());
        Assert.assertEquals(user.getLevel(), user2.getLevel());
        Assert.assertEquals(user.getPassword(), user2.getPassword());
    }
}