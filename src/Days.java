import edu.princeton.cs.algs4.StdOut;

/**
 * Created by atlim on 9.4.2017.
 */
public class Days {

    private int day;
    private int month;
    private int year;

    public Days(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public void increment(){
        day++;
        if (day == 29 && month == 2){
            month++;
            day = 1;
        }
        if (day == 31) {
            if (month == 4 || month == 6 || month == 9 || month == 11) {
                month++;
                day = 1;
            }
        }
        if (day == 32){
            month++;
            day = 1;
        }
        if (month == 13){
            year++;
            month = 1;
        }
    }

    public String toString(){
        return day + "." + month + "." + year;
    }

    public static void main(String[] args){
        Days day = new Days(9,4,2017);
        for (int i = 0; i < 365; i++){
            day.increment();
            StdOut.println(day);
        }
    }
}
