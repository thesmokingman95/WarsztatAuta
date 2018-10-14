package databse;

import sample.Interface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabseConnection {

    private static final String URL = "jdbc:mysql://51.38.131.17:3306/Warsztat?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static String USER = "user1";
    private static String PASSWORD = "user1";
    private static String DBDRIVER = "com.mysql.cj.jdbc.Driver";

    private Connection connection;
    private Statement statement;

    public void connectToDatabase(){
        try {
            Class.forName(DatabseConnection.getDBDRIVER()).newInstance();
            connection = DriverManager.getConnection(DatabseConnection.getURL(),
                    DatabseConnection.getUSER(),
                    DatabseConnection.getPASSWORD());
            statement = connection.createStatement();
        } catch (SQLException exc) {
            exc.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String getURL() {
        return URL;
    }

    public static String getUSER() {
        return USER;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }

    public static String getDBDRIVER() {
        return DBDRIVER;
    }
}
