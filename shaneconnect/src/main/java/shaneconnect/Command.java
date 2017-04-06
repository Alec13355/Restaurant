package shaneconnect;

import com.android.volley.Response;

import org.json.JSONObject;

/**
 * Created by shane on 4/5/17.
 */

public interface Command {

    public void exectute(Response.Listener<JSONObject> s);

}
