public abstract class BusinessEmployee extends Employee{
    
    public BusinessEmployee(String name){
        super(name,50000);
    }

    public abstract double getBonusBudget();

    public abstract String employeeStatus();
}