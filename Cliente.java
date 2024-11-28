public class Cliente {
    private String name;
    private String email;
    private String phoneNumber;

    public Cliente(String name, String email, String phoneNumber) {
        validateName(name);
        validateEmail(email);
        validatePhoneNumber(phoneNumber);
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Validation Methods
    public static void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        if (name.trim().length() < 2) {
            throw new IllegalArgumentException("Name must be at least 2 characters long.");
        }
        if (!name.matches("^[a-zA-Z ]+$")) { // Allows letters and spaces only
            throw new IllegalArgumentException("Name can only contain letters and spaces.");
        }
    }

    public static void validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty.");
        }
        email = email.trim();
        int atIndex = email.indexOf('@');
        int dotIndex = email.lastIndexOf('.');
    
        // Checks if Contains '@', has no spaces, '.' comes after '@'
        if (atIndex < 1 || dotIndex < atIndex + 2 || dotIndex == email.length() - 1 || email.contains(" ")) {
            throw new IllegalArgumentException("Invalid email: must be a valid email format with '@' and a domain.");
        }
    }    

    public static void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be null or empty.");
        }
        phoneNumber = phoneNumber.trim();
        if (!phoneNumber.matches("\\d{10}")) {
            throw new IllegalArgumentException("Invalid phone number: must be exactly 10 digits and contain only numbers.");
        }
    }


    // Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }    

    public String getPhoneNumber() {
        return phoneNumber;
    }    

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Cliente [Nombre: " + name + ", Email: " + email + ", Teléfono: " + phoneNumber + "]";
    }
}   