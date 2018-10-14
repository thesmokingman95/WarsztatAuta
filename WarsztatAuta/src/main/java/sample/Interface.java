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
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

;

public class Interface {

    @FXML //panele okien
    private AnchorPane anchor, anchorAdd, anchorAddingPart, anchorWarsztat1, anchorWarsztat2, mainView, anchorOpony;

    @FXML //combobox wyboru itemu
    private ComboBox<String> cSelectItem, cSelectLocation, cbWybierzWarsztat, cbLokalizacjaCzesci, cbWyborAuta;

    @FXML //przycik zaloguj
    private Button bLogin, bDodaj, bUsun, bSzukaj, bAddOK, bAdd, bRemove, bSearch, bAnulujCzesc, bDodajCzesc, bDodajDoBazy, bPokazOpony;

    @FXML
    private CheckBox chCars, chParts, chWarsztats, checkDefault;

    @FXML //pole wpisania loginu
    private TextField tUsername, tPodajMarke, tPodajModel, tPodajNrRej, tNazwaProducenta, tNazwaModelu, tRodzajBieznika, tDataOT, tRząd, tPolka, tMiejsce, tSzukajka;

    @FXML //pole wpisania hasla
    private PasswordField tPassword;

    @FXML //etykieta wyswietlajaca status polaczenia
    private Label lconnectionStatus;

    @FXML
    private TableView<Auto> tableContentCar, carViewWarsztat1, carViewWarsztat2, wyszukaneSamochodyInfo;;

    @FXML
    private TableView<Warsztat> tableContentWarsztat;

    @FXML
    private TableView<Part> tableContentPart;

    @FXML
    private TableView<Part> partViewWarsztat1, partViewWarsztat2;

    @FXML
    private Pane leftPanePartAdd;

    private DatabseConnection db = new DatabseConnection();


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
    public void searchInDatabase() { //szukajka
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/search.fxml"));
            Stage carStage = new Stage();
            Rectangle2D rect = Screen.getPrimary().getVisualBounds();
            carStage.setTitle("Wyszukaj");
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
    }

    @FXML
    public void searchCarInDatabase(){
        if(!tSzukajka.getText().isEmpty()) {
            String tempString = tSzukajka.getText();
            ObservableList<Auto> listaSamochodow = new AutoService().getAutoByNrRej(tempString);
            wyszukaneSamochodyInfo.setItems(listaSamochodow);

            TableColumn<Auto, Long> id = new TableColumn<Auto, Long>("ID");
            id.setCellValueFactory(new PropertyValueFactory<Auto, Long>("idTabeli"));

            TableColumn<Auto, String> marka = new TableColumn<Auto, String>("Marka");
            marka.setCellValueFactory(new PropertyValueFactory<Auto, String>("marka"));

            TableColumn<Auto, String> model = new TableColumn<Auto, String>("Model");
            model.setCellValueFactory(new PropertyValueFactory<Auto, String>("model"));

            TableColumn<Auto, String> nrRej = new TableColumn<Auto, String>("Nr tablicy");
            nrRej.setCellValueFactory(new PropertyValueFactory<Auto, String>("nrRej"));

            TableColumn<Auto, String> adresWarsztatu = new TableColumn<Auto, String>("Adres");
            adresWarsztatu.setCellValueFactory(new PropertyValueFactory<Auto, String>("info"));

            wyszukaneSamochodyInfo.getColumns().setAll(id, marka, model, nrRej, adresWarsztatu);
       }
    }

    @FXML
    public void selectWarsztat(){

    }

    @FXML
    public void pokazOpony(){ //widok na opony n-tego samochodu
//        if(tableContentCar.getSelectionModel().getSelectedIndex() != -1){
//            ObservableList<Part> party = new PartService().getPartsByAuto((long) tableContentCar.getSelectionModel().getSelectedIndex());
//            partViewWarsztat1.setItems(party);
//
//            TableColumn<Part, Long> id3 = new TableColumn<Part, Long>("ID");
//            id3.setCellValueFactory(new PropertyValueFactory<Part, Long>("idKolumny"));
//
//            TableColumn<Part, Long> idc3 = new TableColumn<Part, Long>("I2D");
//            idc3.setCellValueFactory(new PropertyValueFactory<Part, Long>("autoID"));
//
//            TableColumn<Part, String> producent3 = new TableColumn<Part, String>("Producent");
//            producent3.setCellValueFactory(new PropertyValueFactory<Part, String>("producent"));
//
//            TableColumn<Part, String> model3 = new TableColumn<Part, String>("Model");
//            model3.setCellValueFactory(new PropertyValueFactory<Part, String>("model"));
//
//            TableColumn<Part, String> DOT3 = new TableColumn<Part, String>("DOT");
//            DOT3.setCellValueFactory(new PropertyValueFactory<Part, String>("DOT"));
//
//            TableColumn<Part, String> bieznik3 = new TableColumn<Part, String>("Bieznik");
//            bieznik3.setCellValueFactory(new PropertyValueFactory<Part, String>("bieznik"));
//
//            TableColumn<Part, Integer> półka3 = new TableColumn<Part, Integer>("Półka");
//            półka3.setCellValueFactory(new PropertyValueFactory<Part, Integer>("pólka"));
//
//            TableColumn<Part, Integer> miejsce3 = new TableColumn<Part, Integer>("Miejsce");
//            miejsce3.setCellValueFactory(new PropertyValueFactory<Part, Integer>("miejsce"));
//
//            TableColumn<Part, Integer> rząd3 = new TableColumn<Part, Integer>("Rząd");
//            rząd3.setCellValueFactory(new PropertyValueFactory<Part, Integer>("rząd"));
//
//            TableColumn<Part, String> adresWarsztatu3 = new TableColumn<Part, String>("Adres");
//            adresWarsztatu3.setCellValueFactory(new PropertyValueFactory<Part, String>("info"));
//
//            TableColumn<Part, String> auto3 = new TableColumn<Part, String>("Auto");
//            auto3.setCellValueFactory(new PropertyValueFactory<Part, String>("samochod"));
//
//            partViewWarsztat1.getColumns().setAll(id3, idc3, producent3, model3, DOT3, bieznik3, półka3, miejsce3, rząd3, adresWarsztatu3, auto3);
//        }



    }

    @FXML //dodanie widoku zawartosci warsztatow
    public void openWarsztatView()  {
        if (chWarsztats.isSelected()) {
            int ktoryWarsztat = tableContentWarsztat.getSelectionModel().getSelectedIndex();
            if (ktoryWarsztat == 0) {//widok warsztatu nr 1
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/WarsztatView1.fxml"));
                    Stage warsztatStage = new Stage();
                    Rectangle2D rect = Screen.getPrimary().getVisualBounds();
                    warsztatStage.setTitle("Widok magazynu Orlich Gniazd 2");
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
                    warsztatStage.setTitle("Widok magazynu Radiowa 10");
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
            }
        } else if(chCars.isSelected()){
            if(tableContentCar.getSelectionModel().getSelectedIndex() != -1) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/oponyList.fxml"));
                    Stage oponyStage = new Stage();
                    Rectangle2D rect = Screen.getPrimary().getVisualBounds();
                    oponyStage.setTitle("Widok opon");
                    oponyStage.setScene(new Scene(root));
                    oponyStage.setX(rect.getWidth() / 2);
                    oponyStage.setY(rect.getHeight() / 2 - (rect.getHeight() / 4));
                    oponyStage.requestFocus();
                    oponyStage.show();
                } catch (IOException exc) {
                    Logger logger = Logger.getLogger(getClass().getName());
                    logger.log(Level.SEVERE, "Wyjebalo okno");
                    exc.printStackTrace();
                }
            }
        }
    }

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

            String opcjaComboBoxa = cbWybierzWarsztat.getSelectionModel().getSelectedItem();
            if (opcjaComboBoxa.equals("Warszawa, Orlich Gniazd 2"))
                dodawanySamochod.setWarsztatId(1L);
            else if (opcjaComboBoxa.equals("Warszawa, Radiowa 10"))
                dodawanySamochod.setWarsztatId(2L);
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
            if(cbLokalizacjaCzesci.getSelectionModel().getSelectedIndex() != -1){
                dodawanaCzesc.setWarsztatId(cbLokalizacjaCzesci.getSelectionModel().getSelectedIndex() + 1L);
            }
            if(cbLokalizacjaCzesci.getSelectionModel().getSelectedIndex() != -1) {
                if(cbLokalizacjaCzesci.getSelectionModel().getSelectedIndex() == 0) {
                    ObservableList<Auto> samochody = new AutoService().getCarsByWarsztat(1L);
                    Auto wybrany = samochody.get(cbWyborAuta.getSelectionModel().getSelectedIndex());
                    dodawanaCzesc.setAutoID(wybrany.getId());
                } else if (cbLokalizacjaCzesci.getSelectionModel().getSelectedIndex() == 1){
                    ObservableList<Auto> samochody = new AutoService().getCarsByWarsztat(2L);
                    Auto wybrany = samochody.get(cbWyborAuta.getSelectionModel().getSelectedIndex());
                    dodawanaCzesc.setAutoID(wybrany.getId());
                }
            }
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

    @FXML //zamkniecie okna glownego - przycisk cancel - do PRZEBUDOWY
    public void closeWindow() {
        Stage stage = (Stage) anchor.getScene().getWindow();
        stage.close();
    }

    @FXML //wyswietlanie widoku samochodow w TableView
    public void isCarsSelected() {

        if (chCars.isSelected()) {
            chWarsztats.setSelected(false);
            chParts.setSelected(false);

            tableContentPart.setVisible(false);
            tableContentWarsztat.setVisible(false);
            tableContentCar.setVisible(true);

            ObservableList<Auto> listaSamochodow = new AutoService().getAllCars();
            tableContentCar.setItems(listaSamochodow);

            TableColumn<Auto, Long> id = new TableColumn<Auto, Long>("ID");
            id.setCellValueFactory(new PropertyValueFactory<Auto, Long>("idTabeli"));

            TableColumn<Auto, String> marka = new TableColumn<Auto, String>("Marka");
            marka.setCellValueFactory(new PropertyValueFactory<Auto, String>("marka"));

            TableColumn<Auto, String> model = new TableColumn<Auto, String>("Model");
            model.setCellValueFactory(new PropertyValueFactory<Auto, String>("model"));

            TableColumn<Auto, String> nrRej = new TableColumn<Auto, String>("Nr tablicy");
            nrRej.setCellValueFactory(new PropertyValueFactory<Auto, String>("nrRej"));

            TableColumn<Auto, String> adresWarsztatu = new TableColumn<Auto, String>("Adres");
            adresWarsztatu.setCellValueFactory(new PropertyValueFactory<Auto, String>("info"));

            TableColumn<Auto, Long> idC = new TableColumn<Auto, Long>("I2D");
            idC.setCellValueFactory(new PropertyValueFactory<Auto, Long>("id"));

            tableContentCar.getColumns().setAll(id, marka, model, nrRej, adresWarsztatu, idC);



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

            TableColumn<Part, Long> idc = new TableColumn<Part, Long>("I2D");
            idc.setCellValueFactory(new PropertyValueFactory<Part, Long>("autoID"));

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

            TableColumn<Part, String> adresWarsztatu = new TableColumn<Part, String>("Adres");
            adresWarsztatu.setCellValueFactory(new PropertyValueFactory<Part, String>("info"));

            TableColumn<Part, String> auto = new TableColumn<Part, String>("Auto");
            auto.setCellValueFactory(new PropertyValueFactory<Part, String>("samochod"));

            tableContentPart.getColumns().setAll(id, idc, producent, model, DOT, bieznik, półka, miejsce, rząd, adresWarsztatu, auto);

            pokazOpony();
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
                isWarsztatsSelected();
            }
        }
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
    public void dodajAuto(){
        AutoService autoService = new AutoService();
        ObservableList<String> options = FXCollections.observableArrayList();
        ObservableList<Auto> autaTemp1 = FXCollections.observableArrayList();
        ObservableList<Auto> autaTemp2 = FXCollections.observableArrayList();

        if(cbLokalizacjaCzesci.getSelectionModel().getSelectedIndex() == 0){
            autaTemp1 = autoService.getCarsByWarsztat(1L);
            for (int i = 0; i < autaTemp1.size(); i++) {
                Auto auto = autaTemp1.get(i);
                options.add(i, auto.toString());
            }
            cbWyborAuta.setItems(options);
        } else if(cbLokalizacjaCzesci.getSelectionModel().getSelectedIndex() == 1){
            autaTemp2 = autoService.getCarsByWarsztat(2L);
            for (int i = 0; i < autaTemp2.size(); i++) {
                Auto auto = autaTemp2.get(i);
                options.add(i, auto.toString());
            }
            cbWyborAuta.setItems(options);
        }
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

        ObservableList<Part> warsztaty = (ObservableList<Part>) new PartService().getPartsByWarsztat(2L);

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

        ObservableList<Auto> listaSamochodow = new AutoService().getCarsByWarsztat(2L);
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
