package shaneconnect;

/**
 * Created by shane on 4/5/17.
 */

public class DeleteEmployee extends DeleteDecorator {

    public DeleteEmployee(ShaneConnect sha, String fname,String lname,String id, Command c){
        super(sha,c);
        super.sql =  "DELETE FROM EMPLOYEES WHERE L_NAME = '" + lname + "' AND F_NAME = '" + fname + "' AND EMPLOYEE_ID = " + id + ";";

    }

}
