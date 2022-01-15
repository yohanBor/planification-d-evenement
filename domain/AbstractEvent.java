package domain;
import java.util.ArrayList;
import java.util.Objects;


/**
 * class to link the 2 values objects : Concert & Drama
 */
public abstract class AbstractEvent {
    private final Date date; //date of the event
    private final Name name; //name of the group / artist / title of the drama
    private final int capacity; //capacity that wants the group to have in the room
    private final ArrayList<Date> interval;

    public AbstractEvent(int capacity, Date date, Name name) {
        this.capacity = capacity;
        this.date = date;
        this.name = name;
        interval = null;
    }

    public AbstractEvent(int capacity, ArrayList<Date> interval, Name title) {
        this.capacity = capacity;
        this.date = null;
        this.name = title;
        this.interval = interval;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractEvent)) return false;
        AbstractEvent that = (AbstractEvent) o;
        return getCapacity() == that.getCapacity() && Objects.equals(getDate(), that.getDate()) && Objects.equals(getName(), that.getName()) && Objects.equals(interval, that.interval);
    }

    //GETTERS

    //to know the beginning of the drama (because it's an interval)
    public Date getArrayListOpen(){
        return interval.get(0);
    }

    public Date getDate() {
        return this.date;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public String toString() {
        return this.name.getName();
    }

    public Name getName(){
        return this.name;
    }

    public ArrayList<Date> getInterval() {
        return this.interval;
    }
}
