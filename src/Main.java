import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;



//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ScheduledExecutorService shedules = Executors.newScheduledThreadPool(1);

        Scanner scan = new Scanner(System.in);
        Gson text = new Gson();
        String dbtask=null;
        File file = new File("data.json");
        ArrayList<Task> tasks;
        if(file.exists()){
        String fetch=Fetching();
        Type type = new TypeToken<ArrayList<Task>>() {}.getType();
        tasks=text.fromJson(fetch,type);
        }else{
            tasks=new ArrayList<>();
        }


        for(Task i:tasks){
            Runnable alarm=new Alarmaclock(i);
            long delay = Delay(i.taskTime());
            shedules.schedule(alarm,delay,TimeUnit.MILLISECONDS);
        }


    }
    public static long Delay(LocalTime Tasktime){
        LocalDateTime now;
        now = LocalDateTime.now();
        LocalDateTime target=now.with(Tasktime);
        if (target.isBefore(now)){
            target.plusDays(1);
        }
        return Duration.between(now,target).toMillis();


    }





    public static void alarm(Task task){
        System.out.println(task.getTaskName());
        System.out.println("Task need to Complete");
    }
    public static void Saving(String task){
        try {
            FileWriter writer= new FileWriter("data.json");
            writer.write(task);
            writer.close();
        }
        catch (IOException e){
            System.out.println("Failed to save the file "+e);
        }


    }
    public static String Fetching(){
        StringBuilder sb= new StringBuilder();
        try{
            FileReader reader = new FileReader("data.json");
            int ch;
            while((ch=reader.read())!=-1){
                sb.append((char) ch);
            }
            reader.close();

        }
        catch (IOException e){
            System.out.print("Failed to Fetch the data "+e);
        }
        return sb.toString();

    }
}





