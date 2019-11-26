public abstract class TechnicalEmployee extends Employee{
    
    public TechnicalEmployee(String name){
        super(name,75000.0);
    }

    public abstract String employeeStatus();
}