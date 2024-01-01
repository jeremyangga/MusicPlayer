/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicplayer;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

/**
 *
 * @author User
 */
public class Artwork {
    private String path;
     Mp3File mp3 = null;
     Lagu lagu= new Lagu();
     int idx=1;
    public Artwork(String path) {
        this.path = path;
    }

    public Artwork() {
      
    }
    public void getConnection(String filealbum,String filePath){
            try {
           mp3 = new Mp3File(filealbum);
            }catch (IOException ex) {
                Logger.getLogger(Artwork.class.getName()).log(Level.SEVERE, null, ex);
            }catch (UnsupportedTagException ex) {
                Logger.getLogger(Artwork.class.getName()).log(Level.SEVERE, null, ex);
            }catch (InvalidDataException ex) {
                Logger.getLogger(Artwork.class.getName()).log(Level.SEVERE, null, ex);
                }
       if (mp3.hasId3v1Tag()) {
        	ID3v1 id3v1Tag = mp3.getId3v1Tag();
        	System.out.println("Track: " + id3v1Tag.getTrack());
//                lagu.setId(idx);
//                idx++;
        	System.out.println("Artist: " + id3v1Tag.getArtist());
                lagu.setArtis(id3v1Tag.getArtist());
        	System.out.println("Title: " + id3v1Tag.getTitle());
                lagu.setJudul(id3v1Tag.getTitle());
        	System.out.println("Album: " + id3v1Tag.getAlbum());
                lagu.setAlbum(id3v1Tag.getAlbum());
                lagu.setUrlgambar(filealbum);
                lagu.setUrllagu(filePath);
        	System.out.println("Year: " + id3v1Tag.getYear());
        	System.out.println("Genre: " + id3v1Tag.getGenre() + " (" + id3v1Tag.getGenreDescription() + ")");
        	System.out.println("Comment: " + id3v1Tag.getComment());
        }
       else{
           lagu.setArtis("Artis tidak diketahui");
           lagu.setAlbum("Album tidak diketahui");
           lagu.setJudul("Judul tidak diketahui");
           lagu.setUrlgambar("icons8-music-64.png");
           lagu.setUrllagu(filePath);
       }
       lagu.addToDatabase();
    }
    public void getConnectionPlaylist(String filealbum,String filePath){
            try {
           mp3 = new Mp3File(filealbum);
            }catch (IOException ex) {
                Logger.getLogger(Artwork.class.getName()).log(Level.SEVERE, null, ex);
            }catch (UnsupportedTagException ex) {
                Logger.getLogger(Artwork.class.getName()).log(Level.SEVERE, null, ex);
            }catch (InvalidDataException ex) {
                Logger.getLogger(Artwork.class.getName()).log(Level.SEVERE, null, ex);
                }
       if (mp3.hasId3v1Tag()) {
        	ID3v1 id3v1Tag = mp3.getId3v1Tag();
        	System.out.println("Track: " + id3v1Tag.getTrack());
//                lagu.setId(idx);
//                idx++;
        	System.out.println("Artist: " + id3v1Tag.getArtist());
                lagu.setArtis(id3v1Tag.getArtist());
        	System.out.println("Title: " + id3v1Tag.getTitle());
                lagu.setJudul(id3v1Tag.getTitle());
        	System.out.println("Album: " + id3v1Tag.getAlbum());
                lagu.setAlbum(id3v1Tag.getAlbum());
                lagu.setUrlgambar(filealbum);
                lagu.setUrllagu(filePath);
        	System.out.println("Year: " + id3v1Tag.getYear());
        	System.out.println("Genre: " + id3v1Tag.getGenre() + " (" + id3v1Tag.getGenreDescription() + ")");
        	System.out.println("Comment: " + id3v1Tag.getComment());
        }
        else{
           lagu.setArtis("Artis tidak diketahui");
           lagu.setAlbum("Album tidak diketahui");
           lagu.setJudul("Judul tidak diketahui");
           lagu.setUrlgambar("icons8-music-64.png");
           lagu.setUrllagu(filePath);
       }
       lagu.addToPlaylist();
    }
    public void getArtwork(String filepath,String filelagu){
        path=filepath;
        lagu.setUrllagu(path);
        lagu.setUrlgambar(filelagu);
        try {
           mp3 = new Mp3File(path);
        } catch (IOException ex) {
            Logger.getLogger(Artwork.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedTagException ex) {
            Logger.getLogger(Artwork.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidDataException ex) {
            Logger.getLogger(Artwork.class.getName()).log(Level.SEVERE, null, ex);
        }
       System.out.println("Has ID3v1 tag?: " + (mp3.hasId3v1Tag() ? "YES" : "NO"));
       if(mp3.hasId3v2Tag()){
       ID3v2 id3 = mp3.getId3v2Tag();
       }
        System.out.println("Has ID3v2 tag?: "+(mp3.hasId3v2Tag()?"YES":"NO"));
       if (mp3.hasId3v1Tag()) {
        	ID3v1 id3v1Tag = mp3.getId3v1Tag();
        	System.out.println("Track: " + id3v1Tag.getTrack());
                //lagu.setId(idx);
        	System.out.println("Artist: " + id3v1Tag.getArtist());
                lagu.setArtis(id3v1Tag.getArtist());
        	System.out.println("Title: " + id3v1Tag.getTitle());
                lagu.setJudul(id3v1Tag.getArtist());
        	System.out.println("Album: " + id3v1Tag.getAlbum());
                lagu.setAlbum(id3v1Tag.getAlbum());
        	System.out.println("Year: " + id3v1Tag.getYear());
        	System.out.println("Genre: " + id3v1Tag.getGenre() + " (" + id3v1Tag.getGenreDescription() + ")");
        	System.out.println("Comment: " + id3v1Tag.getComment());
        }
       else{
           lagu.setArtis("Artis tidak diketahui");
           lagu.setAlbum("Album tidak diketahui");
           lagu.setJudul("Judul tidak diketahui");
           lagu.setUrlgambar("icons8-music-64.png");
           lagu.setUrllagu(filelagu);
       }
    }
 
    public byte[] showArtwork()
    {
//        Image img = new Image("icons8-music-64.png");
        byte[] imageData = null;
        if (mp3.hasId3v2Tag()) {
        ID3v2 id3 = mp3.getId3v2Tag();
         imageData = id3.getAlbumImage();
         if(imageData==null){
             System.out.println("gak ada gambar");
             imageData=null;
         }
        }
        else{
         BufferedImage bImage = null;
            try {
                bImage = ImageIO.read(new File("icons8-music-64.png"));
            } catch (IOException ex) {
                Logger.getLogger(Artwork.class.getName()).log(Level.SEVERE, null, ex);
            }
         ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try {
                ImageIO.write(bImage, "png", bos );
            } catch (IOException ex) {
                Logger.getLogger(Artwork.class.getName()).log(Level.SEVERE, null, ex);
            }
         imageData=bos.toByteArray();
         
        }
        return imageData;
    }
}
