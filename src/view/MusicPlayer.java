package view;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.JOptionPane;
import controller.PlayerState;

/**
 *
 * @author cchin
 */
public class MusicPlayer {

    private Clip clip;
    private volatile PlayerState state = PlayerState.STOPPED;
    private long pausePosition = 0;
    private File currentFile;
    private Runnable onSongFinished;
    private final Object lock = new Object();

    public void setOnSongFinished(Runnable callback) {
        this.onSongFinished = callback;
    }

    public void play(File file) throws IOException, LineUnavailableException, UnsupportedAudioFileException {

        synchronized (lock) {
            
            //if (state == PlayerState.PLAYING) return;

            if (state == PlayerState.PAUSED && file.equals(currentFile)) {
                resume();
                return;
            }

            resetClip();
            
            currentFile = file;

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);

            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP && state == PlayerState.PLAYING) {
                    resetClip();
                    if (onSongFinished != null) {
                        onSongFinished.run();
                    }
                }
            });

            clip.start();
            state = PlayerState.PLAYING;
        }
    }

    public void pause() {
        synchronized (lock) {
            if (clip != null && state == PlayerState.PLAYING) {
                pausePosition = clip.getMicrosecondPosition();
                clip.stop();
                state = PlayerState.PAUSED;
            }
        }
    }

    public void resume() {
        synchronized (lock) {
            if (clip != null && state == PlayerState.PAUSED) {
                clip.setMicrosecondPosition(pausePosition);
                clip.start();
                state = PlayerState.PLAYING;
            }
        }
    }

    public void stop() {
        synchronized (lock) {
            resetClip();
            state = PlayerState.STOPPED;
        }
    }

    public void resetClip() {
        if (clip != null) {
            clip.stop();
            clip.close();
            clip = null;
        }
        pausePosition = 0;
        state = PlayerState.STOPPED;
    }

    public boolean isPlaying() {
        return state == PlayerState.PLAYING;
    }
    
    public PlayerState getState() {
        return state;
    }
    
    public long getCurrentPosition() {
        synchronized (lock) {
            if (clip != null && clip.isOpen()) {
                return clip.getMicrosecondPosition();
            } else {
                return pausePosition;
            }
        }
    }

    public long getTotalLength() {
        if (clip != null && clip.isOpen()) {
            return clip.getMicrosecondLength();
        } else {
            return 0;
        }
    }

    public File getCurrentFile() {
        return currentFile;
    }
}
