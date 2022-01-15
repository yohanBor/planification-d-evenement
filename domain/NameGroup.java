package domain;

//Value Object

/**
 * Class to define the value object NameGroup
 */
public class NameGroup extends Name {

    private final String nameGroup; //the name of the group

    public NameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }


    //important to override equals for values object (two objects are equals if all of their attributes are equals)
	public boolean equals(Object other){
		if(!(other instanceof NameGroup)){
			return false;
		}
		NameGroup otherGroup = (NameGroup) other; //cast the object in the linked type
		return this.nameGroup.equals((otherGroup.getNameGroup()));
	}

    //GETTERS

    private String getNameGroup() {
        return this.nameGroup;
    }

    public String getName() {
        return "Name group :" + this.nameGroup;
    }
}
