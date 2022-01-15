package domain;

//Value Object

/**
 * Class to define the value object Name Title for Drama's title
 */
public class NameTitle extends Name {

    private final String nameTitle; //the name of Drama's Title

    public NameTitle(String nameTitle) {
        this.nameTitle = nameTitle;
    }


    //important to override equals for values object (two objects are equals if all of their attributes are equals)
	public boolean equals(Object other){
		if(!(other instanceof NameTitle)){
			return false;
		}
		NameTitle otherGroup = (NameTitle) other; //cast the object in the linked type
		return this.nameTitle.equals((otherGroup.getNameTitle()));
	}

    //GETTERS
    public String getNameTitle() {
        return this.nameTitle;
    }

    public String getName() {
        return "Name Drama :" + this.nameTitle;
    }
}
