/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicplayer;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Werkudara
 */
public class DatabaseLagu {
    private int id;
    private SimpleStringProperty judul;
    private SimpleStringProperty artis;
    private SimpleStringProperty album;
    private SimpleStringProperty urllagu;
    private SimpleStringProperty urlgambar;

    public DatabaseLagu() {
    }

//    public DatabaseLagu(int id, StringProperty judul, StringProperty artis, StringProperty album, StringProperty urllagu, StringProperty urlgambar) {
//        this.id = id;
//        this.judul = judul;
//        this.artis = artis;
//        this.album = album;
//        this.urllagu = urllagu;
//        this.urlgambar = urlgambar;
//    }
    public DatabaseLagu(int id, String judul, String artis, String album, String urllagu, String urlgambar){
        this.id=id;
        this.judul = new SimpleStringProperty(judul);
        this.artis = new SimpleStringProperty(artis);
        this.album = new SimpleStringProperty(album);
        this.urllagu=new SimpleStringProperty(urllagu);
        this.urlgambar=new SimpleStringProperty(urlgambar);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul() {
        return judul.get();
    }

    

    public String getArtis() {
        return artis.get();
    }

    

    public String getAlbum() {
        return album.get();
    }

   
    public String getUrllagu() {
        return urllagu.get();
    }

    

    public String getUrlgambar() {
        return urlgambar.get();
    }

   
    
}
