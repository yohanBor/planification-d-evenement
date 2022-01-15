package application;

import java.security.cert.CollectionCertStoreParameters;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

import domain.AbstractEvent;
import domain.Concert;
import domain.Date;
import domain.Drama;
import domain.Event;
import domain.ID;
import domain.Room;

//Aggregate (Entity composite)
//object used by the customer
//visible from the domain : used by Application
public class Programmation {

    // Value Object used to create our entity, from this agregate
    private final ID id = new ID();
    /**
     * Events to schedule
     */
    private ArrayList<AbstractEvent> listEvent;
    /**
     * Rooms that can accommodate an event
     */
    private ArrayList<Room> listRoom = new ArrayList<Room>();
    /**
     * Only used for programming
     */
    private final HashMap<Room, AbstractEvent> programme = new HashMap<Room, AbstractEvent>();


    private final int month;
    private final int year;

    public Programmation(int month, int year) {
        listEvent = new ArrayList<AbstractEvent>();
        this.month = month;
        this.year = year;
    }

    //réinitialise la liste des évènements
    public void deleteAllEvent(){
        listEvent = new ArrayList<AbstractEvent>();
        System.out.println("Réinitialisation ok");
    }

    /**
     * Add a room to the list
     *
     * @param room
     */
    public void addRoom(Room room) {
        if (listRoom.size() >= 4) {
            System.err.println("Not enough place to add a room : limited 4");
        } else
            listRoom.add(room);
    }

    /**
     * Remove a room from the list of room
     *
     * @param room
     */
    public void deleteRoom(Room room) {
        listRoom.remove(room);
    }

    /**
     * Add an event to schedule
     *
     * @param event
     */
    public void addEvent(AbstractEvent event) {
        listEvent.add(event);
    }

    /**
     * Remove an event
     *
     * @param event
     */
    public void deleteEvent(AbstractEvent event) {
        listEvent.remove(event);
    }

    //try to schedule event during week-end
    private Drama setDate(Drama drama, Room roomCity) {
        // Si on lui a deja affectué une date
        if(drama.getDate() != null)
            return drama;
        domain.Date dateFrom = drama.getInterval().get(0);
        domain.Date dateTo = drama.getInterval().get(1);
        // Regarde si une salle est disponible le Week-end
        if (drama.getDate() == null) {
            for (int day = dateFrom.getDay(); day < dateTo.getDay(); day++) {
                domain.Date dateTest = new Date(dateFrom.getYear(), dateFrom.getMonth(), day);
                LocalDate findDay = LocalDate.of(dateFrom.getYear(), dateFrom.getMonth(), day);
                if (findDay.getDayOfWeek() == DayOfWeek.SATURDAY || findDay.getDayOfWeek() == DayOfWeek.SUNDAY) {
                    Drama eventToTest = new Drama(drama.getCapacity(), dateTest, drama.getName());
                    // Test si la salle a deja un programme pour cette date
                    if (checkRoomIsOkayForEvent(eventToTest, roomCity)) {
//                        System.out.println("Plannifié le weekend :" + dateFrom + " new --> " + eventToTest.getDate());
                        return eventToTest;
                    }
                }
            }
        }
        // Si pas possible le weekend, on lui affectionne la premiere date de son interval
        return new Drama(drama.getCapacity(), dateFrom, drama.getName());
    }
    /**
     * Check if the conditions are respected to add an event to a specific room.
     * 
     * @param eventToAdd try to add this event
     * @param roomCity  try to add the event in this room
     * @return if the event can be schedule
     */
    private boolean checkRoomIsOkayForEvent(AbstractEvent eventToAdd, Room roomCity) {

        if(eventToAdd.getDate().getYear() > this.year || this.month != eventToAdd.getDate().getMonth())
            return false;

        // Tests if the room has sufficient capacity for the event.
        if (roomCity.getCapacity() < eventToAdd.getCapacity())
            return false;

        // Tests if the event is planned before the opening date of the roomCity
        if (!eventToAdd.getDate().isAfter(roomCity.getSlots().getOpenDate())) {
            return false;
        }

        // Test one event per room and per day
        for (Map.Entry<Room, AbstractEvent> entry : programme.entrySet()) {
            Room key = entry.getKey();
            AbstractEvent value = entry.getValue();
            if (value.getDate().equals(eventToAdd.getDate())) {
                return false;
            }
        }
        return true;
    }

        /**
         * Create a list of events based on the abstract events and the rooms entered by
         * the client.
         *
         * @return the list of events that will be used by the client
         */
    public ArrayList<Event> scheduleEvent() {
        System.out.println("Id de la programmation : " + this.getId());
        ArrayList<Event> events = new ArrayList<Event>();
        for (AbstractEvent abstractEvent : listEvent) {
            for (Room roomCity : listRoom) {
                if(abstractEvent instanceof Drama)
                    abstractEvent = setDate((Drama) abstractEvent, roomCity);

                if (checkRoomIsOkayForEvent(abstractEvent, roomCity)) {
                    ID idEvent = new ID();
                    Room roomEvent = new Room(roomCity.getCapacity(), roomCity.getSlots(),idEvent);
                    programme.put(roomEvent, abstractEvent); // Associate an event to the room
                    // Plan the event
                    Event event = new Event(idEvent, abstractEvent, roomCity, abstractEvent.getDate());
                    event.changeHour(roomCity.getSlots().getOpeningTime());
                    events.add(event);
                    break;
                }
            }
        }
        System.out.println("Events programmed :");
        for (Event event : events) {
            System.out.println(event);
        }
        System.out.println("");
        for(int week_i = 0;  week_i <= 4; week_i ++){
            System.out.println("Semaine" + week_i);
            boolean oneConcertperWeek = false;
            ArrayList<Room> RoomToTest = new ArrayList<Room>(listRoom); // recupere la liste des salles, pour tester un concert par salle par semaine
            // Verifie qu'il y ait au moins un concert par semaine et par salle.
            for (int day = 0; day <= 7 && week_i * 7 + day <= 31; day++) {
                for (Room testRoom : listRoom) {
                    for (Event event : events) {
                        if (event.getEvent() instanceof Concert) {
                            // Si le concert est bien le même mois que celui de notre programmation au mois
                            if (event.getDate().getMonth() == this.month) {

                                // Verifie qu'il y ait un jour durant la semaine en cours (test sur chaque semaine) ou un concert est programmé
                                if (event.getDate().getDay() == week_i * 7 + day && event.getRoom() == testRoom ) {
                                    System.out.println("Il y a bien un concert le jour " + event.getDate().getDay() + " avec " + event.getEvent().getName().getName() + " dans la salle à " + event.getRoom().getCapacity() + " capacité");
                                    oneConcertperWeek = true;
                                    RoomToTest.remove(testRoom);
                                }
                            }
                        }
                    }
                }
            }
            if(!oneConcertperWeek) {
                if(week_i == 4)
                    System.out.println("ATTENTION : Non respect d'un concert par semaine, pour la semaine du [" + week_i * 7+ "-31] Revoyez votre plannification.");
                else
                    System.out.println("ATTENTION : Non respect d'un concert par semaine, pour la semaine du [" + week_i * 7+ "-" + (week_i * 7 +7) + "] Revoyez votre plannification.");
            }
            if(RoomToTest.size() > 1)
                System.out.println("ATTENTION : Moins de 3 salles ont été utilisées.");
            System.out.println(""); // Saut de ligne
        }
        return events;
    }

    @Override
    public String toString() {
        for (AbstractEvent event : listEvent) {
            System.out.println(event);
        }
        return "";
    }

    public ID getId() {
        return id;
    }

}
