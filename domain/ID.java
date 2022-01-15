package domain;

//value Object

/**
 * class to manage unique ID for entities object
 */
public class ID {

    private final int id;
    private static int cpt = 0; //to have a unique ID

    public ID() {
        this.id = cpt;
        cpt ++;
    }

    public String toString(){
        return this.id + "";
    }
}
