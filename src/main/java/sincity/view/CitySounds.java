package sincity.view;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class CitySounds {

    public static void playSong() {
        String musicFile = "src/main/resources/citySong.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }
}