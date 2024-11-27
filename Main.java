import java.util.Scanner;

public class Main {
   public static void main(String[] args) {
      Estadio estadio = new Estadio();
      Scanner scanner = new Scanner(System.in);
      System.out.println("Enter your Name, Email and Phone Number:");
      Cliente cliente = new Cliente(scanner.nextLine(), scanner.nextLine(), scanner.nextLine());
      boolean exit = false; 
         // Test 1: Log transactions and view history
        estadio.logTransaction("Reservation: Field Level - 2 seats for John Doe");
        estadio.logTransaction("Reservation: Main Level - 4 seats for Jane Smith");
        System.out.println("Transaction History: " + estadio.transactionHistory);

        // Test 2: Undo the last transaction
        estadio.undoLastTransaction();
        System.out.println("Transaction History After Undo: " + estadio.transactionHistory);

        // Test 3: Add clients to the waitlist
        Cliente client1 = new Cliente("Alice Johnson", "alice@example.com", "123-456-7890");
        Cliente client2 = new Cliente("Bob Brown", "bob@example.com", "098-765-4321");
        estadio.addToWaitlist("Field Level", client1);
        estadio.addToWaitlist("Field Level", client2);
        System.out.println("Waitlist for Field Level: " + estadio.waitLists.get("Field Level"));

        // Test 4: Serve a client from the waitlist
        Cliente servedClient = estadio.serveFromWaitlist("Field Level");
        System.out.println("Served Client: " + servedClient);
        System.out.println("Waitlist for Field Level After Serving: " + estadio.waitLists.get("Field Level"));

        // Test 5: Serve another client from the waitlist
        Cliente servedClient2 = estadio.serveFromWaitlist("Field Level");
        System.out.println("Served Client: " + servedClient2);
        System.out.println("Waitlist for Field Level After Serving Again: " + estadio.waitLists.get("Field Level"));

        // Test 6: Attempt to serve from an empty waitlist
        Cliente servedClient3 = estadio.serveFromWaitlist("Field Level");
        System.out.println("Served Client from Empty Waitlist: " + servedClient3);




        while (!exit) {
         //opciones del menu
            System.out.println("\n--- Menu ---");
            System.out.println("1. View Available Sections");
            System.out.println("2. Reserve a Seat");
            System.out.println("3. Cancel a Reservation");
            System.out.println("4. View My Reservations");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1: 
                    estadio.showAvailableSections();
                    break;
                case 2: 
                    System.out.print("Enter Section Name: ");
                    String sectionName = scanner.nextLine();
                    System.out.print("Enter Row: ");
                    int row = scanner.nextInt();
                    System.out.print("Enter Seat Number: ");
                    int seatNumber = scanner.nextInt();
                    estadio.reserveSeat(cliente, sectionName, row, seatNumber);
                    break;
                case 3: 
                    System.out.print("Enter Section Name: ");
                    sectionName = scanner.nextLine();
                    System.out.print("Enter Row: ");
                    row = scanner.nextInt();
                    System.out.print("Enter Seat Number: ");
                    seatNumber = scanner.nextInt();
                    estadio.cancelSeat(cliente, sectionName, row, seatNumber);
                    break;
                case 4: 
                    estadio.viewReservations(cliente);
                    break;
                case 5: 
                    exit = true;
                    System.out.println("Goodbye!");
                    break;
                default: 
                    System.out.println("Invalid option. Try again.");
            }
        }
        scanner.close();
    }
}
