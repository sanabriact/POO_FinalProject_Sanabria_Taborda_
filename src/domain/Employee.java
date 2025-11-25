package domain;

public class Employee extends Guest {
    // static int because all Employees share this attribute For each new reservation "employeeNumber" increases by one
    private static int nextNumber = 0;
    private double salary;
    private int employeeNumber;
    private String position;
    private boolean active;

    public Employee(String name, String email, long phoneNumber, long id, String position, double salary) {
        super(name, email, phoneNumber, id);
        this.salary = salary;
        this.position = position;
        nextNumber++;
        this.employeeNumber = nextNumber;
        this.active = true;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }

    public boolean getActive() {
        return active;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public static int getNextNumber(){
        return nextNumber;
    }

    public static void setNextNumber(int number){
        nextNumber = number;
    }

    public void setSalary(double salary) {
        if (salary > 0) {
            this.salary = salary;
        } else {
            this.salary = 0;
        }
    }

    public void setPosition(String position) {
        if (!position.isEmpty()) {
            this.position = position;
        } else {
            this.position = null;
        }
    }

    public void dismissEmployee() {
        this.active = false;
    }

    @Override
    public String toString() {
        return "Guest{id=" + getId() + ", name=" + getName() + ", email=" + getEmail() + ", phoneNumber="
                + getPhoneNum() + ", position=" + position + ", salary=$" + salary + ", active=" + active
                + ", employeeNumber=" + employeeNumber + "}";
    }

}
