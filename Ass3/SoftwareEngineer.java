public class SoftwareEngineer extends TechnicalEmployee{
    private boolean accessToCode = false;
    private int codeCheckIns = 0;
    //use unspecified manager and set when calling addReport in manager class
    TechnicalLead manager;
    
    public SoftwareEngineer(String name){
        super(name);
    }

    public boolean getCodeAccess(){
        return this.accessToCode;
    }

    public void setCodeAccess(boolean access){
        this.accessToCode = access;
    }

    public int getSuccessfulCheckIns(){
        return this.codeCheckIns;
    }

    public boolean checkInCode(){
        boolean approved = this.manager.approveCheckIn(this);
        if(approved){
            this.codeCheckIns++;
            return true;
        }else{
            this.accessToCode = false;
            return false;
        }
        
    }

    public String employeeStatus(){
        String status = this.toString() + " has " + Integer.toString(this.codeCheckIns) + " successful check ins";
        return status;
    }

    public Employee getManager(){
        return this.manager;
    }
    

}