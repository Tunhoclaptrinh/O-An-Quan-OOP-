package GameGUI;

import javax.sound.sampled.*;
        import java.io.File;
import java.io.IOException;

public class MusicPlayer {
    private Clip clip;

    // Phương thức để load file nhạc
    public void playMusic(String musicFilePath) {
        try {
            // Tạo đường dẫn tới file nhạc
            File musicFile = new File(musicFilePath);
            if (musicFile.exists()) {
                // Mở file âm thanh
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);

                // Lấy đối tượng Clip để phát nhạc
                clip = AudioSystem.getClip();
                clip.open(audioStream);

                // Phát nhạc
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY); // Lặp liên tục
            } else {
                System.out.println("Không tìm thấy file nhạc: " + musicFilePath);
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Phương thức dừng nhạc
    public void stopMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}
