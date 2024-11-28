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
    public final LinkedList<String> transactionHistory;
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

    // Log a transaction
    public void logTransaction(String action, Cliente cliente, String sectionName, int row, int number) {
        String transaction = String.format(
            "Action: %s | Client: %s | Section: %s | Row: %d | Seat: %d",
            action.toUpperCase(), cliente.getName(), sectionName, row, number
        );
        transactionHistory.add(transaction);
        undoStack.push(transaction);
    }

    // Print transaction history
    public void printTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            System.out.println("\nTransaction History:");
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }

    // Undo the last transaction
    public void undoLastTransaction() {
        if (!undoStack.isEmpty()) {
            String lastTransaction = undoStack.pop(); // Get the last transaction
            String[] parts = lastTransaction.split("\\|");

            String action = parts[0].trim().split(":")[1].trim(); // "reserve" or "cancel"
            String clientName = parts[1].trim().split(":")[1].trim();
            String sectionName = parts[2].trim().split(":")[1].trim();
            int row = Integer.parseInt(parts[3].trim().split(":")[1].trim());
            int number = Integer.parseInt(parts[4].trim().split(":")[1].trim());

            // Find the corresponding Cliente
            Cliente cliente = null;
            for (Cliente c : reservations.keySet()) {
                if (c.getName().equals(clientName)) {
                    cliente = c;
                    break;
                }
            }

            if (cliente == null) {
                System.out.println("Error: Client not found for undo.");
                return;
            }

            if (action.equalsIgnoreCase("RESERVE")) {
                cancelSeat(cliente, sectionName, row, number);
                System.out.println("Undo: Reservation for " + sectionName + ", Row " + row + ", Seat " + number + " has been canceled.");
            } else if (action.equalsIgnoreCase("CANCEL")) {
                reserveSeat(cliente, sectionName, row, number);
                System.out.println("Undo: Seat for " + sectionName + ", Row " + row + ", Seat " + number + " has been re-reserved.");
            }
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

    // Show available sections and sold-out sections
    public void showAvailableSections() {
        List<Section> unavailableSections = new ArrayList<>();
        int menuSelectNum = 1;

        boolean first = true;
        for (Section section : sections) {
            if (!section.isFull()) {
                if (first) {
                    System.out.println("Available Sections:");
                    first = false;
                }
                System.out.println("[" + menuSelectNum + "] " + section.getName() + " -> $" + section.getPrice());
                menuSelectNum++;
            } else {
                unavailableSections.add(section);
            }
        }

        if (!unavailableSections.isEmpty()) {
            System.out.println("Sold Out Sections:");
            for (Section section : unavailableSections) {
                System.out.println("[" + menuSelectNum + "] " + section.getName() + " -> $" + section.getPrice());
                menuSelectNum++;
            }
        }
    }

    public boolean reserveSeat(Cliente cliente, String sectionName, int row, int number) {
        for (Section section : sections) {
            if (section.getName().equalsIgnoreCase(sectionName)) {
                if (section.reserveSeat(row, number)) {
                    Asiento newSeat = new Asiento(sectionName, row, number);
                    reservations.putIfAbsent(cliente, new ArrayList<>());
                    reservations.get(cliente).add(newSeat);
                    logTransaction("reserve", cliente, sectionName, row, number);

                    System.out.println("Seat reserved successfully for " + cliente.getName() + ": " + newSeat);
                    return true;
                } else {
                    System.out.println("Reservation failed: Section is full or seat already taken.");
                    System.out.println("Do you wish to be added to a waiting list for this section? (yes/no)");

                    Scanner scanner = new Scanner(System.in);
                    String input = scanner.nextLine().trim().toLowerCase();

                    if (input.equals("yes")) {
                        addToWaitlist(sectionName, cliente);
                        System.out.println("You have been added to the waiting list for " + sectionName + ".");
                    } else {
                        System.out.println("You have chosen not to join the waiting list.");
                    }
                    return false;
                }
            }
        }
        System.out.println("Error: Section '" + sectionName + "' does not exist.");
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
                    logTransaction("cancel", cliente, sectionName, row, number);

                    System.out.println("Seat canceled successfully for " + cliente.getName() + ": " + seatToRemove);

                    Cliente nextClient = serveFromWaitlist(sectionName);
                    if (nextClient != null) {
                        reserveSeat(nextClient, sectionName, row, number);
                        System.out.println("Seat now assigned to the next client in the waitlist: " + nextClient.getName());
                    } else {
                        System.out.println("No clients in the waitlist for this section.");
                    }
                    return true;
                } else {
                    System.out.println("Cancellation failed: Seat not reserved.");
                    return false;
                }
            }
        }
        System.out.println("Error: Section '" + sectionName + "' does not exist.");
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

        public boolean reserveSeat(int row, int number) {
            if (isFull()) {
                return false;
            }
            Asiento newSeat = new Asiento(name, row, number);
            return seats.add(newSeat);
        }

        public boolean cancelSeat(int row, int number) {
            Asiento seatToRemove = new Asiento(name, row, number);
            return seats.remove(seatToRemove);
        }

        @Override
        public String toString() {
            return "Section [Name: " + name + ", Cost: $" + cost + ", Available Seats: " + getAvailableSeats() + "]";
        }
    }
}
