package domain;

//Entity

/**
 * Class to manage our only entity : Event
 */
public class Event {
    private final ID identity; // unique ID
    private final AbstractEvent event;
    private Room room; // room that hosts the event
    private Date date; // date of the event
    private int time; // hour of the event

    public Event(ID identity, AbstractEvent event, Room room, Date date) {
        this.identity = identity;
        this.event = event;
        this.room = room;
        this.date = date;
    }

    public String toString() {
        return "Event{" +
                "ID=" + identity +
                ", event=" + event +
                ", room=" + room +
                ", date=" + date + " :" + time +"h"+
                '}';
    }

    /**
     * So we can adapt the time of the event to the opening room
     * @param newHour
     */
    public void changeHour(int newHour){
        this.time = newHour;
    }
    // GETTERS

    public AbstractEvent getEvent() {
        return this.event;
    }

    public ID getIdentity() {
        return this.identity;
    }

    public Date getDate() {
        return this.date;
    }

    public Room getRoom() {
        return room;
    }
}
