package userInteface;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import application.Programmation;
import domain.AbstractEvent;
import domain.Concert;
import domain.Date;
import domain.Drama;
import domain.NameArtist;
import domain.NameGroup;
import domain.Room;
import domain.TimeSlot;

public class UiTerminal {

    Programmation programmation;

    public UiTerminal(int month, int year){
        this.programmation = new Programmation(month, year);
    }

    //FOR USER INTERFACE

    //scanner string
	public String inputString() {
		Scanner in = new Scanner(System.in);
		return in.nextLine();
	}

    //scanner int
	public int inputInt() {
		Scanner in = new Scanner(System.in);
		return Integer.parseInt(in.nextLine());
	}

    private void createEventFromUser() {
        System.out.println("What kind of event do you want to create ?");
        System.out.println("1 : Concert \n 2 : Drama");
        String answer = inputString();
        if (answer.equals("1"))
            this.createConcertFromUser();
        else if(answer.equals("2"))
            this.createDramaFromUser();
        else{
            System.out.println("Incorrect choice");
            this.createEventFromUser(); //looping
        }
    }

    private void createDramaFromUser() {

        AbstractEvent eventToCreate;
        System.out.println("How many people do you want for the concert ? ");
        int nbPeople = inputInt();
        int yearBegin,monthBegin,dayBegin;

        System.out.println("Please Give the date intervalle");
        System.out.println("Please enter the year (begin) of the concert");
        yearBegin = inputInt();
        System.out.println("Please enter the month (begin) of the concert (ex: jan --> 01)");
        monthBegin = inputInt();
        System.out.println("Please enter the day (begin) of the concert");
        dayBegin = inputInt();

        int yearEnd,monthEnd,dayEnd;
        System.out.println("Please enter the year (end) of the concert");
        yearEnd = inputInt();
        System.out.println("Please enter the month (end) of the concert (ex: jan --> 01)");
        monthEnd = inputInt();
        System.out.println("Please enter the day (end) of the concert");
        dayEnd = inputInt();
        
        ArrayList<Date> dateInterval = new ArrayList<Date>(List.of(new Date(yearBegin, monthBegin, dayBegin), new Date(yearEnd, monthEnd, dayEnd)));

        System.out.println("Is it a group or an artist ?");
        System.out.println("1 : Group");
        System.out.println("2 : Artist");
        int groupOrArtist = inputInt();
        if (groupOrArtist == 1){
            System.out.println("What's the name of the group ?");
            String name = inputString();
            NameGroup nameGroup = new NameGroup(name);
            eventToCreate = new Drama(nbPeople,dateInterval,nameGroup);
            programmation.addEvent(eventToCreate);
            this.userInteface(); //back to the menu
        }
        else if (groupOrArtist == 2){
            System.out.println("What's the first name of the artist ?");
            String nameArtist = inputString();
            System.out.println("What's the last name of the artist ?");
            String lastName = inputString();
            NameArtist nameOfArtist = new NameArtist(nameArtist, lastName);
            eventToCreate = new Drama(nbPeople, dateInterval, nameOfArtist);
            programmation.addEvent(eventToCreate);
            this.userInteface();
        }
        else {
            System.out.println("Incorrect choice");
            this.createConcertFromUser();
        }
    }


    private void createConcertFromUser() {
        AbstractEvent eventToCreate;
        System.out.println("How many people do you want for the concert ? ");
        int nbPeople = inputInt();
        int year,month,day;
        System.out.println("Please enter the year of the concert");
        year = inputInt();
        System.out.println("Please enter the month of the concert (ex: jan --> 01)");
        month = inputInt();
        System.out.println("Please enter the day of the concert");
        day = inputInt();
        Date date = new Date(year, month, day);
        System.out.println("You entered the following date : " + date);

        System.out.println("Is it a group or an artist ?");
        System.out.println("1 : Group");
        System.out.println("2 : Artist");
        int groupOrArtist = inputInt();
        if (groupOrArtist == 1){
            System.out.println("What's the name of the group ?");
            String name = inputString();
            NameGroup nameGroup = new NameGroup(name);
            eventToCreate = new Concert(nbPeople,date,nameGroup);
            programmation.addEvent(eventToCreate);
            this.userInteface(); //back to the menu
        }
        else if (groupOrArtist == 2){
            System.out.println("What's the first name of the artist ?");
            String nameArtist = inputString();
            System.out.println("What's the last name of the artist ?");
            String lastName = inputString();
            NameArtist nameOfArtist = new NameArtist(nameArtist, lastName);
            eventToCreate = new Concert(nbPeople, date, nameOfArtist);
            programmation.addEvent(eventToCreate);
            this.userInteface();
        }
        else {
            System.out.println("Incorrect choice");
            this.createConcertFromUser();
        }
    }

        private void createRoomFromUser() {
            Room roomToAdd;
            System.out.println("What's the capacity of the room ?");
            int capacity = inputInt();
            int year,month,day;
            System.out.println("Please enter the opening year of the room");
            year = inputInt();
            System.out.println("Please enter the opening month of room (ex: jan --> 01)");
            month = inputInt();
            System.out.println("Please enter the opening day of the room");
            day = inputInt();
            Date date = new Date(year, month, day);
            int openedHour, closedHour;
            System.out.println("Please enter the opening hour of the room");
            openedHour = inputInt();
            System.out.println("Please enter the closing hour of the room");
            closedHour = inputInt();
            roomToAdd = new Room(capacity, new TimeSlot(date, openedHour, closedHour));
            programmation.addRoom(roomToAdd);
            this.userInteface();
        }

    public void userInteface(){
        System.out.println("Hello, what do you want to add ?");
        System.out.println("1 : Add event");
        System.out.println("2 : Add room");
        System.out.println("3 : Delete all event");
        System.out.println("4 : Schedule all events");
        String answer = inputString();

        switch(answer){
            case "1":
                this.createEventFromUser();
                break;
            
            case "2":
                this.createRoomFromUser();
                break;

            case "3": 
                programmation.deleteAllEvent();
                break;

            case "4":
                programmation.scheduleEvent();
                break;

            default:
                System.out.println("Incorrect choice");
                this.userInteface(); //recursion loop
        }
        
    }
    
}
