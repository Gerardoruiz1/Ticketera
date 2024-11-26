public class Asiento {
    private String section; //nombre de la secciom
    private int row;        //row numbe
    private int number;     //seat number

    //constructor
    public Asiento(String section, int row, int number) {
        this.section = section;
        this.row = row;
        this.number = number;
    }

    //getters
    public String getSection() {
        return section;
    }

    public int getRow() {
        return row;
    }

    public int getNumber() {
        return number;
    }

    //setters
    public void setSection(String section) {
        this.section = section;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    @Override
    public String toString() {
        return "Seat [Section: " + section + ", Row: " + row + ", Number: " + number + "]";
    }

    //para comparar las sillas (VAMOS A POR EL BONO)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Asiento other = (Asiento) obj;
        return row == other.row &&
               number == other.number &&
               section.equals(other.section);
    }

    @Override
    public int hashCode() {
        return section.hashCode() + row * 31 + number;
    }
}
