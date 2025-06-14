package view;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.JOptionPane;

/**
 *
 * @author cchin
 */
public class MusicPlayer {

    private Clip clip;
    private boolean isPaused = false;
    private long pausePosition = 0;
    private File currentFile;
    private Runnable onSongFinished;

    public void setOnSongFinished(Runnable callback) {
        this.onSongFinished = callback;
    }

    public void play(File file) {
        try {

            if (file.equals(currentFile) && isPaused) {
                clip.setMicrosecondPosition(pausePosition);
                clip.start();
                isPaused = false;
                return;
            }

            resetClip();

            currentFile = file;

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);

            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP && !isPaused) {
                    resetClip();
                    if (onSongFinished != null) {
                        onSongFinished.run();
                    }
                }
            });

            clip.start();
            isPaused = false;

        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al reproducir: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void pause() {
        if (clip != null && clip.isRunning()) {
            pausePosition = clip.getMicrosecondPosition();
            clip.stop();
            isPaused = true;
        }
    }

    public void stop() {
        resetClip();
        isPaused = false;
        pausePosition = 0;
        currentFile = null;
    }

    public void resetClip() {
        if (clip != null) {
            clip.stop();
            clip.close();
            clip = null;
        }
    }

    public boolean isPlaying() {
        return clip != null && clip.isRunning();
    }

    public long getCurrentPosition() {
        if (clip != null) {
            return clip.getMicrosecondPosition();
        }
        return 0;
    }

    public long getTotalLength() {
        if (clip != null) {
            return clip.getMicrosecondLength();
        }
        return 0;
    }

}
