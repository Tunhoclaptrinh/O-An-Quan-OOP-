package GameGUI;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicPlayer {
    private Clip clip;

    // Phương thức để phát nhạc lặp lại
    public void playMusic(String musicFilePath) {
        try {
            // Tạo đường dẫn tới file nhạc
            File musicFile = new File(musicFilePath);
            if (musicFile.exists()) {
                // Mở file âm thanh
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);

                // Đóng clip trước đó nếu đã mở
                if (clip != null && clip.isOpen()) {
                    clip.close();
                }

                // Tạo clip mới và mở tệp âm thanh
                clip = AudioSystem.getClip();
                clip.open(audioStream);

                // Lặp nhạc liên tục
                clip.loop(Clip.LOOP_CONTINUOUSLY); // Đặt lặp trước
                clip.start(); // Phát nhạc sau
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

    // Phương thức kiểm tra trạng thái nhạc
    public boolean isPlaying() {
        return clip != null && clip.isRunning();
    }

    // Đường dẫn tới tệp nhạc
    public static String musicPath = "D:/0. study_material/Javahoidanit_moitruong/Ptit/Test_structure_and_GUI/src/Assets/sound/pixel-song-16-31697.wav";
    public static String pressMusicPath = "D:/0. study_material/Javahoidanit_moitruong/Ptit/Test_structure_and_GUI/src/Assets/sound/retro-coin-4-236671.wav";

//    public static void playMusic() {
//        // Tạo đối tượng phát nhạc
//        MusicPlayer player = new MusicPlayer();
//
//        // Phát nhạc lặp lại
//        player.playMusic(musicPath);
//        //
//
//        // Phát nhạc lặp lại
//        player.playMusic(musicPath);
//
//        // Giữ chương trình chạy để nghe nhạc
//        // Giữ chương trình chạy (vô hạn)
//        while (true) {
//            try {
//                Thread.sleep(1000); // Giữ chương trình chạy
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public void playPressMusic(String musicFilePath) {
        try {
            // Tạo đường dẫn tới file nhạc
            File musicFile = new File(musicFilePath);
            if (musicFile.exists()) {
                // Mở file âm thanh
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);

                // Đóng clip trước đó nếu đã mở
                if (clip != null && clip.isOpen()) {
                    clip.close();
                }

                // Tạo clip mới và mở tệp âm thanh
                clip = AudioSystem.getClip();
                clip.open(audioStream);

                clip.start(); // Phát nhạc sau
            } else {
                System.out.println("Không tìm thấy file nhạc: " + musicFilePath);
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
