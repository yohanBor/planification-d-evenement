package domain;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;

//valueObject

/**
 * Class to represent the business object "Room"
 */
public class Room {
	private final int capacity; // the capacity the room can hosts
	private final TimeSlot slots; // opening date of the room and open-close hours
	private final ID idEvent; // Utilisé lors de l'attribution d'un evenement à la salle

	public Room(int capacity, TimeSlot slots) {
		this.capacity = capacity;
		this.slots = slots;
		this.idEvent = null;
	}

	public Room(int capacity, TimeSlot slots, ID idEvent) {
		this.capacity = capacity;
		this.slots = slots;
		this.idEvent = idEvent;
	}

	public String toString() {
		return "Room{" +
				"capacity=" + capacity +
				", slots=" + slots +
				'}';
	}

	// important to override equals for values object (two objects are equals if all
	// of their attributes are equals)
	public boolean equals(Object other) {
		if (!(other instanceof Room)) {
			return false;
		}
		Room otherRoom = (Room) other; // cast the object in the linked type
		return this.getCapacity() == (otherRoom.getCapacity()) && this.getSlots().equals(otherRoom.getSlots());
	}

	// GETTERS
	public int getCapacity() {
		return this.capacity;
	}

	public TimeSlot getSlots() {
		return this.slots;
	}

	public int getClosedTime() {
		return slots.getCloseHour();
	}

}
