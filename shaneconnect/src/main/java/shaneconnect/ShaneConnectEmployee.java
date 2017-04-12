package shaneconnect;

/**
 * Created by shane on 4/11/17.
 */

/**
 * Class that represents an employee
 */
public class ShaneConnectEmployee {

    /**
     * the first name of the employee
     */
    private String first;

    /**
     * the last name of the employee
     */
    private String last;
    /**
     * the id number of the employee
     */
    private int id;

    /**
     * constructor using the username
     * @param user the employee username
     */
    public ShaneConnectEmployee(String user){
        String buffer ="";
        int state = 0;
        for(int x=0;x<user.length();x++){
            if(user.charAt(x) == '_'){
                switch (state){
                    case 0:
                        this.last = buffer;
                        break;
                    case 1:
                        this.first = buffer;
                        break;
                }
                buffer="";
            }else{
                buffer+=user.charAt(x);
            }

        }
        this.id = Integer.parseInt(buffer);

    }

    /**
     * Constructor using the firstname, lastname, id
     * @param first the first name of the employee
     * @param last the last name of the employee
     * @param id the id number of the employee
     */
    public ShaneConnectEmployee(String first, String last, int id){
        this.id=id;
        this.last=last;
        this.first=first;
    }

    /**
     *
     * @return returns the user name
     */
    public String getUserName(){
        return last + "_" + first + "_" + id;
    }

    /**
     *
     * @return returns the first name
     */
    public String getFirst(){
        return this.first;
    }

    /**
     *
     * @return returns the last name
     */
    public String getLast(){
        return this.last;
    }

    /**
     *
     * @return returns the employee id
     */
    public int getEmployeeId(){
        return this.id;
    }





}
