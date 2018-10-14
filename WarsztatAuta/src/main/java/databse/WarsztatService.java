package databse;

import entity.Auto;
import entity.Warsztat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WarsztatService {

    private Connection connection;
    private Statement statement;
    private Warsztat warsztat;
    private ResultSet result;

    private final String ID = "warsztatId";
    private final String ULICA = "ulica";
    private  final String MIEJSCOWOSC = "miejscowosc";

    public ObservableList<Warsztat> getAllWarsztat(){
        List<Warsztat> warsztats = new ArrayList();
        ObservableList<Warsztat> warsztaty = FXCollections.observableArrayList(warsztats);
        try {
            Class.forName(DatabseConnection.getDBDRIVER()).newInstance();
            connection = DriverManager.getConnection(DatabseConnection.getURL(),
                    DatabseConnection.getUSER(),
                    DatabseConnection.getPASSWORD());
            statement = connection.createStatement();
            result = statement.executeQuery("Select * FROM Warsztat");


            while (result.next()){
                warsztaty.add( new Warsztat(result.getLong(ID),
                        result.getString(ULICA),
                        result.getString(MIEJSCOWOSC),
                        1L));
            }
            for(int i=0; i < warsztaty.size(); i++){
                warsztaty.get(i).setIdKolumny((long) i + 1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return warsztaty;
    }

    public void addWarsztat(Warsztat warsztat){
        try {
            Class.forName(DatabseConnection.getDBDRIVER()).newInstance();
            connection = DriverManager.getConnection(DatabseConnection.getURL(),
                    DatabseConnection.getUSER(),
                    DatabseConnection.getPASSWORD());
            statement = connection.createStatement();


            String insert = String.format("Insert into Warsztat (%s, %s, %s) " +
                            "                       VALUES (%d, '%s', '%s')",
                    ID, ULICA, MIEJSCOWOSC,
                    warsztat.getId(), warsztat.getUlica(), warsztat.getMiejscowosc() );
            System.out.println(insert);

            statement.execute(insert);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteWarsztat(Warsztat warsztat){
        try {
            Class.forName(DatabseConnection.getDBDRIVER()).newInstance();
            connection = DriverManager.getConnection(DatabseConnection.getURL(),
                    DatabseConnection.getUSER(),
                    DatabseConnection.getPASSWORD());
            statement = connection.createStatement();

            String deleteQuery = String.format("Delete FROM Warsztat Where warsztatId = %d", warsztat.getId());
            statement.execute(deleteQuery);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long getId() throws SQLException {
        return result.getLong(ID);
    }
}
