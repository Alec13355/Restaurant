package shaneconnect;

/**
 * Created by shane on 4/11/17.
 */

public class DeleteLogs extends DeleteDecorator {
    /**
     * The constructor
     * @param sha the ShaneConnect object
     * @param employee the object representing a employee
     * @param c the decorator that is being wrapped around
     */
    public DeleteLogs(ShaneConnect sha, ShaneConnectEmployee employee, Command c){
        super(sha, c);
        super.sql =  "DELETE FROM EMPLOYEE_LOG WHERE EMPLOYEE_ID = " + employee.getEmployeeId() + " ;";
    }
}
