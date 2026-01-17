import  java.time.LocalTime;
public class Task {
    private String Taskname;
    private String  secheduletime;
    private boolean status;

     Task(String Taskname,String time){
         this.Taskname=Taskname;
         this.secheduletime=time;
         this.status=true;
     }

     public String getTaskName(){
         return Taskname;
     }
     public LocalTime taskTime(){

         return LocalTime.parse(secheduletime);
     }
     public void setStatus(boolean a){
         this.status=a;
     }
     @Override
    public String toString(){
         return "TaskName="+this.Taskname+"Sechduletime"+this.secheduletime+"status"+this.status;
     }




}
