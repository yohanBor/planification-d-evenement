package domain;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

//valueObject

/**
 * Class to manage open dates / closing times.
 */
public class TimeSlot {
    private final Date opendate;
    private final int openingTime;
    private final int closingTime;

    public TimeSlot(Date opendate,int openingTime, int closingTime) {
        this.opendate = opendate;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        try {
            if (closingTime < 0 || closingTime > 24 || openingTime < 0 || openingTime > closingTime)
                throw new Exception(); // check if format time is ok
        } catch (Exception e) {
            System.err.println("Illegal Integer in TimeSlot declaration");
        }
    }
    public String toString() {
        return opendate + " :" + openingTime + "h-->" + closingTime +"h";
    }

    // important to override equals for values object (two objects are equals if all
    // of their attributes are equals)
    public boolean equals(Object other) {
        if (!(other instanceof TimeSlot)) {
            return false;
        }
        TimeSlot otherTime = (TimeSlot) other; // cast the object in the linked type
        return this.opendate.equals(otherTime.getOpenDate()) && this.closingTime == otherTime.getCloseHour();
    }

    // GETTERS

    public Date getOpenDate() {
        return opendate;
    }

    public int getOpeningTime(){
        return this.openingTime;
    }
    public int getCloseHour() {
        return this.closingTime;
    }

}
