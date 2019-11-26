public abstract class Employee{
    private String name = "";
    private double baseSalary = 0.0;
    private static int ID = 1;
    private int employeeID = 0;
    
    public Employee(String name, double baseSalary){
        this.name = name;
        this.baseSalary = baseSalary;
        this.employeeID = ID;
        ID++;
    }

    public double getBaseSalary(){
        return this.baseSalary;
    }

    public String getName(){
        return this.name;
    }

    public int getEmployeeID(){
        return this.employeeID;
    }

    public abstract Employee getManager();

    public boolean equals(Employee other){
        if(this.employeeID == other.employeeID){
            return true;
        }
        return false;
    }

    public String toString(){
        return (Integer.toString(this.employeeID) +  " " + this.name);
    }

    public abstract String employeeStatus();



}