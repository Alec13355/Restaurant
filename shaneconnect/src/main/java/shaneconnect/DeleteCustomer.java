package shaneconnect;

/**
 * Created by shane on 4/5/17.
 */

public class DeleteCustomer extends DeleteDecorator {

    public DeleteCustomer(ShaneConnect sha, String username, Command c){
        super(sha,c);
        super.sql =  "DELETE FROM CUSTOMER WHERE USER_NAME = '" + username + "' ;";

    }

}
