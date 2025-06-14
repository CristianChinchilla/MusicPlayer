package controller;

import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.sound.sampled.UnsupportedAudioFileException;
import model.Song;

/**
 *
 * @author cchin
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

//    public void precarga() {
//        musicList.addLast(new Song());
//        songIterator = musicList.listIterator();
//        currentSong = songIterator.next();
//    }
//
//    private void resetIterator() {
//        if (musicList.isEmpty()) {
//            currentSong = null;
//            songIterator = musicList.listIterator();
//        } else {
//            int currentIndex = currentSong != null ? musicList.indexOf(currentSong) : 0;
//            songIterator = musicList.listIterator(currentIndex);
//            iteratorValid = true;
//        }
//    }
//
//    public void addLast(String title, String artist, int lenght) {
//        musicList.addLast(new Song(title, artist, lenght));
//        iteratorValid = false;
//    }
//
//    public void addFirst(String title, String artist, int lenght) {
//        boolean wasAtBeginnig = !songIterator.hasPrevious();
//        iteratorValid = false;
//    }
//
//    public void play(boolean isAutoplayActive) throws InterruptedException {
//        if (!iteratorValid) {
//            resetIterator();
//        }
//
//        if (musicList.isEmpty()) {
//            System.out.println("No hay canciones en la lista de reproducción.");
//            return;
//                    
//                    
//        }
//
//        if (isAutoplayActive) {
//            ListIterator<Song> autoIterator = musicList.listIterator();
//            while (autoIterator.hasNext()) {
//                currentSong = autoIterator.next();
//                playCurrentSong();
//            }
//        } else {
//            if (currentSong == null && !musicList.isEmpty()) {
//                currentSong = musicList.getFirst();
//            }
//            if (currentSong != null) {
//                playCurrentSong();
//            }
//        }
//    }
//
//    public void playCurrentSong() throws InterruptedException {
//        System.out.println("Reproducciendo: " + currentSong.getTitle());
//        for (int i = 0; i < currentSong.getLength(); i++) {
//            System.out.println(i + 1);
//            Thread.sleep(1000);
//        }
//    }
//
//    public void hasNext() {
//        if (!iteratorValid) {
//            resetIterator();
//        }
//
//        if (songIterator.hasNext()) {
//            currentSong = songIterator.next();
//            System.out.println("Siguiente canción: " + currentSong.getTitle());
//        } else {
//            System.out.println("No hay más canciones siguientes");
//            if (!musicList.isEmpty()) {
//                songIterator = musicList.listIterator(musicList.size());
//                currentSong = musicList.getLast();
//            }
//        }
//    }
//
//    public void hasPrevious() {
//        if (!iteratorValid) {
//            resetIterator();
//        }
//
//        if (songIterator.hasPrevious()) {
//            currentSong = songIterator.previous();
//            System.out.println("Canción anterior: " + currentSong.getTitle());
//        } else {
//            System.out.println("No hay canciones anteriores");
//            if (!musicList.isEmpty()) {
//                songIterator = musicList.listIterator();
//                currentSong = musicList.getFirst();
//            }
//        }
//    }
//
//    public void deleteSong(String songName) {
//        if (musicList.isEmpty()) {
//            System.out.println("La lista esta vacía.");
//            return;
//        }
//
//        boolean found = false;
//        Iterator<Song> deleteIterator = musicList.iterator();
//        while (deleteIterator.hasNext()) {
//            Song searchedSong = deleteIterator.next();
//            if (searchedSong.getTitle().equalsIgnoreCase(songName)) {
//                deleteIterator.remove();
//                System.out.println("Canción eliminada: " + searchedSong.getTitle());
//
//                iteratorValid = false;
//
//                if (currentSong != null && currentSong.equals(searchedSong)) {
//                    currentSong = null;
//                }
//                return;
//            }
//        }
//        System.out.println("No se encontro la canción.");
//    }
//
//    public void showPlaylist() {
//        if (musicList.isEmpty()) {
//            System.out.println("La lista esta vacía.");
//            return;
//        }
//
//        System.out.println("Lista de canciones: ");
//        for (Song song : musicList) {
//            System.out.println(song);
//        }
//    }
//    
//    public LinkedList<Song> getMusicList() {
//        return musicList;
//    }
}
