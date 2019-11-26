public class Accountant extends BusinessEmployee{
    private double bonusBudget = 0;
    private TechnicalLead teamLead;
    BusinessLead manager;

    public Accountant(String name){
        super(name);
    }

    public TechnicalLead getTeamSupported(){
        return teamLead;
    }

    public void supportTeam(TechnicalLead lead){
        this.teamLead = lead;//establishes that this accountant suports this team and increases binus budget based on slalries of employees (sum + sum*0.1)
        double salaries = 0;
        int numreps = this.teamLead.getNumDirectReports();//numreps = numberof reports
        Employee[] directReps = this.teamLead.getDirectReports();
        for(int i = 0; i<numreps; i++){
            salaries = salaries + directReps[i].getBaseSalary();
        }
        salaries = salaries + salaries*.1;
        this.bonusBudget = salaries;
    }

    public boolean approveBonus(double bonus){
        if(this.getTeamSupported() == null){
            return false;
        }else{
            return (bonus <= this.bonusBudget);
        }
    }

    public String employeeStatus(){
        String status = this.toString() + " with a budget of " + Double.toString(bonusBudget);
        String leadname = this.teamLead.getName();
        status = status + " is supporting " + leadname;
        return status;
    }

    public double getBonusBudget(){
        return this.bonusBudget;
    }

    public Employee getManager(){
        return this.manager;
    }
}