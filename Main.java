import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Estadio estadio = new Estadio();
        Scanner scanner = new Scanner(System.in);
        
        Cliente cliente = newCliente();
        boolean exit = false;
        while (!exit) {
            // opciones del menu
            System.out.println("\n--- Menu ---");
            System.out.println("1. View Available Sections");
            System.out.println("2. Reserve a Seat");
            System.out.println("3. Cancel a Reservation");
            System.out.println("4. View My Reservations");
            System.out.println("5. Add another Client");
            System.out.println("6. Undo Last Transaction");
            System.out.println("7. View All Clients Reservation History"); // New option
            System.out.println("8. Exit"); 
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
                    cliente = newCliente();
                    break;
                case 6:
                    estadio.undoLastTransaction();
                    break;
                case 7:
                    exit = true;
                    System.out.println("Goodbye!");
                    break;
                case 8: 
                    exit = true;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
        scanner.close();
    }

    private static Cliente newCliente(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your Name:");
        String name = scanner.nextLine();
        Cliente.validateName(name);
        
        System.out.println("Enter your Email:");
        String email = scanner.nextLine();
        Cliente.validateEmail(email);
        System.out.println("Enter your Phone Number:");
        String phoneNumber = scanner.nextLine();
        Cliente.validatePhoneNumber(phoneNumber);
        
        scanner.close();
        return new Cliente(name, email, phoneNumber);
    }
}
