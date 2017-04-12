package shaneconnect;

/**
 * Created by shane on 4/5/17.
 */

/**
 * Decorator that deletes employees
 */
public class DeleteEmployee extends DeleteDecorator {

    /**
     * Constructor
     * @param sha the ShaneConnect object
     * @param fname the first name
     * @param lname the last name
     * @param id the id number
     * @param c the decorator to be wrapped around
     */
    public DeleteEmployee(ShaneConnect sha, String fname,String lname,String id, Command c){
        super(sha,c);
        super.sql =  "DELETE FROM EMPLOYEES WHERE L_NAME = '" + lname + "' AND F_NAME = '" + fname + "' AND EMPLOYEE_ID = " + id + ";";

    }

}
