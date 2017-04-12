package shaneconnect;

import com.android.volley.Response;

import org.json.JSONObject;

/**
 * Created by shane on 4/5/17.
 */

/**
 * Represents a command, executing this command will not do anything. Any server actions require at least a single decorator
 */
public class ConcreteCommand implements Command {

    private String sql;

    /**
     * The constructor, nothing important is done here
     */
    public ConcreteCommand(){
        this.sql = "";
    }

    /**
     *  execute executes every command and decorator that is a composite of each other similar to a linked list stack.
     * @param s your response method for each delete command, returns json of the form {success:int}
     */
    @Override
    public void exectute(Response.Listener<JSONObject> s) {
        return;
    }
}
