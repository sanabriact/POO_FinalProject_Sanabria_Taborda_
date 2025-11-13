package domain;

public class Employee extends Guest {
    private double salary;

    public Employee(String name, String email, long phoneNumber, long id, double salary){
        super(name, email, phoneNumber, id);
        this.salary = salary;
    }

    public double getSalary(){
        return salary;
    }

    public void setSalary(double salary){
        if(salary > 0){
            this.salary = salary;
        }else{
            this.salary = 0;
        }
    }

        


}
