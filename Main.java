import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import application.Programmation;
import domain.*;
import repository.ProgrammationInMemory;
import userInteface.UiTerminal;

public class Main {
    public static void main(String[] args) {

        // Room available for programming events in JolieCité
        Room room1 = new Room(500,  new TimeSlot(new Date(2022, 01, 01), 19, 24 ));
        Room room2 = new Room(1000, new TimeSlot(new Date(2022, 01, 01), 19, 24 ));
        Room room3 = new Room(1500, new TimeSlot(new Date(2022, 01, 01), 19, 24 ));
        Room room4 = new Room(2000, new TimeSlot(new Date(2022, 01, 01), 19, 24 ));

        // Programmation each month, here january
        Programmation programmation = new Programmation(01, 2022);

        // make rooms available to programmation (for the aggregate)
        programmation.addRoom(room1);
        programmation.addRoom(room2);
        programmation.addRoom(room3);
        programmation.addRoom(room4);


        // Semaine 0 [0-7], bien remplie
        AbstractEvent event1 = new Concert(750, new Date(2022, 01, 1), new NameArtist("David", "Guetta"));
        AbstractEvent event2 = new Concert(1300, new Date(2022, 01, 2), new NameGroup("ACDC"));
        AbstractEvent event3 = new Concert(250, new Date(2022, 01, 3), new NameGroup("Queen"));
        AbstractEvent event4 = new Drama(500, new ArrayList<Date>(List.of(new Date(2022, 01, 4), new Date(2022, 01, 10))), new NameTitle("Romeo et Juliette"));
        // Date choisi le 8 janvier (car ca sera un samedi --> Week-end favorisé)
        AbstractEvent event5 = new Concert(500, new Date(2022, 01, 5), new NameArtist("Johnny", "hallyday"));
        AbstractEvent event6 = new Drama(250, new ArrayList<Date>(List.of(new Date(2022, 01, 6), new Date(2022, 01, 11))), new NameTitle("Le Malade Imaginaire"));
        // Date choisi le 9 janvier (car ca sera un dimanche --> Week-end favorisé, et que le 8 est deja pris)
        AbstractEvent event7 = new Concert(500, new Date(2022, 01, 7), new NameGroup("The Beatles"));

        // Semaine 1 [7-14], concert dans la semaine planifié mais seulement 2 salles utilisé !
        AbstractEvent event8 = new Drama(250, new ArrayList<Date>(List.of(new Date(2022, 01, 10), new Date(2022, 01, 15))), new NameTitle("Huit clos"));
        // Date choisi le 10 janvier, car pas de week-end pendant l'intervale
        AbstractEvent event9 = new Concert(1200, new Date(2022, 01, 11), new NameArtist("Elvis", "Presley"));
        AbstractEvent event10 = new Concert(500, new Date(2022, 01, 13), new NameArtist("Michael", "Jackson"));

        // Semaine 2 [14-21], semaine tres prisée par les concerts, pas tous ne pourront être planifé !
        AbstractEvent event11 = new Concert(1005, new Date(2022, 01, 14), new NameArtist("Madonna", " "));
        AbstractEvent event12 = new Concert(1000, new Date(2022, 01, 15), new NameArtist("Led", "Zeppelin"));
        AbstractEvent event13 = new Concert(500, new Date(2022, 01, 16), new NameArtist("Elvis", "Presley"));
        AbstractEvent event14 = new Concert(1600, new Date(2022, 01, 17), new NameArtist("Rihanna", " "));
        AbstractEvent event15 = new Concert(500, new Date(2022, 01, 18), new NameGroup("The Beatles"));
        AbstractEvent event16 = new Concert(1100, new Date(2022, 01, 19), new NameArtist("David", "Guetta"));
        AbstractEvent event17 = new Concert(500, new Date(2022, 01, 20), new NameGroup("ACDC"));
        AbstractEvent event18 = new Concert(1900, new Date(2022, 01, 21), new NameArtist("Elvis", "Presley"));
        // Ces 2 concerts ne pourront être programmé car la salle à 1900 capacité est deja prise le 21
        AbstractEvent event19 = new Concert(1900, new Date(2022, 01, 21), new NameGroup("Queen"));
        AbstractEvent event20 = new Concert(1900, new Date(2022, 01, 21), new NameArtist("Orelsan", " "));

        // Semaine 3 [21-28], semaine qui respecte les conditions ( 3 concerts dans la semaine + 3 salles utilisées)
        AbstractEvent event21 = new Concert(1900, new Date(2022, 01, 23), new NameGroup("The Beatles"));
        AbstractEvent event22 = new Concert(1000, new Date(2022, 01, 24), new NameGroup("Queen"));
        AbstractEvent event23 = new Concert(200, new Date(2022, 01, 25), new NameArtist("Madonna", " "));

        // Semaine 4 [28-31], que des pieces de theatre, aucune contrainte respecté :(
        AbstractEvent event24 = new Drama(250, new ArrayList<Date>(List.of(new Date(2022, 01, 28), new Date(2022, 01, 31))), new NameTitle("Huit clos"));
        AbstractEvent event25 = new Drama(1250, new ArrayList<Date>(List.of(new Date(2022, 01, 28), new Date(2022, 01, 31))), new NameTitle("En attendant Godot"));
        AbstractEvent event26 = new Drama(800, new ArrayList<Date>(List.of(new Date(2022, 01, 28), new Date(2022, 01, 31))), new NameTitle("Britannicus"));

        // Ajout hors mois ! Ne seras pas inclu dans la programmation
        AbstractEvent event27 = new Concert(200, new Date(2025, 01, 25), new NameArtist("Madonna", " "));

        // Ajout avant l'ouverture des salles ! Ne seras pas inclu dans la programmation
        AbstractEvent event28 = new Concert(200, new Date(2020, 01, 25), new NameGroup("The Beatles"));


        // adding events to week 1
        programmation.addEvent(event1);
        programmation.addEvent(event2);
        programmation.addEvent(event3);
        programmation.addEvent(event4);
        programmation.addEvent(event5);
        programmation.addEvent(event6);
        programmation.addEvent(event7);
        programmation.addEvent(event8);
        programmation.addEvent(event9);
        programmation.addEvent(event10);
        programmation.addEvent(event11);
        programmation.addEvent(event12);
        programmation.addEvent(event13);
        programmation.addEvent(event14);
        programmation.addEvent(event15);
        programmation.addEvent(event16);
        programmation.addEvent(event17);
        programmation.addEvent(event18);
        programmation.addEvent(event19);
        programmation.addEvent(event20);
        programmation.addEvent(event21);
        programmation.addEvent(event22);
        programmation.addEvent(event23);
        programmation.addEvent(event24);
        programmation.addEvent(event25);
        programmation.addEvent(event26);
        programmation.addEvent(event27);
        programmation.addEvent(event28);
        // the user lauches the programmation for month 1
        programmation.scheduleEvent();

        // Repository
//        ProgrammationInMemory ProgRepository = new ProgrammationInMemory(); // create the repository of aggregates
//        ProgRepository.save(programmation); //save current programmation
//        ProgRepository.showMemory(); //checks if the memory contains our object

        //to try (basic) user interface
//        UiTerminal userInterface = new UiTerminal(01, 2022);
//        userInterface.userInteface();
    }
}
