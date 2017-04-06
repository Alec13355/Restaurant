package shaneconnect;

/**
 * Created by shane on 4/5/17.
 */

public class DeleteOrders extends DeleteDecorator {

    public DeleteOrders(ShaneConnect sha, String name, Command c){
        super(sha,c);
        super.sql =  "DELETE FROM ORDERS WHERE DESCRIP = '" + name + "' ;";
    }
}
