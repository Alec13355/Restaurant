package shaneconnect;

import com.android.volley.Response;

import org.json.JSONObject;

/**
 * Created by shane on 4/5/17.
 */

public abstract class DeleteDecorator implements Command {

    protected ShaneConnect shane;

    protected String sql;

    private Command command;

    public DeleteDecorator(ShaneConnect shane, Command c){
        this.shane = shane;
        this.sql="";
        this.command = c;
    }

    protected String getSql(){
        return sql;
    }

    public void exectute(Response.Listener<JSONObject> s) {
        shane.delete(this,s);
        command.exectute(s);
    }



}
