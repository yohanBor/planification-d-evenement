package domain;

//value object

public class Concert extends AbstractEvent {

	/**
	 * @param capacity la capacité souhaitée pour l'événement
	 * @param date la date du concert
	 * @param name le nom du groupe ou de l'artiste qui se produit
	 */
	public Concert(int capacity, Date date, Name name) {
		super(capacity, date, name);
	}

	//important to override equals for values object (two objects are equals if all of their attributes are equals)
	public boolean equals(Object other){
		if(!(other instanceof Concert)){
			return false;
		}
		Concert otherConcert = (Concert) other; //cast the object in the linked type
		return this.getCapacity() == (otherConcert.getCapacity()) && this.getDate().equals(otherConcert.getDate()) && this.toString().equals(otherConcert.toString());
	}

	


}
