import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * la realisation de cette classe est base sur :
 * https://stackoverflow.com/questions/22118116/how-to-run-a-particular-task-every-friday-in-a-week-at-any-time-using-schedulede

 */

public class ProcedureComptable {
    public ProcedureComptable() {
        this.startSchedular();
    }

    //fonction qui s'execute a tous les vendredis a minuit
    private void startSchedular() {
        Calendar cal = Calendar.getInstance();
        Map<Integer,Integer> dayToDelay = new HashMap<Integer,Integer>();
        dayToDelay.put(Calendar.FRIDAY, 6);
        dayToDelay.put(Calendar.SATURDAY, 5);
        dayToDelay.put(Calendar.SUNDAY, 4);
        dayToDelay.put(Calendar.MONDAY, 3);
        dayToDelay.put(Calendar.TUESDAY, 2);
        dayToDelay.put(Calendar.WEDNESDAY, 1);
        dayToDelay.put(Calendar.THURSDAY, 0);

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);  //check what day it is today
        int hour = cal.get(Calendar.HOUR_OF_DAY);       //check what hour
        int delayInDays = dayToDelay.get(dayOfWeek);    //add delay to days
        int delayInHours = 0;
        if (delayInDays == 0 && hour < 24) {            //si c'est Jeudi
            delayInHours = 24 - hour;                   //on ajoute les heures restantes avant vendredi minuit
        } else {
            delayInHours = delayInDays*24 + ((24 - hour));
        }

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(new FichierTEF(), delayInHours, 179, TimeUnit.HOURS);
    }
}
