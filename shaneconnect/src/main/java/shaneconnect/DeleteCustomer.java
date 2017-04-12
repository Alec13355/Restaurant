package shaneconnect;

/**
 * Created by shane on 4/5/17.
 */

/**
 * Decorator class for commands and other decorators, this decorator will delete a customer
 */
public class DeleteCustomer extends DeleteDecorator {

    /**
     *  The constructor for the decorator
     * @param sha the ShaneConnect object
     * @param username the username you are deleting
     * @param c the decorator class to be surrounded with
     */
    public DeleteCustomer(ShaneConnect sha, String username, Command c){
        super(sha,c);
        super.sql =  "DELETE FROM CUSTOMER WHERE USER_NAME = '" + username + "' ;";

    }

}
