import java.util.Calendar;
import java.util.GregorianCalendar;

public class test {
    public static void main(String[] args) {
        GregorianCalendar gc = new GregorianCalendar(2013, 8, 15, 24, 9, 55);
        p(gc, Calendar.YEAR);    //gives year

        p(gc, Calendar.MONTH);   // gives month staring at 0 for  January

        p(gc, Calendar.DATE);    // date

        p(gc, Calendar.DAY_OF_WEEK);// Sunday=1, Monday=2, .....Saturday -7

        p(gc, Calendar.WEEK_OF_MONTH);//what week its running in week ,whether its first or second;

        p(gc, Calendar.DAY_OF_WEEK_IN_MONTH);//In this case, How may times does Sunday is repeating in the month = 3;
        p(gc, Calendar.DAY_OF_YEAR);//count of the day in the year
        p(gc, Calendar.HOUR);//12 hour format. if the time is 22:09:55, answer would be (22-12)=10
        p(gc, Calendar.HOUR_OF_DAY);// hour of day that is 22 (24h format)
        p(gc, Calendar.MINUTE);// 09
        p(gc, Calendar.SECOND);// 55

        System.out.println();

        System.out.println(gc.getTime());
    }

    static void p(Calendar c, int type) {
        System.out.print(c.get(type) + "-");
    }
    int i=0;
    String s = "FichierTEF" + i++ + ".txt";
    
    System.out.println(s);
}
