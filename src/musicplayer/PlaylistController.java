/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicplayer;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class PlaylistController implements Initializable{
   
    @FXML
    private Slider sliderVolume;

    @FXML
    private Text txtJudul;

    @FXML
    private TableView<DatabaseLagu>TblLagu;
    @FXML
    private TableColumn<Lagu, Integer> laguid;
    @FXML
   private TableColumn<Lagu, String> judullagu;
    @FXML
    private TableColumn<Lagu, String> artis;
    @FXML
    private TableColumn<Lagu, String> album;

    @FXML
    private ImageView gambar;

    @FXML
    private Slider sliderTime;

    @FXML
    private Label lblTotalTime;

    @FXML
    private ImageView btnclose;

    @FXML
    private ImageView btnmax;

    @FXML
    private ImageView btnmin;

    @FXML
    private ImageView btnMute;

    @FXML
    private Button btnPause;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnStop;
    
    @FXML
    private Button btnHapus;
    
    @FXML
    private Button btnPrevious;

    @FXML
    private Button btnNext;

    @FXML
    private Button btnRepeat;

    @FXML
    private Button btnShuffle;

    @FXML
    private Label lblCurrentTime;

    @FXML
    private Button btnBack;
    private boolean shuffle = false;
    private List<String> listArtwork = new ArrayList<>();
    private String pathalbum;
    Artwork artwork = new Artwork();
    private MediaPlayer player;
    private MediaView mediaview;
    Random rand = new Random();
    private List<String> listLagu=new ArrayList<>();
    private int indexLaguMax = 0;
    private boolean Pause=false;
    private boolean KondisiAwal=false;
     private boolean main=true;
    private boolean mute = false;
    private boolean adalagu = false;
    private boolean repeat;
    private String filePath;
    private int id_lagu;
    private int idx=0;
    private int id_last;
    private String urllagu[];
    private String urlgambar[];
    private String titlesong[];
      int[] cekShuffle = new int[0];
    int[] temp = new int[0];
    int countt=0;
    ObservableList<DatabaseLagu> data=FXCollections.observableArrayList();
    MusicPlayerController control;
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        indexLaguTerakhir();
        if(indexLaguMax!=0){
        urllagu=new String[indexLaguMax];
        urlgambar = new String [indexLaguMax];
        titlesong=new String[indexLaguMax];
        try {
            System.out.println("index nambah");
            Connection con=ConnectDatabase.getConnection();
            ResultSet rs = con.createStatement().executeQuery("select * from playlist");
            while(rs.next())
            {
                data.add(new DatabaseLagu(
                        rs.getInt("id"),
                        titlesong[idx]=rs.getString("judul"), 
                        rs.getString("artis"), 
                        rs.getString("album"),
                        urllagu[idx]=rs.getString("urllagu"),
                        urlgambar[idx]=rs.getString("urlgambar")
                ));
                idx++;
            }
        } catch (SQLException ex) {
            System.err.println("Error"+ex);
        }
        }
        else{
            urllagu = new String[1];
            urlgambar = new String[1];
        }
        
        laguid.setCellValueFactory(new PropertyValueFactory<>("id"));
        judullagu.setCellValueFactory(new PropertyValueFactory<>("judul"));
        artis.setCellValueFactory(new PropertyValueFactory<>("artis"));
        album.setCellValueFactory(new PropertyValueFactory<>("album")); 
        
        TblLagu.setItems(null);
        TblLagu.setItems(data); 
        for(int i=0;i<idx;i++){
            System.out.println("url lagu : "+urllagu[i]);
            System.out.println("url gambar : "+urlgambar[i]);
            listLagu.add(urllagu[i]);
            listArtwork.add(urlgambar[i]);
            
        }
    }
    public void kliklagu(){
       if(adalagu==false){
       id_lagu = TblLagu.getSelectionModel().getSelectedItem().getId()-1;
       filePath = TblLagu.getSelectionModel().getSelectedItem().getUrllagu();
       pathalbum = TblLagu.getSelectionModel().getSelectedItem().getUrlgambar();
        System.out.println("id pencet: "+id_lagu);
        System.out.println(filePath);
        initLagu(filePath);
        System.out.println(pathalbum);
           System.out.println("klik awal");
        adalagu=true;
       }
       else{
           stop();
            id_lagu = TblLagu.getSelectionModel().getSelectedItem().getId()-1;
       filePath = TblLagu.getSelectionModel().getSelectedItem().getUrllagu();
       pathalbum = TblLagu.getSelectionModel().getSelectedItem().getUrlgambar();
        System.out.println("id pencet : "+id_lagu);
        System.out.println(filePath);
        initLagu(filePath);
        System.out.println(pathalbum);
           System.out.println("klik setelahnya");
       }
    }
    public void indexLaguTerakhir(){
         try {
            Connection con=ConnectDatabase.getConnection();
            ResultSet rs = con.createStatement().executeQuery("select * from playlist");
            
            while (rs.next())
            {
                indexLaguMax = rs.getInt("id");
            }
        } catch (SQLException ex) {
            System.err.println("Error"+ex);
        }
        System.out.println(indexLaguMax);
    }
     public void backtomain(javafx.event.ActionEvent event){
         if(KondisiAwal==false){
       }
       else{
           stop();
       }
        Parent root2=null;
        try {
            root2 = FXMLLoader.load(getClass().getResource("mp3ui.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MusicPlayerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene2 = new Scene(root2);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.setTitle("MP3 Player");
        window.show();
//         stop();
//        Parent root2=null;
//        try {
//            root2 = FXMLLoader.load(getClass().getResource("mp3ui.fxml"));
//        } catch (IOException ex) {
//            Logger.getLogger(MusicPlayerController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        Scene scene2 = new Scene(root2);
//        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        window.setScene(scene2);
//        window.setTitle("Playlist");
//        window.show();
    }
     private void initLagu(String pathLagu) {
        Media media = new Media(pathLagu);
        player = new MediaPlayer(media);
    }
    public void TabelLagu(){
        TblLagu.getItems().clear();
        try {
            Connection con=ConnectDatabase.getConnection();
            ResultSet rs = con.createStatement().executeQuery("select * from playlist");
            while(rs.next())
            {
                data.add(new DatabaseLagu(
                        rs.getInt("id"),
                        rs.getString("judul"), 
                        rs.getString("artis"), 
                        rs.getString("album"),
                        rs.getString("urllagu"),
                        rs.getString("urlgambar")
                ));
            }
        } catch (SQLException ex) {
            System.err.println("Error"+ex);
        }
        
        laguid.setCellValueFactory(new PropertyValueFactory<>("id_film"));
        judullagu.setCellValueFactory(new PropertyValueFactory<>("judul"));
        artis.setCellValueFactory(new PropertyValueFactory<>("artis"));
        album.setCellValueFactory(new PropertyValueFactory<>("album")); 
        
        TblLagu.setItems(null);
        TblLagu.setItems(data);
    }
    @FXML
    public void keluar(){
       System.exit(0);
    }
    @FXML
    public void min(MouseEvent event){
        Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
        s.setIconified(true);
    }
    @FXML
     public void max(MouseEvent event){
        Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
        s.setFullScreen(true);
    }
    @FXML
    public void play(){
        KondisiAwal=true;
        System.out.println("id lagu "+id_lagu);
        String jdl = titlesong[id_lagu];
           txtJudul.setText(jdl);
       if(Pause==false){
           System.out.println("idx : "+idx);
       // idx=id_lagu;
           System.out.println("id lagu : "+id_lagu);
           System.out.println("index lagu max : "+indexLaguMax);
        initLagu(urllagu[id_lagu]);
        String now = listArtwork.get(id_lagu);
        String lagunow = listLagu.get(id_lagu);
        artwork.getArtwork(now,lagunow);
        System.out.println(now);
           albumartwork();
           System.out.println(indexLaguMax);
       player.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        lblCurrentTime.setText((int) (player.getCurrentTime().toMinutes())
                                + ":" + (int) (player.getCurrentTime().toSeconds() % 60));
                        if (sliderTime.isPressed()) {
                            player.seek(player.getMedia().getDuration().
                                    multiply(sliderTime.getValue() / 100));
                        } else {
                            sliderTime.setValue(
                                    (player.getCurrentTime().toMillis()
                                    / player.getTotalDuration().toMillis()) * 100);
                        }
                    }
                });
            }
        });
        player.setOnReady(new Runnable() {
            @Override
            public void run() {
                lblTotalTime.setText((int) (player.getTotalDuration().toMinutes())
                        + ":" + (int) (player.getTotalDuration().toSeconds() % 60));
                if (mute == false) {
                    player.setVolume(sliderVolume.getValue() / 100);
                } else {
                    player.setVolume(0);
                }
                player.play();
            }
        });
        player.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                if(repeat==false){
                   id_lagu++;
                }
                player.stop();
                sliderTime.setValue(0);
                if(id_lagu!=indexLaguMax){
                initLagu(listLagu.get(id_lagu));
                play();
                }
                else if(id_lagu==indexLaguMax){
                stop();
                JOptionPane.showMessageDialog(null, "Lagu habis");
                }
            }
        });
       }
       else{
           player.play();
       }
    }
    @FXML
    public void pause(){
        player.pause();
        Pause=true;
    }
    @FXML
    public void stop(){
        player.stop();
       lblTotalTime.setText((int) (player.getTotalDuration().toMinutes()*0)
                       + ":" + (int) (player.getTotalDuration().toSeconds() * 0));
       lblCurrentTime.setText((int) (player.getCurrentTime().toMinutes()*0)
                                + ":" + (int) (player.getCurrentTime().toSeconds() * 0)); 
       idx=0;
    }
    @FXML
   public void setVolume(){
       if (player != null) {
            player.setVolume(sliderVolume.getValue() / 100);
            System.out.println(sliderVolume.getValue());
        }
   }
   @FXML
   public void next(){
       player.stop();
       id_lagu++;
       sliderTime.setValue(0);
       System.out.println(id_lagu);
       System.out.println(idx);
       if(id_lagu==indexLaguMax)
       {
         JOptionPane.showMessageDialog(null, "Lagu habis");
         stop();
       }
       else{
       initLagu(listLagu.get(id_lagu));
       play();
       }
       
   }
   @FXML
   public void previous(){
       //now
       player.stop();
       id_lagu--;
       sliderTime.setValue(0);
       if(id_lagu==-1)
       {
         JOptionPane.showMessageDialog(null, "Lagu habis");
         stop();
       }
       else{
       initLagu(listLagu.get(id_lagu));
       play();
       }
   }
   public void albumartwork(){
        byte[] gbr=artwork.showArtwork();
        BufferedImage img = null;
        if(gbr==null){
            File file = new File("icons8-music-64.png");
            try {
                img = ImageIO.read(file);
                        } catch (IOException ex) {
                Logger.getLogger(MusicPlayerController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Image image = SwingFXUtils.toFXImage(img, null);
            gambar.setImage(image);
        }else{
           try {
               img = ImageIO.read(new ByteArrayInputStream(gbr));
           } catch (IOException ex) {
               System.out.println("error");
           }
           Image image = SwingFXUtils.toFXImage(img, null);
         gambar.setImage(image);
        }    
   }
   @FXML
   public void artwork() throws IOException, UnsupportedTagException, InvalidDataException {
       String source = player.getMedia().getSource();
       System.out.println(source + " +++");
        Mp3File mp3 = new Mp3File(source);
        ID3v2 tag = mp3.getId3v2Tag();
        String artis = tag.getArtist();
        System.out.println(artis);
        byte[] imageData = tag.getAlbumImage();
        //converting the bytes to an image
       // imagePane= ImageIO.read(new ByteArrayInputStream(imageData));
   }
   @FXML
   public void Repeat(){
       if(repeat){
            repeat = false;
            System.out.println("Repeat is off");
        }else{
            repeat = true;
            System.out.println("Repeat once is on");
            listLagu.get(id_lagu);
            player.play();
        }
   }
   @FXML
   public void Shuffle(){
              if(KondisiAwal==false){
       }
       else{
           stop();
       }
       if(shuffle){
           shuffle=true;
           countt=0;
           temp=new int[0];
           cekShuffle=new int[0];
       }
       else{
           shuffle=false;
       int rand_lagu=0;
       boolean cek=true;
       while(cek==true){
           System.out.println(listLagu.size());
           rand_lagu=rand.nextInt(indexLaguMax);
           rand_lagu+=1;
           if(countt==0){
               countt+=1;
               cekShuffle = new int[countt];
               cekShuffle[countt-1]=rand_lagu;
        
           }else if(countt!=0){
               for(int i=0;i<countt;i++){
                   if(cekShuffle[i]==rand_lagu){
                       cek=true;
                       break;
                   }else{
                       cek=false;
                   }
               }
               if(cek==false){
                   temp=new int[countt];
                   for(int i=0;i<countt;i++){
                       temp[i]=cekShuffle[i];
                   }
                   countt+=1;
                   cekShuffle=new int[countt];
                   for(int  i=0;i<countt-1;i++)
                   {
                       cekShuffle[i]=temp[i];
                   }
                   cekShuffle[countt-1]=rand_lagu;
               }
           }
       }
       

       System.out.println("Random : "+rand_lagu);
       id_lagu=rand_lagu;
       //initLagu(filetemp);
       play();
     //  System.out.println(listLagu.size());
       }
   }
       public void getTabelLagu(){
           indexLaguTerakhir();
        String tempurllagu[]=new String[indexLaguMax];
        String tempurlgambar[]=new String[indexLaguMax];
        String tempjudul[]=new String[indexLaguMax];
        TblLagu.getItems().clear();
      
        if(indexLaguMax!=0){
        urllagu=new String[indexLaguMax];
        urlgambar = new String [indexLaguMax];
        titlesong=new String[indexLaguMax];
        idx=0;
            System.out.println("indexLaguMax");
        try {
            System.out.println("index nambah");
            Connection con=ConnectDatabase.getConnection();
            ResultSet rs = con.createStatement().executeQuery("select * from playlist");
            while(rs.next())
            {
                data.add(new DatabaseLagu(
                        rs.getInt("id"),
                        titlesong[idx]=rs.getString("judul"), 
                        rs.getString("artis"), 
                        rs.getString("album"),
                        urllagu[idx]=rs.getString("urllagu"),
                        urlgambar[idx]=rs.getString("urlgambar")
                )); 
                idx++;
            }
        } catch (SQLException ex) {
            System.err.println("Error"+ex);
        }
        }
        else{
            urllagu = new String[1];
            urlgambar = new String[1];
        }
        
        laguid.setCellValueFactory(new PropertyValueFactory<>("id"));
        judullagu.setCellValueFactory(new PropertyValueFactory<>("judul"));
        artis.setCellValueFactory(new PropertyValueFactory<>("artis"));
        album.setCellValueFactory(new PropertyValueFactory<>("album")); 
        
        TblLagu.setItems(null);
        TblLagu.setItems(data); 
        for(int i=0;i<idx;i++){
            System.out.println("url lagu : "+urllagu[i]);
            System.out.println("url gambar : "+urlgambar[i]);
            listLagu.add(urllagu[i]);
            listArtwork.add(urlgambar[i]);
            
        }
    }
   public void hapus() {
        Connection con = ConnectDatabase.getConnection();
        PreparedStatement statement = null;
        String sql = "DELETE FROM playlist WHERE id = ?";
        try {
            statement = con.prepareStatement(sql);
            statement.setInt(1, id_lagu);
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
        TabelLagu();
        getTabelLagu();
    }
}
