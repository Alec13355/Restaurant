package com.example.shane.shaneconnect;

import android.content.Context;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * The latest in Shane Drafahl Industries® has led to the development in a way to modulate your server needs into one convenient way to communicate with
 * a server you own.
 */
public class ShaneConnect {


    /**
     * String address of where the server
     * and example might be
     */
    private String url;

    /**
     * maind is what stores the mainactivity that can put on operation in a query for the OS
     */
    private Context maind;

    /**
     *
     * @param url is a String object that holds the location of the server you want to connect to and example may be http://10.0.2.2:3019 do not enter the address of the post request
     * @param d is the MainActivity that controls the runtime of the app, for example to give an argument of this from the MainActivity class is just to type "this".
     */
    public ShaneConnect(String url, Context d) {
        this.url=url;
        maind=d;
    }


    /**
     * getAccountData will make a call to the server to get a json object of the representing account user given as an argument.
     * @param account_name this is the name of the account you want data from, for example "SMITH_BOB_1" would be an argument. The convention is that the last name, first name, and a unique ID are concatenated together with underscores to create the username.
     * @param s is a asynchronous callback function
     */
    public void getAccountData(String account_name,Response.Listener<JSONObject> s ) {
        RequestQueue queue = Volley.newRequestQueue(maind);
        JSONObject out = new JSONObject();
        try {
            out.put("name",account_name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest lastFMAuthRequest = new JsonObjectRequest(Request.Method.POST, url + "/getAccount", out, s, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.print(error.networkResponse);

            }

        });
        queue.add(lastFMAuthRequest);
    }

    /**
     *This creates a user on the EMPLOYEES database and returns the username for the new employee
     * @param lname the last name
     * @param fname the first name
     * @param permissionString a string of characters that correspond to permissions such as 1111
     * @param status could be used for different levels of the company. For example 2 might be manager and 0 be super user
     * @param password a password
     * @param address the address of the employee
     * @param cell the cellphone number
     * @param phone their primary phone number
     *
     */
    public void createAccount(String lname,String fname,String permissionString,int status,String password,String address,String cell, String phone,int hourlyRate,Response.Listener<JSONObject> s){
        RequestQueue queue = Volley.newRequestQueue(maind);
        JSONObject out = new JSONObject();
        try{
            out.put("lnam",lname);
            out.put("fname",fname);
            out.put("perm", permissionString);
            out.put("stat",status);
            out.put("pass",password);
            out.put("address", address);
            out.put("cell",cell);
            out.put("phone",phone);
            out.put("rate",hourlyRate);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest lastFMAuthRequest = new JsonObjectRequest(Request.Method.POST, url + "/createAccount", out, s, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.print(error.networkResponse);

            }

        });
        queue.add(lastFMAuthRequest);
    }
    public String dontUseThis(){
        return "Fuck the world";
    }

    /**
     *
     * @param user the user name such as Smith_Bob_1
     * @param isClockingIn enter 1 if clocking in, 0 if clocking out
     * @param s returns json object in the form in the response {error:int} if the error is 0 then is was added to the db
     */
    public void newEmployeeLog(String user,int isClockingIn,Response.Listener<JSONObject> s){
        RequestQueue queue = Volley.newRequestQueue(maind);
        JSONObject out = new JSONObject();
        try{
            out.put("user",user);
            out.put("isIn",isClockingIn);
            int temp;
            if(isClockingIn == 1){
                temp=0;
            }else{
                temp=1;
            }
            out.put("isOut",temp);
            out.put("timeStamp",((Number)System.currentTimeMillis()).intValue());
        } catch (JSONException e){
            e.printStackTrace();
        }
        JsonObjectRequest lastFMAuthRequest = new JsonObjectRequest(Request.Method.POST, url + "/log", out, s, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.print(error.networkResponse);

            }

        });
        queue.add(lastFMAuthRequest);
    }


    public void getlogs(final String user, final int index, Response.Listener<JSONObject> s){
        RequestQueue queue = Volley.newRequestQueue(maind);
        JSONObject out = new JSONObject();
        try{
            out.put("user",user);
            out.put("index",index);
        }catch(JSONException e){
            e.printStackTrace();
        }
        JsonObjectRequest lastFMAuthRequest = new JsonObjectRequest(Request.Method.POST, url + "/log", out,s , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.print(error.networkResponse);

            }

        });

    }




}
