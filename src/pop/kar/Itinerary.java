package pop.kar;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Itinerary {

    private final LinkedList<Town> itineraryList;
    private final Scanner scanner;

    public Itinerary() {
        itineraryList = new LinkedList<>();
        scanner = new Scanner(System.in);
        itineraryList.add(new Town("Sydney", 0));
    }

    public int addTown(String townName, int distanceFromSydney){

        if(distanceFromSydney <= 0){
            System.out.println("Distance from Sydney have to be greater than 0");
            return -1;
        }

        Town newTown = new Town(townName, distanceFromSydney);
        if(itineraryList.contains(newTown)){
            System.out.printf("%nCity %s is already in the list", townName);
            return -2;
        }

        ListIterator<Town> listIterator = itineraryList.listIterator();
        Town previousTown = listIterator.next();

        while(listIterator.hasNext()){
            if(previousTown.distanceFromSydney() > newTown.distanceFromSydney()){
                listIterator.previous();
                break;
            }
            previousTown = listIterator.next();
        }

        listIterator.add(newTown);
        return listIterator.previousIndex()+1;
    }

    public void runItineraryList(){

        boolean quit = false;
        ListIterator<Town> listIterator = itineraryList.listIterator();

        while(quit){

            String choice = scanner.nextLine();

            switch (choice.toUpperCase().charAt(0)){
                case 'F' -> {
                    if(listIterator.hasNext()){
                        System.out.println(listIterator.next());
                    } else {
                        System.out.println("End of list");
                    }
                }
                case 'B' -> {
                    if(listIterator.hasPrevious()){
                        System.out.println(listIterator.previous());
                    } else {
                        System.out.println("Start of list");
                    }
                }
                case 'L' -> printItineraryList();
                case 'M' -> printMenuOptions();
                default -> quit = true;
            }

        }
    }

    public void printItineraryList(){

        System.out.println(itineraryList);
    }

    private void printMenuOptions(){
        System.out.println("""
                \nAvailable actions (select word or letter):
                (F)orward
                (B)ackward
                (L)ist Places
                (M)enu
                (Q)uit""");
    }
}
