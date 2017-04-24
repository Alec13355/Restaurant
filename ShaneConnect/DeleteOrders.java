package shaneconnect;

/**
 * Created by shane on 4/5/17.
 */

/**
 * Decorator to delete orders
 */
public class DeleteOrders extends DeleteDecorator {
    /**
     * Constructor
     * @param sha the ShaneConnect object
     * @param desc the description of the order
     * @param c the decorator to be wrapped around
     */
    public DeleteOrders(ShaneConnect sha, String desc, Command c){
        super(sha,c);
        super.sql =  "DELETE FROM ORDERS WHERE DESCRIP = '" + desc + "' ;";
    }
}
