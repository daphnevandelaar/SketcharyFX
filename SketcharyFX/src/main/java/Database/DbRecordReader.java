package Database;

import Models.User;

public class DbRecordReader {

    public static User defineUserFromDbRecord(String record){

        User user = new User();
        user.setUsername(record.substring(1, record.indexOf(',')));
        record = record.substring(record.indexOf(',')+1, record.length());

        user.setPassword(record.substring(0, record.indexOf(',')));
        record = record.substring(record.indexOf(',')+1, record.length());

        user.setExpPoints(Integer.parseInt(record.substring(0, record.indexOf(','))));
        record = record.substring(record.indexOf(',')+1, record.length());

        //Hier
        user.setLevel(Integer.parseInt(record.substring(0, record.indexOf(','))));
        record = record.substring(record.indexOf(',')+1, record.length());

        user.setId(Integer.parseInt(record.substring(0, record.length()-1)));

        return user;
    }



}
