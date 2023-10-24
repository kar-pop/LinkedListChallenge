package pop.kar;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Itinerary {

    private final LinkedList<Town> itineraryList;
    private final Scanner scanner;

    private final String END_OF_LIST_MESSAGE = "End of list";
    private final String START_OF_LIST_MESSAGE = "Start of list";

    public Itinerary() {
        itineraryList = new LinkedList<>();
        scanner = new Scanner(System.in);
        itineraryList.add(new Town("Sydney", 0));
    }

    public int addTown(String townName, int distanceFromSydney) {

        if (distanceFromSydney <= 0) {
            System.out.println("Distance from Sydney have to be greater than 0");
            return -1;
        }

        Town newTown = new Town(townName, distanceFromSydney);
        if (itineraryList.contains(newTown)) {
            System.out.printf("City %s is already in the list%n", townName);
            return -2;
        }

        ListIterator<Town> listIterator = itineraryList.listIterator();
        Town previousTown = listIterator.next();

        while (listIterator.hasNext()) {
            if (previousTown.distanceFromSydney() > newTown.distanceFromSydney()) {
                listIterator.previous();
                break;
            }
            previousTown = listIterator.next();
        }

        listIterator.add(newTown);
        return listIterator.previousIndex() + 1;
    }

    public void runItineraryList() {

        boolean quit = false;
        ListIterator<Town> listIterator = itineraryList.listIterator();
        printMenuOptions();
        boolean listGoingForward = true;

        while (!quit) {
            System.out.print("\nSelect action: ");
            String choice = scanner.nextLine().toUpperCase();

            switch (choice.charAt(0)) {

                case 'F' -> {
                    if (listIterator.hasNext()) {

                        if (!listGoingForward) {
                            listIterator.next();
                            listGoingForward = true;
                            if (!listIterator.hasNext()) {
                                System.out.println(END_OF_LIST_MESSAGE);
                                break;
                            }
                        }
                        System.out.println(listIterator.next());


                    } else {
                        System.out.println(END_OF_LIST_MESSAGE);
                        listGoingForward = false;
                    }
                }
                case 'B' -> {
                    if (listIterator.hasPrevious()) {

                        if (listGoingForward) {
                            listIterator.previous();
                            listGoingForward = false;
                            if (!listIterator.hasPrevious()) {
                                System.out.println(START_OF_LIST_MESSAGE);
                                break;
                            }
                        }
                        System.out.println(listIterator.previous());

                    } else {
                        System.out.println(START_OF_LIST_MESSAGE);
                        listGoingForward = true;
                    }
                }
                case 'L' -> printItineraryList();
                case 'M' -> printMenuOptions();
                default -> quit = true;
            }

        }
    }

    public void printItineraryList() {

        for (Town t : itineraryList) {
            System.out.println(t);
        }
    }

    private void printMenuOptions() {
        System.out.println("""
                Available actions (select word or letter and press enter):
                (F)orward
                (B)ackward
                (L)ist Places
                (M)enu
                (Q)uit""");
    }
}
