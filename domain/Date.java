package domain;

import java.time.LocalDate;
import java.util.Objects;

public class Date {
    private final int year;
    private final int month;
    private final int day;

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
        LocalDate currentdate = LocalDate.now();
        try {
            // Check year
            if(this.year < currentdate.getYear())
                throw new Exception();

            // Check month
            else if(this.year == currentdate.getYear() && this.month < currentdate.getMonthValue())
                throw new Exception();

            else if(this.month == currentdate.getMonthValue() && this.day < currentdate.getDayOfMonth())
                throw new Exception();
            // CHECK nombre mois et jours (On generalise que chaque mois contient 31 jours)
            else if(this.month > 12 || this.day > 31)

                throw new Exception();
        }catch (Exception e){
            System.err.println("Invalid date");
        }
    }

    //checks if this.date is after dateToCompare
    public boolean isAfter(Date dateToCompare){
        if (this.year > dateToCompare.getYear())
            return true;
        
        if (this.year != dateToCompare.getYear()){
            return false;
        }
        if (this.year == dateToCompare.getYear()){
            if (this.getMonth() > dateToCompare.getMonth())
                return true;
            else if (this.getMonth() == dateToCompare.getMonth()){
                if (this.getDay() >= dateToCompare.getDay())
                    return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return year + "-"+ month + "-" + day;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Date)) return false;
        Date date = (Date) o;
        return this.year == date.getYear() && this.month == date.getMonth() && this.day == date.getDay();
    }

    public int getDay() {
        return this.day;
    }

    public int getMonth(){
        return this.month;
    }

    public int getYear(){
        return  this.year;
    }


}
