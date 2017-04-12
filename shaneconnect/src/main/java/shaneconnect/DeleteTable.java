package shaneconnect;

import com.android.volley.Response;

import org.json.JSONObject;

/**
 * Created by shane on 4/5/17.
 */

/**
 *
 */
public class DeleteTable extends DeleteDecorator {


    public DeleteTable(ShaneConnect sha,String name, Command c){
        super(sha,c);
        super.sql =  "DELETE FROM TABLES WHERE NAME = '" + name + "' ;";
    }



}
