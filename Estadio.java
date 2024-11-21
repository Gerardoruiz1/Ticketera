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

    public void sectionSelect() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Select section: (Enter menu option 1-" + sections.size() + ")");
            int menuSelect;
            do {
                menuSelect = scanner.nextInt();
            } while (menuSelect < 1 || menuSelect > sections.size());
    
            Section selectedSection = sections.get(menuSelect - 1);
    
            if (selectedSection.isFull()) {
                System.out.println("Section is full. Adding to queue...");
                // add to queue
            } else {
                System.out.println("Section has been selected.");
            }
        }
    }
        

    // Private Section class
    private class Section {
        private final String name;
        private final int cost;
        private final int capacity;
        private final Set<Integer> seats;

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

        @Override
        public String toString() {
            return "Section [Name: " + name + ", Cost: $" + cost + ", Available Seats: " + getAvailableSeats() + "]";
        }
    }
}
