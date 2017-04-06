package shaneconnect;

import com.android.volley.Response;

import org.json.JSONObject;

/**
 * Created by shane on 4/5/17.
 */

/**
 *
 */
public class ConcreteCommand implements Command {

    private String sql;

    public ConcreteCommand(){
        this.sql = "";
    }

    @Override
    public void exectute(Response.Listener<JSONObject> s) {
        return;
    }
}
