import pop.kar.Itinerary;

public class Main {
    public static void main(String[] args) {

        Itinerary itinerary = new Itinerary();
        itinerary.addTown("Adelaide", 1374);
        itinerary.addTown("Alice Springs", 2771);
        itinerary.addTown("Brisbane", 917);
        itinerary.addTown("Darwin", 3972);
        itinerary.addTown("Melbourne", 877);
        itinerary.addTown("melbourne", 877);
        itinerary.addTown("Perth", 3923);

        itinerary.runItineraryList();



    }
}