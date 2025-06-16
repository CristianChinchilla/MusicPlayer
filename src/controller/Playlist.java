package controller;

import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.sound.sampled.UnsupportedAudioFileException;
import model.Song;

/**
 *
 * @author José Daniel Segura Menjivar - C4J929
 * @author Cristian Chinchilla Fonseca - C4E360
 */
public class Playlist {

    private List<Song> songs;

    public Playlist() {
        this.songs = new ArrayList<>();
    }

    public void addSong(File audioFile) throws UnsupportedAudioFileException, IOException {
        Song song = new Song(audioFile);
        songs.add(song);
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void removeSong(int index) {
        if (index >= 0 && index < songs.size()) {
            songs.remove(index);
        }
    }


}
