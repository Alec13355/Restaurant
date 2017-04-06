package shaneconnect;

/**
 * Created by shane on 4/5/17.
 */

public class DeleteFood extends DeleteDecorator{

        public DeleteFood(ShaneConnect sha, String name, Command c){
            super(sha,c);
            super.sql =  "DELETE FROM FOOD WHERE NAME = '" + name + "' ;";
        }
}
