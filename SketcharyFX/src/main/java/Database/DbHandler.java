package Database;

import java.sql.*;

public class DbHandler {
    private String conString = "jdbc:postgresql://localhost:5432/SketcharyDB";
    private String dbUserString = "postgres";
    private String dbPassString = "1234";
    private Connection conn;
    private ResultSetMetaData rsmd;
    private ResultSet rs;

    protected ResultSet getDataByView(String query){
        try
        {
            Class.forName("org.postgresql.Driver").newInstance();
            conn = DriverManager.getConnection(conString, dbUserString, dbPassString);

            Statement st = conn.createStatement();
            rs = st.executeQuery(query);

        }
        catch (ClassNotFoundException ex) {System.err.println(ex.getMessage());}
        catch (IllegalAccessException ex) {System.err.println(ex.getMessage());}
        catch (InstantiationException ex) {System.err.println(ex.getMessage());}
        catch (SQLException ex)           {System.err.println(ex.getMessage());}
        finally {
            try {
                //conn.close();
            }
            catch (Exception sqlEx) {
                //System.err.println(sqlEx.getErrorCode());
                System.err.println(sqlEx.getMessage());
            }
        }

        return rs;
    }

    protected void getTableValues(){
        try{
//            int columnCount = rsmd.getColumnCount();
//
//            System.out.println(rsmd.getColumnCount());
//            Object[] row = new Object[columnCount];
//            for (int i = 1; i <= columnCount+1; ++i) {
//                row[i - 1] = rs.getString(i); // Or even rs.getObject()
//                System.out.println(rs.getObject(i));

            while(rs.next()){
                System.out.println("----");
                System.out.println(rs.getObject(1));
                System.out.println("----");
                System.out.println(rs.getObject(2));
                System.out.println("----");
                System.out.println(rs.getObject(3));
            }

        }
        catch (SQLException ex){

        }

    }
}
