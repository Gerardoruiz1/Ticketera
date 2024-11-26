import java.util.Scanner;

public class Main {
   public static void main(String[] args) {
      Estadio estadio = new Estadio();
      Scanner scanner = new Scanner(System.in);
  
      System.out.println("Enter your Name, Email and Phone Number:");
      Cliente cliente = new Cliente(scanner.nextLine(), scanner.nextLine(), scanner.nextLine());
      System.out.println(cliente.getName());
  
      estadio.showAvailableSections();
      estadio.sectionSelect(scanner); 
      estadio.testSeatOperations();
  }


   // public static void main(String[] args){
   //    try (Scanner scanner = new Scanner(System.in)) {
   //       System.out.println("Enter your Name, Email and Phone Number:");
   //       Cliente cliente = new Cliente(scanner.nextLine(), scanner.nextLine(), scanner.nextLine());
   //       System.out.println(cliente.getName());
   //    }
   //    Estadio coliseo = new Estadio();
   //    coliseo.showAvailableSections();
   //    coliseo.sectionSelect();
   //    coliseo.testSeatOperations();
   // } 
}
