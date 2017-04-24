package shaneconnect;

/**
 * Created by shane on 4/5/17.
 */

/**
 * Decorator that deletes food
 */
public class DeleteFood extends DeleteDecorator{

    /**
     * The constructor
     * @param sha the ShaneConnect object
     * @param name the name of the food
     * @param c the decorator that is being wrapped around
     */
        public DeleteFood(ShaneConnect sha, String name, Command c){
            super(sha,c);
            super.sql =  "DELETE FROM FOOD WHERE NAME = '" + name + "' ;";
        }
}
