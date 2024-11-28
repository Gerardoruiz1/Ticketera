import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class Estadio {
    private final List<Section> sections;
    private final HashMap<Cliente, List<Asiento>> reservations;
    public final LinkedList<String> transactionHistory; // this could be done with a LinkedList<HashMap<String,String>>
    public final Stack<String> undoStack;
    public final HashMap<String, Queue<Cliente>> waitLists;
    public Estadio() {
        sections = new ArrayList<>();
        sections.add(new Section("Field Level", 300, 5));
        sections.add(new Section("Main Level", 120, 1));
        sections.add(new Section("Grandstand Level", 45, 2));
        reservations = new HashMap<>();
        transactionHistory = new LinkedList<>();
        undoStack = new Stack<>();
        waitLists = new HashMap<>();
        for (Section section : sections) {
            waitLists.put(section.getName(), new LinkedList<>());
        }
    }

     // Add a transaction to the history log
     public void logTransaction(String transaction) {
        transactionHistory.add(transaction);
        undoStack.push(transaction); // Save the transaction to the undo stack
    }

    // Undo the last transaction
    public void undoLastTransaction() {
        if (!undoStack.isEmpty()) {
            String lastTransaction = undoStack.pop();
            System.out.println("Undoing: " + lastTransaction);
            // Add logic to reverse the transaction if necessary
        } else {
            System.out.println("No actions to undo.");
        }
    }

    // Add a client to the waitlist for a specific section
    public void addToWaitlist(String sectionName, Cliente cliente) {
        if (waitLists.containsKey(sectionName)) {
            waitLists.get(sectionName).add(cliente);
        }
    }

    // Serve the next client from the waitlist for a specific section
    public Cliente serveFromWaitlist(String sectionName) {
        if (waitLists.containsKey(sectionName) && !waitLists.get(sectionName).isEmpty()) {
            return waitLists.get(sectionName).poll();
        }
        return null;
    }


    // Show available sections and sold out sections 
    public void showAvailableSections() {
        List<Section> unavailableSections = new ArrayList<>();
        int menuSelectNum = 1;

        boolean first = true;
        for (Section section : sections){
            if (!section.isFull()){
                if (first){
                    System.out.println("Available Sections:");
                    first = false;
                }
                System.out.println("[" + menuSelectNum + "]" + section.getName() + " -> $" + section.getPrice());
                menuSelectNum++;
            } else {
                unavailableSections.add(section);
            }
        }

        if (unavailableSections.size() >= 1){
            System.out.println("Sold Out Sections:");
        }
        for (Section section : unavailableSections){
            System.out.println("[" + menuSelectNum + "]" + section.getName() + " -> $" + section.getPrice());
            menuSelectNum++;
        }
    }
    public boolean reserveSeat(Cliente cliente, String sectionName, int row, int number) {
        for (Section section : sections) {
            if (section.getName().equalsIgnoreCase(sectionName)) {
                if (section.reserveSeat(row, number)) {
                    Asiento newSeat = new Asiento(sectionName, row, number);
                    reservations.putIfAbsent(cliente, new ArrayList<>());
                    reservations.get(cliente).add(newSeat);

                    System.out.println("Seat reserved successfully for " + cliente.getName() + ": " + newSeat);
                    return true;
                } else {
                    System.out.println("Reservation failed: Section is full or seat already taken.");
                    System.out.println("Do you wish to be added to a waiting list for this section?");
                    //if yes
                    //added to waitingList 
                    //if not 
                    System.out.println("choose another seat");
                    //aqui poner si la persona desea estan 
                    return false;
                }
            }
        }
        System.out.println("Section not found: " + sectionName);
        return false;
    }
    public boolean cancelSeat(Cliente cliente, String sectionName, int row, int number) {
        for (Section section : sections) {
            if (section.getName().equalsIgnoreCase(sectionName)) {
                if (section.cancelSeat(row, number)) { 
                    Asiento seatToRemove = new Asiento(sectionName, row, number);
                    if (reservations.containsKey(cliente)) {
                        reservations.get(cliente).remove(seatToRemove);
                        if (reservations.get(cliente).isEmpty()) {
                            reservations.remove(cliente); 
                        }
                    }

                    System.out.println("Seat canceled successfully for " + cliente.getName() + ": " + seatToRemove);
                    return true;
                } else {
                    System.out.println("Cancellation failed: Seat not reserved.");
                    return false;
                }
            }
        }
        System.out.println("Section not found: " + sectionName);
        return false;
    }
    public void viewReservations(Cliente cliente) {
        if (reservations.containsKey(cliente)) {
            System.out.println("Reservations for " + cliente.getName() + ":");
            for (Asiento seat : reservations.get(cliente)) {
                System.out.println(seat);
            }
        } else {
            System.out.println(cliente.getName() + " has no reservations.");
        }
    }
    

   // PARA PROBAR, HAY QUE BORRAR DESPUES
    public void testSeatOperations() {
        System.out.println("Testing Seat Operations:");
    
        Section fieldLevel = sections.get(0);
    
        System.out.println("Reserving Row 1, Seat 1: " + fieldLevel.reserveSeat(1, 1));
        System.out.println("Reserving Row 1, Seat 2: " + fieldLevel.reserveSeat(1, 2));
        System.out.println("Reserving Row 1, Seat 1 again: " + fieldLevel.reserveSeat(1, 1)); 
        System.out.println("Available Seats: " + fieldLevel.getAvailableSeats()); 
        System.out.println("Canceling Row 1, Seat 1: " + fieldLevel.cancelSeat(1, 1)); 
        System.out.println("Canceling Row 1, Seat 3: " + fieldLevel.cancelSeat(1, 3)); 
        System.out.println("Available Seats after cancellation: " + fieldLevel.getAvailableSeats()); 
    }
    public void sectionSelect(Scanner scanner) {
        System.out.println("Select section: (Enter menu option 1-" + sections.size() + ")");
        int menuSelect;
        do {
            while (!scanner.hasNextInt()) { 
                System.out.println("Invalid input. Please enter a number between 1 and " + sections.size() + ":");
                scanner.next(); 
            }
            menuSelect = scanner.nextInt();
        } while (menuSelect < 1 || menuSelect > sections.size());
    
        Section selectedSection = sections.get(menuSelect - 1);
    
        if (selectedSection.isFull()) {
            System.out.println("Section is full. Adding to queue...");
        } else {
            System.out.println("Section has been selected.");
        }
    }

    // Private Section class
    private class Section {
        private final String name;
        private final int cost;
        private final int capacity;
        private final Set<Asiento> seats;

        public Section(String name, int cost, int capacity) {
            this.name = name;
            this.cost = cost;
            this.capacity = capacity;
            this.seats = new LinkedHashSet<>();
        }

        public boolean isFull() {
            return this.getTakenSeats() >= this.getCapacity();
        }

        public String getName() {
            return this.name;
        }

        public int getPrice() {
            return this.cost;
        }

        public int getCapacity() {
            return this.capacity;
        }

        public int getTakenSeats() {
            return this.seats.size();
        }

        public int getAvailableSeats() {
            return this.getCapacity() - this.seats.size();
        }

        public boolean reserveSeat(int row, int number){
            if (isFull()) {
                return false;
            }
            Asiento newSeat = new Asiento(name, row, number);
            return seats.add(newSeat);
        }

        public boolean cancelSeat(int row, int number){
            Asiento seatToRemove = new Asiento(name, row, number);
            return seats.remove(seatToRemove);
        }
        @Override
        public String toString() {
            return "Section [Name: " + name + ", Cost: $" + cost + ", Available Seats: " + getAvailableSeats() + "]";

        }
    }
}

