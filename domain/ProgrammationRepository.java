package domain;

import application.Programmation;

/**
 *  Interface that allows the domain to say that it permits to save/load itself
 */
public interface ProgrammationRepository {

    void save(Programmation programmation); //possible to save an aggregate
    void update(Programmation programmation); //possible to update an aggregate
    Programmation findProgById(ID ProgId); //load an aggregate thanks to its ID
    
}
