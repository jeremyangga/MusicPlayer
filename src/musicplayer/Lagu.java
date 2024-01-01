/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicplayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author User
 */
public class Lagu {
    private int id;
    private String judul;
    private String artis;
    private String album;
    private String urllagu;
    private String urlgambar;
    
    public Lagu(){}
    public Lagu(String judul){
        this.judul = judul;
    }

    public Lagu(int id, String judul, String artis, String album, String url) {
        this.id = id;
        this.judul = judul;
        this.artis = artis;
        this.album = album;
        this.urllagu = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getArtis() {
        return artis;
    }

    public void setArtis(String artis) {
        this.artis = artis;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getUrlGambar() {
        return urlgambar;
    }

    public void setUrlGambar(String url) {
        this.urlgambar = url;
    }

    public String getUrllagu() {
        return urllagu;
    }

    public void setUrllagu(String urllagu) {
        this.urllagu = urllagu;
    }

    public String getUrlgambar() {
        return urlgambar;
    }

    public void setUrlgambar(String urlgambar) {
        this.urlgambar = urlgambar;
    }
    public void addToDatabase(){
        Connection con = ConnectDatabase.getConnection();
        PreparedStatement statement = null;
        String query = "INSERT INTO lagu "
                + "(judul,artis,album,urllagu,urlgambar) values (?,?,?,?,?)";
        try {
            statement = con.prepareStatement(query);
            //statement.setInt(1, id);
            statement.setString(1, judul);
            statement.setString(2, artis);
            statement.setString(3, album);
            statement.setString(4, urllagu);
            statement.setString(5, urlgambar);
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try {
                if(statement!=null)
                   statement.close();
                if(con!=null)
                    con.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
     public void addToPlaylist(){
        Connection con = ConnectDatabase.getConnection();
        PreparedStatement statement = null;
        String query = "INSERT INTO playlist "
                + "(judul,artis,album,urllagu,urlgambar) values (?,?,?,?,?)";
        try {
            statement = con.prepareStatement(query);
            //statement.setInt(1, id);
            statement.setString(1, judul);
            statement.setString(2, artis);
            statement.setString(3, album);
            statement.setString(4, urllagu);
            statement.setString(5, urlgambar);
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try {
                if(statement!=null)
                   statement.close();
                if(con!=null)
                    con.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
