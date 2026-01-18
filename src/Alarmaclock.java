public class Alarmaclock implements  Runnable{
    private Task task;

    public Alarmaclock(Task task){
        this.task=task;
    }
    @Override
    public void run() {
        System.out.println("Alarm is ringed");
        System.out.println("Task need to complete "+ task.getTaskName());
        task.setStatus(false);

    }
}
