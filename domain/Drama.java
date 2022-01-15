package domain;

import java.util.ArrayList;

public class Drama extends AbstractEvent {

	/**
     * @param capacity la capacité souhaitée pour l'événement
     * @param date la date de la pièce (intervalle de dates)
     * @param name le nom du groupe ou de l'artiste qui se produit
     */
	public Drama(int capacity, ArrayList<Date> date, Name name) {
		super(capacity, date, name);
	}

	/**
	 *
	 * @param capacity
	 * @param date Utilisé seulement une fois la date decidée dans "Programmation.java
	 * @param name
	 */
	public Drama(int capacity, Date date, Name name) {
		super(capacity, date, name);
	}
	//important to override equals for values object (two objects are equals if all of their attributes are equals)
	public boolean equals(Object other){
		if(!(other instanceof Drama)){
			return false;
		}
		Drama otherDrama = (Drama) other; //cast the object in the linked type
		return this.getCapacity() == (otherDrama.getCapacity()) && this.getDate().equals(otherDrama.getDate()) && this.toString().equals(otherDrama.toString());
	}

}
