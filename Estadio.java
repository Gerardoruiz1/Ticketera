import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Estadio {
    private final List<Section> sections;

    public Estadio() {
        sections = new ArrayList<>();
        sections.add(new Section("Field Level", 300, 500));
        sections.add(new Section("Main Level", 120, 1000));
        sections.add(new Section("Grandstand Level", 45, 2000));
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
   //
    public void testSeatOperations() {
        System.out.println("Testing Seat Operations:");
    
        // Create a section (Field Level)
        Section fieldLevel = sections.get(0); // Get Field Level section
    
        // Reserve seats
        System.out.println("Reserving Row 1, Seat 1: " + fieldLevel.reserveSeat(1, 1)); // Expected: true
        System.out.println("Reserving Row 1, Seat 2: " + fieldLevel.reserveSeat(1, 2)); // Expected: true
        System.out.println("Reserving Row 1, Seat 1 again: " + fieldLevel.reserveSeat(1, 1)); // Expected: false (duplicate)
    
        // Check availability
        System.out.println("Available Seats: " + fieldLevel.getAvailableSeats()); // Expected: Capacity - 2
    
        // Cancel a seat
        System.out.println("Canceling Row 1, Seat 1: " + fieldLevel.cancelSeat(1, 1)); // Expected: true
        System.out.println("Canceling Row 1, Seat 3: " + fieldLevel.cancelSeat(1, 3)); // Expected: false (not reserved)
    
        // Check availability after cancellation
        System.out.println("Available Seats after cancellation: " + fieldLevel.getAvailableSeats()); // Expected: Capacity - 1
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
            // Add to queue (not implemented yet)be
        } else {
            System.out.println("Section has been selected.");
        }
    }

    //  public void sectionSelect() {
        
    //     try (Scanner scanner = new Scanner(System.in)) {
    //         System.out.println("Select section: (Enter menu option 1-" + sections.size() + ")");
    //         int menuSelect;
    //         do {
    //             menuSelect = scanner.nextInt();
    //         } while (menuSelect < 1 || menuSelect > sections.size());
    
    //         Section selectedSection = sections.get(menuSelect - 1);
    
    //         if (selectedSection.isFull()) {
    //             System.out.println("Section is full. Adding to queue...");
    //             // add to queue
    //         } else {
    //             System.out.println("Section has been selected.");
    //         }
    //     }
    // }
        

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
