package databse;

import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PartService extends Wszystko {


    private Connection connection;
    private Statement statement;
    private final String PARTID = "partId";
    private final String CAtEGORY_ID = "categoryId";
    private final String WARSZATT_ID = "warsztatId";
    private final String PRODECENT = "producent";
    private final String MODEL = "model";
    private final String PÓLKA = "półka";
    private final String MIEJSCE = "miejsce";
    private final String RZAD = "rząd";
    private final String DOT = "DOT";
    private final String BIEZNIK = "bieznik";

    public ObservableList<Part> getAllParts(){
        List<Part> parts = new ArrayList();
        ObservableList<Part> party = FXCollections.observableList(parts);
        try {
            Class.forName(DatabseConnection.getDBDRIVER()).newInstance();
            connection = DriverManager.getConnection(DatabseConnection.getURL(),
                    DatabseConnection.getUSER(),
                    DatabseConnection.getPASSWORD());
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("Select * FROM Part");


            while (result.next()) {
                parts.add(new Part(result.getLong(PARTID),
                        result.getLong(CAtEGORY_ID),
                        result.getLong(WARSZATT_ID),
                        result.getString(PRODECENT),
                        result.getString(MODEL),
                        result.getInt(PÓLKA),
                        result.getInt(MIEJSCE),
                        result.getInt(RZAD),
                        result.getString(DOT),
                        result.getString(BIEZNIK),
                        1L));
            }

            for (int i = 0; i < parts.size(); i++) {
                parts.get(i).setIdKolumny((long) i + 1);
            }
        }

        catch(Exception e){
                e.printStackTrace();
            }

        return party;

    }

    public List<Part> getPartByCategory(PartCategory partCategory){
        List<Part> parts = new ArrayList();
        ObservableList<Part> party = FXCollections.observableList(parts);

        try {
            Class.forName(DatabseConnection.getDBDRIVER()).newInstance();
            connection = DriverManager.getConnection(DatabseConnection.getURL(),
                    DatabseConnection.getUSER(),
                    DatabseConnection.getPASSWORD());
            statement = connection.createStatement();

            String query = String.format("Select * From Part Where partId = %d",partCategory.getId() );
            ResultSet result = statement.executeQuery(query);

            while (result.next()){
                parts.add( new Part(result.getLong(PARTID),
                        result.getLong(CAtEGORY_ID),
                        result.getLong(WARSZATT_ID),
                        result.getString(PRODECENT),
                        result.getString(MODEL),
                        result.getInt(PÓLKA),
                        result.getInt(MIEJSCE),
                        result.getInt(RZAD),
                        result.getString(DOT),
                        result.getString(BIEZNIK), 1L));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        for(int i=0; i < parts.size(); i++){
            parts.get(i).setIdKolumny((long) i + 1);
        }
        return party;
    }

    public List<Part> getPartsByProducent(String producent){
        List<Part> parts = new ArrayList();
        ObservableList<Part> party = FXCollections.observableList(parts);
        try {
            Class.forName(DatabseConnection.getDBDRIVER()).newInstance();
            connection = DriverManager.getConnection(DatabseConnection.getURL(),
                    DatabseConnection.getUSER(),
                    DatabseConnection.getPASSWORD());
            statement = connection.createStatement();

            String query = String.format("Select * From Part Where producent = %s",producent );
            ResultSet result = statement.executeQuery(query);

            while (result.next()){
                parts.add( new Part(result.getLong(PARTID),
                        result.getLong(CAtEGORY_ID),
                        result.getLong(WARSZATT_ID),
                        result.getString(PRODECENT),
                        result.getString(MODEL),
                        result.getInt(PÓLKA),
                        result.getInt(MIEJSCE),
                        result.getInt(RZAD),
                        result.getString(DOT),
                        result.getString(BIEZNIK),
                        1L));
            }

            for(int i=0; i < parts.size(); i++){
                parts.get(i).setIdKolumny((long) i + 1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return party;
    }

    public List<Part> getPartsByModel(String model){
        List<Part> parts = new ArrayList();
        ObservableList<Part> party = FXCollections.observableList(parts);
        try {
            Class.forName(DatabseConnection.getDBDRIVER()).newInstance();
            connection = DriverManager.getConnection(DatabseConnection.getURL(),
                    DatabseConnection.getUSER(),
                    DatabseConnection.getPASSWORD());
            statement = connection.createStatement();

            String query = String.format("Select * From Part Where model = %s", model );
            ResultSet result = statement.executeQuery(query);

            while (result.next()){
                parts.add( new Part(result.getLong(PARTID),
                        result.getLong(CAtEGORY_ID),
                        result.getLong(WARSZATT_ID),
                        result.getString(PRODECENT),
                        result.getString(MODEL),
                        result.getInt(PÓLKA),
                        result.getInt(MIEJSCE),
                        result.getInt(RZAD),
                        result.getString(DOT),
                        result.getString(BIEZNIK),
                        1L));
            }

            for(int i=0; i < parts.size(); i++){
                parts.get(i).setIdKolumny((long) i + 1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return party;
    }

    public List<Part> getPartsByWarsztat(Long idWarszatu){
        List<Part> parts = new ArrayList();
        ObservableList<Part> party = FXCollections.observableList(parts);
        try {
            Class.forName(DatabseConnection.getDBDRIVER()).newInstance();
            connection = DriverManager.getConnection(DatabseConnection.getURL(),
                    DatabseConnection.getUSER(),
                    DatabseConnection.getPASSWORD());
            statement = connection.createStatement();

            String query = String.format("Select * From Part Where warsztatId = %d", idWarszatu);

            ResultSet result = statement.executeQuery(query);

            while (result.next()){
                parts.add( new Part(result.getLong(PARTID),
                        result.getLong(CAtEGORY_ID),
                        result.getLong(WARSZATT_ID),
                        result.getString(PRODECENT),
                        result.getString(MODEL),
                        result.getInt(PÓLKA),
                        result.getInt(MIEJSCE),
                        result.getInt(RZAD),
                        result.getString(DOT),
                        result.getString(BIEZNIK),
                        1L));
            }
            for(int i=0; i < parts.size(); i++){
                parts.get(i).setIdKolumny((long) i + 1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return party;
    }

    public void addPart(Part part){
        try {
            Class.forName(DatabseConnection.getDBDRIVER()).newInstance();
            connection = DriverManager.getConnection(DatabseConnection.getURL(),
                    DatabseConnection.getUSER(),
                    DatabseConnection.getPASSWORD());
            statement = connection.createStatement();


            String insert = String.format("Insert into Part (%s, %s, %s, %s, %s,%s,%s, %s, %s) " +
                    "                       VALUES (%d, %d, '%s','%s', %d,%d,%d, '%s', '%s') ",
                                            CAtEGORY_ID, WARSZATT_ID, PRODECENT, MODEL, PÓLKA, MIEJSCE, RZAD, DOT, BIEZNIK,
                                            part.getCategoryID(), part.getWarsztatId(), part.getProducent(),part.getModel(),
                                            part.getPólka(), part.getMiejsce(), part.getRząd(), part.getDOT(), part.getBieznik() );
            System.out.println(insert);

            statement.execute(insert);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletePart(Part part){
        try {
            Class.forName(DatabseConnection.getDBDRIVER()).newInstance();
            connection = DriverManager.getConnection(DatabseConnection.getURL(),
                    DatabseConnection.getUSER(),
                    DatabseConnection.getPASSWORD());
            statement = connection.createStatement();

            String deleteQuery = String.format("Delete FROM Part Where partId = %d", part.getId());
            statement.execute(deleteQuery);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
