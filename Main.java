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
                        if (!estadio.isValidSection(sectionName)) {
                            System.out.println("Error: Invalid section name. Please choose from the available sections.");
                            break;
                        }

                        int row = -1;
                        while (row < 0) {
                            System.out.print("Enter Row: ");
                            if (scanner.hasNextInt()) {
                                row = scanner.nextInt();
                            } else {
                                System.out.println("Invalid input. Please enter a valid row number.");
                                scanner.next(); // Clear invalid input
                            }
                        }

                        int seatNumber = -1;
                        while (seatNumber < 0) {
                            System.out.print("Enter Seat Number: ");
                            if (scanner.hasNextInt()) {
                                seatNumber = scanner.nextInt();
                            } else {
                                System.out.println("Invalid input. Please enter a valid seat number.");
                                scanner.next(); // Clear invalid input
                            }
                        }

                        scanner.nextLine(); // Consume newline
                        estadio.reserveSeat(cliente, sectionName, row, seatNumber);
                        break;
                    case 3:
                        System.out.print("Enter Section Name: ");
                        sectionName = scanner.nextLine();
                        if (!estadio.isValidSection(sectionName)) {
                            System.out.println("Error: Invalid section name. Please choose from the available sections.");
                            break;
                        }

                        row = -1;
                        while (row < 0) {
                            System.out.print("Enter Row: ");
                            if (scanner.hasNextInt()) {
                                row = scanner.nextInt();
                            } else {
                                System.out.println("Invalid input. Please enter a valid row number.");
                                scanner.next(); // Clear invalid input
                            }
                        }

                        seatNumber = -1;
                        while (seatNumber < 0) {
                            System.out.print("Enter Seat Number: ");
                            if (scanner.hasNextInt()) {
                                seatNumber = scanner.nextInt();
                            } else {
                                System.out.println("Invalid input. Please enter a valid seat number.");
                                scanner.next(); // Clear invalid input
                            }
                        }

                        scanner.nextLine(); // Consume newline
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
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and 8.");
                scanner.nextLine(); // Clear invalid input
            }
        }
        scanner.close(); // Close the scanner at the very end
    }

    private static Cliente createNewCliente(Scanner scanner) {
        String name = null;
        String email = null;
        String phoneNumber = null;

        while (name == null) {
            try {
                System.out.println("Enter your Name:");
                name = scanner.nextLine();
                Cliente.validateName(name);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                name = null;
            }
        }

        while (email == null) {
            try {
                System.out.println("Enter your Email:");
                email = scanner.nextLine();
                Cliente.validateEmail(email);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                email = null;
            }
        }

        while (phoneNumber == null) {
            try {
                System.out.println("Enter your Phone Number:");
                phoneNumber = scanner.nextLine();
                Cliente.validatePhoneNumber(phoneNumber);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                phoneNumber = null;
            }
        }

        return new Cliente(name, email, phoneNumber); // Return valid client
    }
}
