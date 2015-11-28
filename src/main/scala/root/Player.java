package root;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;

/**
 * @author D.Tolpekin
 */
public class Player {
    public static final String AUDIO_FILE = "icq.mp3";

    public static synchronized void play() throws Exception {
        new JFXPanel();
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(Paths.get(AUDIO_FILE).toUri().toString()));
        mediaPlayer.play();
        Thread.sleep(1000);
        mediaPlayer.stop();
    }
}
