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
    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
    }

    private void validateEmail(String email) {
        if (email == null || !email.contains("@") || email.contains(" ")) {
            throw new IllegalArgumentException("Invalid email: must contain '@' and no spaces.");
        }
    }

    private void validatePhoneNumber(String phoneNumber) {
        phoneNumber = phoneNumber.trim(); // Remove spaces
        if (phoneNumber == null || !phoneNumber.matches("\\d{10}")) {
            throw new IllegalArgumentException("Invalid phone number: must be exactly 10 digits.");
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