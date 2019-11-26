public class TechnicalLead extends TechnicalEmployee{
    private int headCount = 4;
    private Employee[] directReports = new Employee[4];
    private int numDirectReports = 0; //would like these to be private but cant figure out how accountant 
    //can access this info for bonusbudget unless I make other setter getters, made getters
    BusinessLead supportBusinessLead;
    private int codeCheckIns = 0;
    private double baseSalary = 0;

    public TechnicalLead(String name){
        super(name);
        this.baseSalary = this.getBaseSalary()*1.3;//overriding baseSalary
    }

    public boolean hasHeadCount(){
        return (this.numDirectReports < this.headCount);
    }

    public boolean addReport(SoftwareEngineer e){
        boolean acceptReport = this.hasHeadCount();
        if(acceptReport){//adds Seng to reports, 4 max
            e.manager = this;
            this.directReports[this.numDirectReports] = e;
            this.numDirectReports++;
            return true;
        }else{
        return false;
        }
    }

    public boolean approveCheckIn(SoftwareEngineer e){
        boolean isManager = (this.getEmployeeID() == e.manager.getEmployeeID());
        if(isManager && e.getCodeAccess()){
            this.codeCheckIns++; //codeCheckIns for lead is different from Seng, increments when they aprove a chekin rather than when they themselves check in
            return true;
        }
        return false;
    }

    public boolean requestBonus(Employee e, double bonus){
        boolean approved = this.supportBusinessLead.approveBonus(e,bonus);
        if(approved){
            return true;
            //e.baseSalary  = e.baseSalary + bonus;
        }
        return false;
    }
    
//teamlead status + is managing:
//Sengs it is managing
    public String getTeamStatus(){
        String leadStatus = this.employeeStatus();
        if(this.numDirectReports == 0){
            return leadStatus + " and no direct reports yet.";
        }else{
            String status = leadStatus + " and is managing:";
            for(int i = 0; i<this.numDirectReports; i++){
                status = status + "\n" + this.directReports[i].employeeStatus();
            }
            return status;
        }
    }

    public String employeeStatus(){
        String status = this.toString() + " has " + Integer.toString(this.codeCheckIns) + " successful check ins";
        return status;
    }

    public Employee getManager(){
        return this;
    }

    public Employee[] getDirectReports(){
        return this.directReports;
    }

    public int getNumDirectReports(){
        return this.numDirectReports;
    }

}