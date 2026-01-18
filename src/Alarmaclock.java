import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;


public class Alarmaclock implements  Runnable{
    private Task task;
    public Alarmaclock(Task task){
        this.task=task;
    }
    @Override
    public void run() {
        try {
            sound();
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Task need to complete "+ task.getTaskName());
        task.setStatus(false);

    }
    private static void sound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File music = new File("/home/bikky/Downloads/sound.wav");
        AudioInputStream audio = AudioSystem.getAudioInputStream(music);
        Clip clip = AudioSystem.getClip();
        clip.open(audio);
        clip.start();
        clip.drain();
        clip.close();

    }
}
