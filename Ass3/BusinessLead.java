public class BusinessLead extends BusinessEmployee{ //could make a Lead interface for some of the methods like getTeamStatus and request bonus and add report and stuff
    private double baseSalary = 0;
    private int headCount = 10;
    private Accountant[] directReports = new Accountant[10];
    private int numDirectReports = 0;
    private double bonusBudget = 0;
    
    public BusinessLead(String name){
        super(name);
        this.baseSalary = this.getBaseSalary()*2;
    }

    public boolean hasHeadCount(){
        return (this.numDirectReports < this.headCount);
    }

    public boolean addReport(Accountant e, TechnicalLead supportTeam){
        if(this.hasHeadCount()){
            this.directReports[numDirectReports] = e;
            this.numDirectReports++;
            e.manager = this;
            this.bonusBudget += e.getBaseSalary()*1.1;//gives bigger bonud salary and adds accountant to directreports
            e.supportTeam(supportTeam);
            return true;
        }else{
            return false;
        }
    }

    public boolean requestBonus(Employee e, double bonus){
        if(bonus<=this.bonusBudget){
            this.bonusBudget -= bonus;
            return true;
        }else{
            return false;
        }
    }

    public boolean approveBonus(Employee e, double bonus){
        for(int i=0; i<this.numDirectReports; i++){//goes through direct reports to find which accountant supports the technicalLead who is amanageer to employee e
            Employee man = e.getManager();
            Accountant accntnt = this.directReports[i];
            Employee sup = accntnt.getTeamSupported();
            if(man.getEmployeeID() == sup.getEmployeeID()){ //checks if bonus fits and then awards it or not
                double budget = accntnt.getBonusBudget();
                if(bonus<=budget){
                    return true;
                }else{
                    return false;
                }
            }else{
                continue;
            }
        }
        return false;
    }

    public String employeeStatus(){
        String status = this.toString() + " has a budget of " + Double.toString(this.bonusBudget);//not sure if there should be more, maybe all the accountants
        //could show whole company perhaps
        return status;
    }

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

    public double getBonusBudget(){
        return this.bonusBudget;
    }

    public Employee getManager(){
        return this;
    }


}