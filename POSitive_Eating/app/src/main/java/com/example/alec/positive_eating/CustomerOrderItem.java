package com.example.alec.positive_eating;

/**
 * @author Christian Shinkle
 * The CustomerOrderItem class is used as a simple object to contain to String per order,
 * entree and side. The main purpose is to be able to call the toString method to create a proper
 * String for the CustomerOrderList class.
 */
public class CustomerOrderItem {

    private String entree, side;
    /**
     * Initializes instance variables entree and side.
     * @param theEntree
     * @param theSide
     */
    public CustomerOrderItem(String theEntree, String theSide) {
        entree = theEntree;
        side = theSide;
    }
    /**
     * Returns entree.
     * @return
     */
    public String getEntree() {
        return entree;
    }
    /**
     * Returns side.
     * @return
     */
    public String getSide() {
        return side;
    }
    /**
     * Sets entree.
     * @param set
     */
    public void setEntree(String set) {
        entree = new String(set);
    }
    /**
     * Sets side.
     * @param set
     */
    public void setSide(String set) {
        side = new String(set);
    }
    /**
     * Checks to see if entree or side are null.
     * If one of them are, it replaces the respective String with "(Null)".
     * Else it concatenates either entree or side to the String to be returned. .
     * @return
     */
    @Override
    public String toString() {
        String tmp = "Entree: ";
        if(entree == null){
            tmp+="(None)";
        } else {
            tmp+=entree;
        }
        tmp+="\nSide: ";
        if(side==null) {
            tmp+="(None)";
        } else {
            tmp+=side;
        }
        return tmp;
    }
}
