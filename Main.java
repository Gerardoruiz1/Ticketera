import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Estadio estadio = new Estadio();
        Scanner scanner = new Scanner(System.in); // Shared Scanner instance

        Cliente cliente = createNewCliente(scanner); // Ensures valid inputs
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. View Available Sections");
            System.out.println("2. Reserve a Seat");
            System.out.println("3. Cancel a Reservation");
            System.out.println("4. View My Reservations");
            System.out.println("5. Add another Client");
            System.out.println("6. Undo Last Transaction");
            System.out.println("7. View All Clients Reservation History");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            if (scanner.hasNextInt()) { // Validate input as an integer
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

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
                        cliente = createNewCliente(scanner); // Allow adding a new client
                        break;
                    case 6:
                        estadio.undoLastTransaction();
                        break;
                    case 7:
                        estadio.printTransactionHistory();
                        break;
                    case 8:
                        exit = true;
                        System.out.println("Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please choose a number between 1 and 8.");
                }
            } else { // Handle invalid input
                System.out.println("Invalid input. Please enter a number between 1 and 8.");
                scanner.nextLine(); // Consume invalid input
            }
        }
        scanner.close(); // Close the scanner at the very end
    }

    private static Cliente createNewCliente(Scanner scanner) {
        while (true) {
            try {
                System.out.println("Enter your Name:");
                String name = scanner.nextLine();
                Cliente.validateName(name);

                System.out.println("Enter your Email:");
                String email = scanner.nextLine();
                Cliente.validateEmail(email);

                System.out.println("Enter your Phone Number:");
                String phoneNumber = scanner.nextLine();
                Cliente.validatePhoneNumber(phoneNumber);

                return new Cliente(name, email, phoneNumber); // Return valid client
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println("Please try again.");
            }
        }
    }
}
