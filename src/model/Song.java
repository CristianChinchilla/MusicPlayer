package model;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author cchin
 */
public class Song {
    
    private File file;
    private String title;
    private String artist;
    private double length;

    public Song(File file) throws UnsupportedAudioFileException, IOException {
        this.file = file;
        this.title = file.getName();
        this.artist = "Desconocido";
        this.length = calculateLength();
    }

    public File getFile() {
        return file;
    }
    
    public void setFile() {
        this.file = file;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    private double calculateLength() throws UnsupportedAudioFileException, IOException {
        try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(file)) {
            AudioFormat format = audioStream.getFormat();
            long frames = audioStream.getFrameLength();
            return (frames / format.getFrameRate());
        } catch (Exception e) {
            System.err.println("Error al calcular duración: " + e.getMessage());
            return 0.0;
        }
    }
    
    public String getFormattedLength() {
        int minutes = (int) (length / 60);
        int seconds = (int) (length % 60);
        return String.format("%02d:%02d", minutes, seconds);
    }
}
