import java.util.Scanner;

public class Main {
   public static void main(String[] args) {
      Estadio estadio = new Estadio();
      Scanner scanner = new Scanner(System.in);
      System.out.println("Enter your Name, Email and Phone Number:");
      Cliente cliente = new Cliente(scanner.nextLine(), scanner.nextLine(), scanner.nextLine());
      boolean exit = false; 
         // Test 1: Log transactions and view history

        while (!exit) {
         //opciones del menu
            System.out.println("\n--- Menu ---");
            System.out.println("1. View Available Sections");
            System.out.println("2. Reserve a Seat");
            System.out.println("3. Cancel a Reservation");
            System.out.println("4. View My Reservations");
            System.out.println("5. Add another Client"); 
            System.out.println("6. Undo Last Transaction");
            System.out.println("7. View All Clients Reservation History"); // New option
            System.out.println("8. Exit"); 
            //
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
                case 8: 
                    exit = true;
                    System.out.println("Goodbye!");
                    break;
                case 5:
                System.out.println("Enter your Name, Email and Phone Number:");
                cliente = new Cliente(scanner.nextLine(), scanner.nextLine(), scanner.nextLine());
                break;
                case 6:
                estadio.undoLastTransaction();
                break;
                case 7:
                estadio.printTransactionHistory(); // Call to print transaction history
                break;
                default: 
                    System.out.println("Invalid option. Try again.");
            }
        }
        scanner.close();
    }
}
