package entity;

import databse.AutoService;
import databse.PartService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Part {

    private Long idKolumny;
    private Long id;
    private Long warsztatId;
    private String producent;
    private String model;
    private Long autoID;
    private int pólka;
    private int miejsce;
    private int rząd;
    private String DOT;
    private String bieznik;
    private String info;
    private String samochod;

    public Part(){

    }

    public Part(Long id, Long autoID, Long warsztatId, String producent, String model , int pólka, int miejsce, int rząd, String DOT, String bieznik, Long idKolumny, String info, String samochod) {
        this.id = id;
        this.producent = producent;
        this.model = model;
        this.autoID = autoID;
        this.pólka = pólka;
        this.miejsce = miejsce;
        this.rząd = rząd;
        this.DOT = DOT;
        this.bieznik = bieznik;
        this.warsztatId = warsztatId;
        this.idKolumny = idKolumny;
        this.info = info;
        this.samochod = samochod;
    }


    public String getInfo(){
        String temp = "";
        if(warsztatId == 1){
            temp = "Warszawa, Orlich Gniazd 2";
        } else if (warsztatId == 2){
            temp = "Warszawa, Radiowa 10";
        }
        return temp;
    }

    public String getSamochod() {
        return samochod;
    }

    public void setSamochod(String samochod){
        this.samochod = samochod;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProducent() {
        return producent;
    }

    public void setProducent(String producent) {
        this.producent = producent;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPólka() {
        return pólka;
    }

    public void setPólka(int pólka) {
        this.pólka = pólka;
    }

    public int getMiejsce() {
        return miejsce;
    }

    public void setMiejsce(int miejsce) {
        this.miejsce = miejsce;
    }

    public int getRząd() {
        return rząd;
    }

    public void setRząd(int rząd) {
        this.rząd = rząd;
    }

    public String getDOT() {
        return DOT;
    }

    public void setDOT(String DOT) {
        this.DOT = DOT;
    }

    public String getBieznik() {
        return bieznik;
    }

    public void setBieznik(String bieznik) {
        this.bieznik = bieznik;
    }

    public Long getWarsztatId() {
        return warsztatId;
    }

    public void setWarsztatId(Long warsztatId) {
        this.warsztatId = warsztatId;
    }

    public Long getIdKolumny() {
        return idKolumny;
    }

    public void setIdKolumny(Long idKolumny) {
        this.idKolumny = idKolumny;
    }

    public Long getAutoID() {
        return autoID;
    }

    public void setAutoID(Long autoID) {
        this.autoID = autoID;
    }

//    public String nazwaAuta(long autoID) {
//        AutoService autoService = new AutoService();
//        ObservableList<Auto> autos = autoService.getAllCars();
//        Auto wybrany = autos.get((int) autoID);
//       return wybrany.toString();
//    }


}
