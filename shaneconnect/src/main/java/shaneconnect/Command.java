package shaneconnect;

import com.android.volley.Response;

import org.json.JSONObject;

/**
 * Created by shane on 4/5/17.
 */

/**
 * Interface for commands, the command structure is a decorator pattern/command pattern/ visitor pattern possibly?
 */
public interface Command {

    public void exectute(Response.Listener<JSONObject> s);

}
