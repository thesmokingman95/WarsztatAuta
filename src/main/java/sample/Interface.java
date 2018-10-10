package sample;

import databse.AutoService;
import databse.DatabseConnection;
import databse.PartService;
import databse.WarsztatService;
import entity.Auto;
import entity.Part;
import entity.Warsztat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

;

public class Interface {

    DatabseConnection db = new DatabseConnection();



    @FXML //panele okien
    private AnchorPane anchor, anchorAdd, anchorAddingPart, anchorWarsztat1, anchorWarsztat2, mainView;

    @FXML //combobox wyboru itemu
    private ComboBox<String> cSelectItem; //bedzie sparametryzowany inaczej - wczytanie z bazy warsztatow

    @FXML //combobox wyboru lokalizacji
    private ComboBox<String> cSelectLocation;

    @FXML //przycik zaloguj
    private Button bLogin;

    @FXML
    private Button bDodaj;

    @FXML
    private Button bUsun;

    @FXML
    private Button bSzukaj;

    @FXML
    private CheckBox chCars;

    @FXML
    private CheckBox chParts;

    @FXML
    private CheckBox chWarsztats;

    @FXML //przycik OK w oknie dodawania itemu
    private Button bAddOK;

    @FXML //przycisk dodaj do bazy
    private Button bAdd;

    @FXML //przycisk usun z bazy
    private Button bRemove;

    @FXML //przycisk szukaj w bazie
    private Button bSearch;

    @FXML //pole wpisania loginu
    private TextField tUsername;

    @FXML //pole wpisania hasla
    private PasswordField tPassword;

    @FXML //etykieta wyswietlajaca status polaczenia
    private Label lconnectionStatus;

    @FXML //checkbox ustalajacy haslo i login na default
    private CheckBox checkDefault;

    @FXML
    private TableView<Auto> tableContentCar;
    @FXML
    private TableView<Warsztat> tableContentWarsztat;
    @FXML
    private TableView<Part> tableContentPart;

    @FXML
    private ComboBox<String> cbWybierzWarsztat;

    @FXML
    private TextField tPodajMarke;

    @FXML
    private TextField tPodajModel;

    @FXML
    private TextField tPodajNrRej;

    @FXML
    private Button bAnulujDodanie;

    @FXML
    private Button bDodajSamochod;

    @FXML
    private TextField tNazwaProducenta;

    @FXML
    private TextField tNazwaModelu;

    @FXML
    private TextField tRodzajBieznika;

    @FXML
    private TextField tDataOT;

    @FXML
    private TextField tRząd;

    @FXML
    private TextField tPolka;

    @FXML
    private TextField tMiejsce;

    @FXML
    private Button bAnulujCzesc;

    @FXML
    private Button bDodajCzesc;

    @FXML
    private TableView<Auto> carViewWarsztat1, carViewWarsztat2;

    @FXML
    private TableView<Part> partViewWarsztat1, partViewWarsztat2;

    @FXML
    private ComboBox<String> cbLokalizacjaCzesci;

    @FXML
    private Button bDodajDoBazy;



    @FXML //logowanie sie do bazy - JESZCZE NIE DZIALA - LOGOWANIE W KLASIE
    public void loginToDatabase() {

        if (!tUsername.getText().isEmpty() && !tPassword.getText().isEmpty()) {

            db.connectToDatabase();
            lconnectionStatus.setTextFill(Color.GREEN);
            lconnectionStatus.setText("Status: polaczono");

            try {
                Parent root = FXMLLoader.load(getClass().getResource("/content.fxml"));
                Stage contentStage = new Stage();
                Rectangle2D rect = Screen.getPrimary().getVisualBounds();
                contentStage.setTitle("Content Pane");
                contentStage.setScene(new Scene(root));
                contentStage.setX(rect.getWidth() / 2 - ((rect.getWidth()) / 3));
                contentStage.setY(rect.getHeight() / 2 - (rect.getHeight() / 4));
                contentStage.requestFocus();
                contentStage.show();


//                WarsztatService warsztatService = new WarsztatService();
//                Warsztat warsztat = new Warsztat();
//                warsztat.setId(3L);
//                warsztat.setMiejscowosc("Warszawa");
//                warsztat.setUlica("Radiowa 16");
//                warsztatService.addWarsztat(warsztat);

            } catch (IOException exc) {
                Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, "Wyjebalo okno");
                exc.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Bląd!");
            alert.setHeaderText("Źle wprowadzone dane logowania!");
            alert.setContentText("Nazwa użytkownika i haslo nie mogą byc puste");
            alert.showAndWait();
        }
    }

    @FXML //dzialanie przycisku dodaj w oknie contentowym
    public void addToDatabase() throws IOException {
        if (chCars.isSelected()) { //tworzenie okna dodawania samochodu do bazy
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/addCar.fxml"));
                Stage carStage = new Stage();
                Rectangle2D rect = Screen.getPrimary().getVisualBounds();
                carStage.setTitle("Dodaj samochod do bazy");
                carStage.setScene(new Scene(root));
                carStage.setX(rect.getWidth() / 2);
                carStage.setY(rect.getHeight() / 2 - (rect.getHeight() / 4));
                carStage.requestFocus();
                carStage.show();

            } catch (IOException exc) {
                Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, "Wyjebalo okno");
                exc.printStackTrace();
            }
        } else if (chParts.isSelected()) { //tworzenie okna dodawania czesci do bazy
            Parent root = FXMLLoader.load(getClass().getResource("/addPart.fxml"));
            Stage partStage = new Stage();
            Rectangle2D rect = Screen.getPrimary().getVisualBounds();
            partStage.setTitle("Dodaj oponę do bazy");
            partStage.setScene(new Scene(root));
            partStage.setX(rect.getWidth() / 2);
            partStage.setY(rect.getHeight() / 2 - (rect.getHeight() / 4));
            partStage.requestFocus();
            partStage.show();
        } else if (chWarsztats.isSelected()) { //dodanie warsztatow - będzie na szrywno i STRACI FUNKCJONALNOSCI

        }
    }

    @FXML
    public void initialize() {


    }
    @FXML //dodanie widoku zawartosci warsztatow
    public void openWarsztatView() throws IOException {
        if (chWarsztats.isSelected()) {
            int ktoryWarsztat = tableContentWarsztat.getSelectionModel().getSelectedIndex();
            if (ktoryWarsztat == 0) {//widok warsztatu nr 1
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/WarsztatView1.fxml"));
                    Stage warsztatStage = new Stage();
                    Rectangle2D rect = Screen.getPrimary().getVisualBounds();
                    warsztatStage.setTitle("Widok warsztatu 1");
                    warsztatStage.setScene(new Scene(root));
                    warsztatStage.setX(rect.getWidth() / 2);
                    warsztatStage.setY(rect.getHeight() / 2 - (rect.getHeight() / 4));
                    warsztatStage.requestFocus();
                    warsztatStage.show();
                } catch (IOException exc) {
                    Logger logger = Logger.getLogger(getClass().getName());
                    logger.log(Level.SEVERE, "Wyjebalo okno");
                    exc.printStackTrace();
                }

            } else if (ktoryWarsztat == 1) { //widok warsztatu nr 2
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/WarsztatView2.fxml"));
                    Stage warsztatStage = new Stage();
                    Rectangle2D rect = Screen.getPrimary().getVisualBounds();
                    warsztatStage.setTitle("Widok warsztatu 2");
                    warsztatStage.setScene(new Scene(root));
                    warsztatStage.setX(rect.getWidth() / 2);
                    warsztatStage.setY(rect.getHeight() / 2 - (rect.getHeight() / 4));
                    warsztatStage.requestFocus();
                    warsztatStage.show();
                } catch (Exception exc) {
                    Logger logger = Logger.getLogger(getClass().getName());
                    logger.log(Level.SEVERE, "Wyjebalo okno");
                    exc.printStackTrace();
                }
//            } else if (chParts.isSelected()) {
//                if (tableContent.getSelectionModel().getSelectedIndex() != -1) {
//                    PartService partService = new PartService();
//                    ObservableList<Part> party = partService.getAllParts();
//                    Part part = new Part();
//                    Part wybrany = party.get(tableContent.getSelectionModel().getSelectedIndex());
//                    part.setId(wybrany.getId());
//                    partService.deletePart(part);
//                }
                //}
            }
        }
    }

    //    @FXML //anuluj dodanie czesci - DO PRZEBUDOWY
//    public void cancelAddingPart() {
//        Stage stage = (Stage) anchorAddingPart.getScene().getWindow();
//        stage.close();
//        if (result != null) {
//            try {
//                result.close();
//            } catch (SQLException e) {
//                System.out.println("rozlaczono");
//            }
//        }
//        if (statement != null) {
//            try {
//                statement.close();
//            } catch (SQLException e) {
//                System.out.println("rozlaczono");
//            }
//        }
//        if (connection != null) {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                System.out.println("rozlaczono");
//            }
//        }
//
//      @FXML   //jak powyzej
//    public void cancelAdding() {
//        Stage stage = (Stage) anchorAdd.getScene().getWindow();
//        stage.close();
//        if (result != null) {
//            try {
//                result.close();
//            } catch (SQLException e) {
//                System.out.println("rozlaczono");
//            }
//        }
//        if (statement != null) {
//            try {
//                statement.close();
//            } catch (SQLException e) {
//                System.out.println("rozlaczono");
//            }
//        }
//        if (connection != null) {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                System.out.println("rozlaczono");
//            }
//        }
//    }
//
    @FXML //przycik OK zatwierdzajacy dodawanie do bazy samochodu
    public void confirmAddingCar() {
        Auto dodawanySamochod = new Auto();

        if (!tPodajMarke.getText().isEmpty() && !tPodajModel.getText().isEmpty() && !tPodajNrRej.getText().isEmpty()) {
            ObservableList<Auto> listaSamochodow = new AutoService().getAllCars();
            System.out.println(listaSamochodow.size());
            dodawanySamochod.setMarka(tPodajMarke.getText());
            dodawanySamochod.setModel(tPodajModel.getText());
            dodawanySamochod.setNrRej(tPodajNrRej.getText());
            dodawanySamochod.setId(5L);

            String opcjaComboBoxa = cbWybierzWarsztat.getSelectionModel().getSelectedItem().toString();
            if (opcjaComboBoxa.equals("Warszawa, Orlich Gniazd 2"))
                dodawanySamochod.setWarsztatId(1L);
            else if (opcjaComboBoxa.equals("Warszawa, Radiowa 16"))
                dodawanySamochod.setWarsztatId(3L);
            new AutoService().addAuto(dodawanySamochod);


            Stage stage = (Stage) anchorAdd.getScene().getWindow();
            stage.close();



        } else {
            System.err.println("Wyjebalo w kosmos");
        }
    }

    @FXML  ///przycik OK zatwierdzajacy dodawanie do bazy czesci
    public void confirmAddingPart() {
        Part dodawanaCzesc = new Part();
        if (!tNazwaProducenta.getText().isEmpty() && !tNazwaModelu.getText().isEmpty() && !tRodzajBieznika.getText().isEmpty() && !tDataOT.getText().isEmpty()
                && !tRząd.getText().isEmpty() && !tPolka.getText().isEmpty() && !tMiejsce.getText().isEmpty() && isNumeric(tRząd.getText()) && isNumeric(tPolka.getText()) && isNumeric(tMiejsce.getText())) {

            dodawanaCzesc.setProducent(tNazwaProducenta.getText());
            dodawanaCzesc.setModel(tNazwaModelu.getText());
            dodawanaCzesc.setBieznik(tRodzajBieznika.getText());
            dodawanaCzesc.setDOT(tDataOT.getText());
            dodawanaCzesc.setRząd(Integer.parseInt(tRząd.getText()));
            dodawanaCzesc.setPólka(Integer.parseInt(tPolka.getText()));
            dodawanaCzesc.setMiejsce(Integer.parseInt(tMiejsce.getText()));
            dodawanaCzesc.setCategoryID(1L);
            String opcjaComboBoxa = cbLokalizacjaCzesci.getSelectionModel().getSelectedItem().toString();
            if (opcjaComboBoxa.equals("Warszawa, Orlich Gniazd 2"))
                dodawanaCzesc.setWarsztatId(1L);
            else if (opcjaComboBoxa.equals("Warszawa, Radiowa 16"))
                dodawanaCzesc.setWarsztatId(3L);

            new PartService().addPart(dodawanaCzesc);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            if (!isNumeric(tRząd.getText())) {
                alert.setTitle("Bląd!");
                alert.setHeaderText("Numer rzędu musi byc liczba calkowita!");
                alert.setContentText("Podaj liczbe calkowita: np. 2, 8, 34");
                alert.showAndWait();
            } else if (!isNumeric(tPolka.getText())) {
                alert.setTitle("Bląd!");
                alert.setHeaderText("Numer polki musi byc liczba calkowita!");
                alert.setContentText("Podaj liczbe calkowita: np. 2, 8, 34");
                alert.showAndWait();
            } else if (!isNumeric(tMiejsce.getText())) {
                alert.setTitle("Bląd!");
                alert.setHeaderText("Numer miejca musi byc liczba calkowita!");
                alert.setContentText("Podaj liczbe calkowita: np. 2, 8, 34");
                alert.showAndWait();
            }
        }
    }

    @FXML //sprawdzenie stanu checkboxa - logowanie domyslne - jeszcze nie dziala
    public void isDefaultSelected() {
        if (checkDefault.isSelected()) {
            tUsername.setText("user1");
            tPassword.setText("user1");
        }
    }

    @FXML //zamkniecie okna glownego - przycisk cancel - do PRZEBUDOWY
    public void closeWindow() {
        Stage stage = (Stage) anchor.getScene().getWindow();
        stage.close();
//        if (result != null) {
//            try {
//                result.close();
//            } catch (SQLException e) {
//                System.out.println("rozlaczono");
//            }
//        }
//        if (statement != null) {
//            try {
//                statement.close();
//            } catch (SQLException e) {
//                System.out.println("rozlaczono");
//            }
//        }
//        if (connection != null) {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                System.out.println("rozlaczono");
//            }
//        }
    }

    @FXML //wyswietlanie widoku samochodow w TableView
    public void isCarsSelected() {

        ObservableList<Auto> listaSamochodow = new AutoService().getAllCars();

        if (chCars.isSelected()) {
            chWarsztats.setSelected(false);
            chParts.setSelected(false);
              tableContentCar.setItems(listaSamochodow);


            tableContentPart.setVisible(false);
            tableContentWarsztat.setVisible(false);
            tableContentCar.setVisible(true);

            TableColumn<Auto, Long> id = new TableColumn<Auto, Long>("ID");
            id.setCellValueFactory(new PropertyValueFactory<Auto, Long>("idTabeli"));

            TableColumn<Auto, Long> warsztatId = new TableColumn<Auto, Long>("ID Warsztatu");
            warsztatId.setCellValueFactory(new PropertyValueFactory<Auto, Long>("warsztatId"));

            TableColumn<Auto, String> marka = new TableColumn<Auto, String>("Marka");
            marka.setCellValueFactory(new PropertyValueFactory<Auto, String>("marka"));

            TableColumn<Auto, String> model = new TableColumn<Auto, String>("Model");
            model.setCellValueFactory(new PropertyValueFactory<Auto, String>("model"));

            TableColumn<Auto, String> nrRej = new TableColumn<Auto, String>("Nr tablicy");
            nrRej.setCellValueFactory(new PropertyValueFactory<Auto, String>("nrRej"));

            tableContentCar.getColumns().setAll(id, warsztatId, marka, model, nrRej);

        } else {
//            for (int i = 0; i < 5; i++)
//                tableContentCar.getColumns().get(i).setVisible(false);
        }
    }

    @FXML  //wyswiertlanie widoku czesci w TableView
    public void isPartsSelected() {
        if (chParts.isSelected()) {
            chCars.setSelected(false);
            chWarsztats.setSelected(false);
            ObservableList<Part> listaCzesci = new PartService().getAllParts();

            tableContentCar.setVisible(false);
            tableContentPart.setVisible(true);
            tableContentWarsztat.setVisible(false);

            tableContentPart.setItems(listaCzesci);

            TableColumn<Part, Long> id = new TableColumn<Part, Long>("ID");
            id.setCellValueFactory(new PropertyValueFactory<Part, Long>("idKolumny"));

            TableColumn<Part, Long> categoryID = new TableColumn<Part, Long>("Category ID");
            categoryID.setCellValueFactory(new PropertyValueFactory<Part, Long>("categoryID"));

            TableColumn<Part, Long> warsztatId = new TableColumn<Part, Long>("warsztat ID");
            warsztatId.setCellValueFactory(new PropertyValueFactory<Part, Long>("warsztatId"));

            TableColumn<Part, String> producent = new TableColumn<Part, String>("Producent");
            producent.setCellValueFactory(new PropertyValueFactory<Part, String>("producent"));

            TableColumn<Part, String> model = new TableColumn<Part, String>("Model");
            model.setCellValueFactory(new PropertyValueFactory<Part, String>("model"));

            TableColumn<Part, String> DOT = new TableColumn<Part, String>("DOT");
            DOT.setCellValueFactory(new PropertyValueFactory<Part, String>("DOT"));

            TableColumn<Part, String> bieznik = new TableColumn<Part, String>("Bieznik");
            bieznik.setCellValueFactory(new PropertyValueFactory<Part, String>("bieznik"));

            TableColumn<Part, Integer> półka = new TableColumn<Part, Integer>("Półka");
            półka.setCellValueFactory(new PropertyValueFactory<Part, Integer>("pólka"));

            TableColumn<Part, Integer> miejsce = new TableColumn<Part, Integer>("Miejsce");
            miejsce.setCellValueFactory(new PropertyValueFactory<Part, Integer>("miejsce"));

            TableColumn<Part, Integer> rząd = new TableColumn<Part, Integer>("Rząd");
            rząd.setCellValueFactory(new PropertyValueFactory<Part, Integer>("rząd"));

            tableContentPart.getColumns().setAll(id, categoryID, warsztatId, producent, model, półka, miejsce, rząd, DOT, bieznik);
        }
    }

    @FXML
    public void isWarsztatsSelected() { //wyswietlanie widoku warsztatow w TableView
        if (chWarsztats.isSelected()) {
            chParts.setSelected(false);
            chCars.setSelected(false);

            tableContentCar.setVisible(false);
            tableContentPart.setVisible(false);
            tableContentWarsztat.setVisible(true);

            ObservableList<Warsztat> listaWarsztatow = new WarsztatService().getAllWarsztat();
            tableContentWarsztat.setItems(listaWarsztatow);

            TableColumn<Warsztat, Long> id = new TableColumn<Warsztat, Long>("ID");
            id.setCellValueFactory(new PropertyValueFactory<Warsztat, Long>("idKolumny"));

            TableColumn<Warsztat, String> miejscowosc = new TableColumn<Warsztat, String>("Miejscowosc");
            miejscowosc.setCellValueFactory(new PropertyValueFactory<Warsztat, String>("miejscowosc"));

            TableColumn<Warsztat, String> ulica = new TableColumn<Warsztat, String>("Ulica");
            ulica.setCellValueFactory(new PropertyValueFactory<Warsztat, String>("ulica"));

            tableContentWarsztat.getColumns().setAll(id, ulica, miejscowosc);
        }
    }

    @FXML //przycisk usuwajacy z bazy samochod albo czesc
    public void deleteFromDatabase() {
        if (chCars.isSelected()) {
            if (tableContentCar.getSelectionModel().getSelectedIndex() != -1) {
                AutoService autoService = new AutoService();
                ObservableList<Auto> samochody = autoService.getAllCars();
                Auto auto = new Auto();
                System.out.println(tableContentCar.getSelectionModel().getSelectedIndex());
                Auto wybrany = samochody.get(tableContentCar.getSelectionModel().getSelectedIndex());
                auto.setId(wybrany.getId());
                autoService.deleteAuto(auto);
                isCarsSelected();
            }
        } else if (chParts.isSelected()) {
            if (tableContentPart.getSelectionModel().getSelectedIndex() != -1) {
                PartService partService = new PartService();
                ObservableList<Part> party = partService.getAllParts();
                Part part = new Part();
                Part wybrany = party.get(tableContentPart.getSelectionModel().getSelectedIndex());
                part.setId(wybrany.getId());
                partService.deletePart(part);
                isPartsSelected();
            }
        } else if (chWarsztats.isSelected()) {
            if (tableContentWarsztat.getSelectionModel().getSelectedIndex() != -1) {
                WarsztatService warsztatService = new WarsztatService();
                ObservableList<Warsztat> warsztats = warsztatService.getAllWarsztat();
                Warsztat warsztat = new Warsztat();
                Warsztat wybrany = warsztats.get(tableContentWarsztat.getSelectionModel().getSelectedIndex());
                warsztat.setId(wybrany.getId());
                warsztatService.deleteWarsztat(warsztat);
            }
        }
    }

    @FXML //dane do comboboxa o warsztatach - do zrobienia
    public void selectWarsztat() {

    }

    @FXML  //co sie dzieje po wybraniu checkboxa select item
    public void selectItem() {

//            System.out.println("warsztaty: " +listaWarsztatow.size());
//
//            AutoService autoService = new AutoService();
//            List<Auto> cars  = autoService.getAllCars();
//            System.out.println(cars.size());
//            for(int i = 0; i < cars.size(); i++){
//                Auto auto = cars.get(i);
//                System.out.println(auto.getId());
//                System.out.println(auto.getWarsztatId());
//                System.out.println(auto.getMarka());
//                System.out.println(auto.getModel());
//                System.out.println(auto.getNrRej());
//
//            }
//
//            System.out.println("auta: " +cars.size());
//
//            PartService partService = new PartService();
//            List<Part> listaCzesci = partService.getAllParts();
//            for(int i=0; i < listaCzesci.size(); i++){
//                Part part = listaCzesci.get(i);
//                System.out.println(part.getId());
//                System.out.println(part.getWarsztatId());
//                System.out.println(part.getProducent());
//                System.out.println(part.getModel());
//                System.out.println(part.getBieznik());
//                System.out.println(part.getDOT());
//                System.out.println(part.getRząd());
//                System.out.println(part.getPólka());
//                System.out.println(part.getMiejsce());
//            }
//
//            System.out.println("czesci: " +listaCzesci.size());
    }

    @FXML //dzilanie przycisku OK w panelu dodawanie itemu
    public void confirmAdding() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/addCar.fxml"));
            Stage carStage = new Stage();
            Rectangle2D rect = Screen.getPrimary().getVisualBounds();
            carStage.setTitle("Add car to database");
            carStage.setScene(new Scene(root));
            carStage.setX(rect.getWidth() / 2 + (rect.getWidth() / 4));
            carStage.setY(rect.getHeight() / 2 - (rect.getHeight() / 8));
            carStage.requestFocus();
            carStage.show();

        } catch (IOException exc) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Wyjebalo w kosmos");
            exc.printStackTrace();
        }
    }

    @FXML
    public void searchInDatabase() { //szukajka

    }

    @FXML //musi byc zebt sie apka nie jebala
    public void cancelAddingCar() {
        Stage stage = (Stage) anchorAdd.getScene().getWindow();
        stage.close();
    }

    @FXML //musi byc zebt sie apka nie jebala
    public void cancelAddingPart() {
        Stage stage = (Stage) anchorAddingPart.getScene().getWindow();
        stage.close();
        stage.close();
    }

    @FXML
    public void dodajDoComboBoxa() {
        WarsztatService warsztatService = new WarsztatService();
        ObservableList<Warsztat> listaWarsztatow = warsztatService.getAllWarsztat();
        ObservableList<String> options = FXCollections.observableArrayList();

        for (int i = 0; i < listaWarsztatow.size(); i++) {
            Warsztat warsztat = listaWarsztatow.get(i);
            options.add(i, warsztat.toString());
        }
        cbWybierzWarsztat.setItems(options);
    }

    @FXML
    public void wybierzWarsztatCzesci() {
        WarsztatService warsztatService = new WarsztatService();
        ObservableList<Warsztat> listaWarsztatow = warsztatService.getAllWarsztat();
        ObservableList<String> options = FXCollections.observableArrayList();

        for (int i = 0; i < listaWarsztatow.size(); i++) {
            Warsztat warsztat = listaWarsztatow.get(i);
            options.add(i, warsztat.toString());
        }
        cbLokalizacjaCzesci.setItems(options);
    }

    @FXML
    public void pokazDaneWarsztat1() {

        ObservableList<Part> warsztaty = (ObservableList<Part>) new PartService().getPartsByWarsztat(1L);

        partViewWarsztat1.setItems(warsztaty);

        TableColumn<Part, Long> id = new TableColumn<Part, Long>("ID");
        id.setCellValueFactory(new PropertyValueFactory<Part, Long>("idKolumny"));

//        TableColumn<Part, Long> categoryID = new TableColumn<Part, Long>("Category ID");
//        categoryID.setCellValueFactory(new PropertyValueFactory<Part, Long>("categoryID"));
//
//        TableColumn<Part, Long> warsztatId = new TableColumn<Part, Long>("warsztat ID");
//        warsztatId.setCellValueFactory(new PropertyValueFactory<Part, Long>("warsztatId"));

        TableColumn<Part, String> producent = new TableColumn<Part, String>("Producent");
        producent.setCellValueFactory(new PropertyValueFactory<Part, String>("producent"));

        TableColumn<Part, String> model = new TableColumn<Part, String>("Model");
        model.setCellValueFactory(new PropertyValueFactory<Part, String>("model"));

        TableColumn<Part, String> DOT = new TableColumn<Part, String>("DOT");
        DOT.setCellValueFactory(new PropertyValueFactory<Part, String>("DOT"));

        TableColumn<Part, String> bieznik = new TableColumn<Part, String>("Bieznik");
        bieznik.setCellValueFactory(new PropertyValueFactory<Part, String>("bieznik"));

        TableColumn<Part, Integer> półka = new TableColumn<Part, Integer>("Półka");
        półka.setCellValueFactory(new PropertyValueFactory<Part, Integer>("pólka"));

        TableColumn<Part, Integer> miejsce = new TableColumn<Part, Integer>("Miejsce");
        miejsce.setCellValueFactory(new PropertyValueFactory<Part, Integer>("miejsce"));

        TableColumn<Part, Integer> rząd = new TableColumn<Part, Integer>("Rząd");
        rząd.setCellValueFactory(new PropertyValueFactory<Part, Integer>("rząd"));

        partViewWarsztat1.getColumns().setAll(id, producent, model, półka, miejsce, rząd, DOT, bieznik);

        ObservableList<Auto> listaSamochodow = new AutoService().getCarsByWarsztat(1L);
        carViewWarsztat1.setItems(listaSamochodow);

        TableColumn<Auto, Long> idCar = new TableColumn<Auto, Long>("ID");
        idCar.setCellValueFactory(new PropertyValueFactory<Auto, Long>("idTabeli"));

        TableColumn<Auto, String> marka = new TableColumn<Auto, String>("Marka");
        marka.setCellValueFactory(new PropertyValueFactory<Auto, String>("marka"));

        TableColumn<Auto, String> modelCar = new TableColumn<Auto, String>("Model");
        modelCar.setCellValueFactory(new PropertyValueFactory<Auto, String>("model"));

        TableColumn<Auto, String> nrRej = new TableColumn<Auto, String>("Nr tablicy");
        nrRej.setCellValueFactory(new PropertyValueFactory<Auto, String>("nrRej"));
        carViewWarsztat1.getColumns().setAll(idCar, marka, modelCar, nrRej);
    }


    @FXML
    public void pokazDaneWarsztat2() {

        ObservableList<Part> warsztaty = (ObservableList<Part>) new PartService().getPartsByWarsztat(3L);

        partViewWarsztat2.setItems(warsztaty);

        TableColumn<Part, Long> id = new TableColumn<Part, Long>("ID");
        id.setCellValueFactory(new PropertyValueFactory<Part, Long>("idKolumny"));


        TableColumn<Part, String> producent = new TableColumn<Part, String>("Producent");
        producent.setCellValueFactory(new PropertyValueFactory<Part, String>("producent"));

        TableColumn<Part, String> model = new TableColumn<Part, String>("Model");
        model.setCellValueFactory(new PropertyValueFactory<Part, String>("model"));

        TableColumn<Part, String> DOT = new TableColumn<Part, String>("DOT");
        DOT.setCellValueFactory(new PropertyValueFactory<Part, String>("DOT"));

        TableColumn<Part, String> bieznik = new TableColumn<Part, String>("Bieznik");
        bieznik.setCellValueFactory(new PropertyValueFactory<Part, String>("bieznik"));

        TableColumn<Part, Integer> półka = new TableColumn<Part, Integer>("Półka");
        półka.setCellValueFactory(new PropertyValueFactory<Part, Integer>("pólka"));

        TableColumn<Part, Integer> miejsce = new TableColumn<Part, Integer>("Miejsce");
        miejsce.setCellValueFactory(new PropertyValueFactory<Part, Integer>("miejsce"));

        TableColumn<Part, Integer> rząd = new TableColumn<Part, Integer>("Rząd");
        rząd.setCellValueFactory(new PropertyValueFactory<Part, Integer>("rząd"));

        partViewWarsztat2.getColumns().setAll(id, producent, model, półka, miejsce, rząd, DOT, bieznik);

        ObservableList<Auto> listaSamochodow = new AutoService().getCarsByWarsztat(3L);
        carViewWarsztat2.setItems(listaSamochodow);

        TableColumn<Auto, Long> idCar = new TableColumn<Auto, Long>("ID");
        idCar.setCellValueFactory(new PropertyValueFactory<Auto, Long>("idTabeli"));

        TableColumn<Auto, String> marka = new TableColumn<Auto, String>("Marka");
        marka.setCellValueFactory(new PropertyValueFactory<Auto, String>("marka"));

        TableColumn<Auto, String> modelCar = new TableColumn<Auto, String>("Model");
        modelCar.setCellValueFactory(new PropertyValueFactory<Auto, String>("model"));

        TableColumn<Auto, String> nrRej = new TableColumn<Auto, String>("Nr tablicy");
        nrRej.setCellValueFactory(new PropertyValueFactory<Auto, String>("nrRej"));
        carViewWarsztat2.getColumns().setAll(idCar, marka, modelCar, nrRej);
    }

    public boolean isNumeric(String strNum) {
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

}
