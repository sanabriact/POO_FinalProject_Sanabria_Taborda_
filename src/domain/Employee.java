package domain;

public class Employee extends Guest {

    // Static counter that assigns a unique number to every employee
    private static int nextNumber = 0;
    private double salary;
    private int employeeNumber;
    private String position;
    private boolean active;

    // Constructor that initializes an employee using inherited Guest fields
    public Employee(String name, String email, long phoneNumber, long id, String position, double salary) {
        super(name, email, phoneNumber, id);    
        this.setSalary(salary);                
        this.setPosition(position);            
        nextNumber++;                          
        this.employeeNumber = nextNumber;      
        this.setActive(true);          
    }

    // Returns the employee's job position
    public String getPosition() {
        return position;
    }

    // Returns the employee's salary
    public double getSalary() {
        return salary;
    }

    // Returns whether the employee is active
    public boolean getActive() {
        return active;
    }

    // Returns the unique employee number
    public int getEmployeeNumber() {
        return employeeNumber;
    }

    // Returns the next employee number that will be assigned
    public static int getNextNumber() {
        return nextNumber;
    }

    // Updates the static employee counter
    public static void setNextNumber(int number) {
        nextNumber = number;
    }

    // Sets the employee's salary; invalid values are replaced with 0
    public void setSalary(double salary) {
        if (salary > 0) {
            this.salary = salary;
        } else {
            this.salary = 0;
        }
    }

    // Sets the employee's position; assigns null if empty
    public void setPosition(String position) {
        if (!position.isEmpty()) {
            this.position = position;
        } else {
            this.position = null;
        }
    }

    // Sets the employee's active status
    public void setActive(boolean active) {
        this.active = active;
    }

    // Marks the employee as inactive (dismissed)
    public void dismissEmployee() {
        this.active = false;
    }

    // Returns a string representation of the employee with all key attributes
    @Override
    public String toString() {
        return "Guest{id=" + getId() + ", name=" + getName() +
                ", email=" + getEmail() + ", phoneNumber=" + getPhoneNum() +
                ", position=" + position + ", salary=$" + salary +
                ", active=" + active + ", employeeNumber=" + employeeNumber + "}";
    }
}
