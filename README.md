# Baseball Stadium Seat Reservation System

## Overview
This project implements a seat reservation system for a baseball stadium. The system manages seat availability, customer reservations, and waitlists for fully booked sections. Operators can perform actions such as reserving seats, canceling reservations, and viewing available sections.

---

## Features
1. **Seat Reservation**:
   - Allows operators to reserve seats for customers based on availability.
   - Automatically adds customers to a waitlist if a section is fully booked.
   
2. **Cancellation**:
   - Enables operators to cancel existing reservations, making seats available for others or serving the next customer on the waitlist.
   
3. **View Availability**:
   - Displays all sections with available seats and those that are fully booked.

4. **Undo Feature**:
   - Supports undoing the last reservation or cancellation action using a stack.

5. **Transaction Logging**:
   - Logs reservation and cancellation transactions in a history list.

6. **Waitlist Management**:
   - Manages a queue for customers waiting for a specific section.

---

## Stadium Sections
The stadium consists of the following seating options:

1. Field Level 
Cost: $300
Capacity: 500         

2. Main Level
Cost: $120
Capacity: 1,000

3. Grandstand Level
Cost: $45
Capacity: 2,000

---

## Technologies Used
The project uses Java and employs the following data structures:
1. **Set**: To store available seats and ensure no duplicate reservations.
2. **LinkedList**: To maintain a history of transactions.
3. **HashMap**: To map customers to their reserved seats.
4. **Stack**: To support the undo functionality.
5. **Queue**: To manage the waitlist for each section.

---

## Classes and Design

### `Asiento`
Represents a seat in the stadium with the following attributes:
- `section` (String): Name of the section.
- `row` (int): Row number.
- `number` (int): Seat number.

#### Key Methods:
- `equals` and `hashCode`: To ensure proper comparisons between seats.
- `toString`: Provides a readable representation of the seat.

---

### `Cliente`
Represents a customer with the following attributes:
- `name` (String): Customer's name.
- `email` (String): Customer's email address.
- `phoneNumber` (String): Customer's phone number.

#### Key Methods:
- Input validation for name, email, and phone number.
- `toString`: Provides a readable representation of the customer.

---

### `Estadio`
Manages the stadium's seating, reservations, and waitlists.

#### Key Attributes:
- `sections`: List of seating sections.
- `reservations`: Maps customers to their reserved seats.
- `transactionHistory`: Logs all transactions.
- `undoStack`: Stores the last action for undo functionality.
- `waitLists`: Manages a queue for each section's waitlist.

#### Key Methods:
- `reserveSeat`: Reserves a seat for a customer.
- `cancelSeat`: Cancels a reservation.
- `showAvailableSections`: Displays available and fully booked sections.
- `addToWaitlist` and `serveFromWaitlist`: Manage waitlists for each section.

---

### `Main`
The main entry point of the program that provides a menu-driven interface for the operator.

#### Menu Options:

1. View available sections.
2. Reserve a seat.
3. Cancel a reservation.
4. View customer's reservations.
5. Add another client.
6. Undo last transaction.
7. View all clients' reservation history.
8. Exit the program.

## Programmer Credits
Justin Rivera Muñiz
Gerardo Ruiz Barreto
Anthony Martinez Gomez