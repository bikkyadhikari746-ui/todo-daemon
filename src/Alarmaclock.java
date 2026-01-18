import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import com.google.gson.Gson;


public class Alarmaclock implements  Runnable{
    private Task task;
    private ArrayList tasks;
    public Alarmaclock(Task task, ArrayList tasks){
        this.task=task;
        this.tasks=tasks;

    }
    @Override
    public void run() {
        System.out.println("Task need to complete "+ task.getTaskName());
        System.out.print("IT time "+ task.taskTime());
        System.out.println(" ");
        try {
            sound();
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }

        task.setStatus(false);
        Main.cleanlist(tasks);
        Main.Saving(tasks);




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
