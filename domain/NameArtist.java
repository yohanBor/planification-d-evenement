package domain;

import java.io.IOException;
import java.util.regex.*;

//Value Object

public class NameArtist extends Name {
    private final String firstName;
    private final String lastName;


    public NameArtist(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        try {
            if (!Pattern.matches("[a-zA-Z]+", firstName) || !Pattern.matches("[a-zA-Z ]*", lastName)) { //check if the name is ok
                throw new IOException();
            }
        } catch (IOException e) {
            System.err.println("Illegal charactere in NameArtist declaration");
        }
    }

    //important to override equals for values object (two objects are equals if all of their attributes are equals)
    public boolean equals(Object other){
        if(!(other instanceof NameArtist)){
            return false;
        }
        NameArtist otherArtist = (NameArtist) other; //cast the object in the linked type
        return this.firstName.equals((otherArtist.getFirstName())) && this.lastName.equals(otherArtist.getLastName());
    }

    //GETTERS

    public String getName() {
        return "Name Artist :" + this.firstName + " " + this.lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


}