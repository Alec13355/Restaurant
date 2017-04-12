package shaneconnect;

import com.android.volley.Response;

import org.json.JSONObject;

/**
 * Created by shane on 4/5/17.
 */

/**
 * abstract class for the delete decorators
 */
public abstract class DeleteDecorator implements Command {

    protected ShaneConnect shane;

    protected String sql;

    private Command command;

    /**
     *
     * @param shane the shaneconnect
     * @param c a delete decorator object or decorator
     */
    public DeleteDecorator(ShaneConnect shane, Command c){
        this.shane = shane;
        this.sql="";
        this.command = c;
    }

    protected String getSql(){
        return sql;
    }

    /**
     * executes all commands that are within the decorator
     * @param s response method
     */
    public void exectute(Response.Listener<JSONObject> s) {
        shane.delete(this,s);
        command.exectute(s);
    }



}
