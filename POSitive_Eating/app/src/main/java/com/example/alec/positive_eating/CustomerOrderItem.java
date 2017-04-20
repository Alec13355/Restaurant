package com.example.alec.positive_eating;

/**
 * @author Christian Shinkle
 * The CustomerOrderItem class is used as a simple object to contain to String per order,
 * entree and side. The main purpose is to be able to call the toString method to create a proper
 * String for the CustomerOrderList class.
 */
public class CustomerOrderItem {

    private String entreeDesc, sideDesc, entreeName, sideName;
    /**
     * Initializes instance variables entree and side.
     * @param theEntreeDesc
     * @param theSideDesc
     * @param theEntreeName
     * @param theSideName
     */
    public CustomerOrderItem(String theEntreeDesc, String theSideDesc, String theEntreeName,
                             String theSideName) {
        entreeDesc = theEntreeDesc;
        sideDesc = theSideDesc;
        entreeName = theEntreeName;
        sideName = theSideName;
    }
    /**
     * Returns entree.
     * @return
     */
    public String getEntreeDesc() {
        return entreeDesc;
    }
    /**
     * Returns side.
     * @return
     */
    public String getSideDesc() {
        return sideDesc;
    }

    public String getEntreeName() {
        return entreeName;
    }

    public String getSideName() {
        return sideName;
    }

    public void setEntreeDesc(String set) {
        entreeDesc = new String(set);
    }

    public void setEntreeName(String set) {
        entreeName = new String(set);
    }
    /**
     * Sets side.
     * @param set
     */
    public void setSideDesc(String set) {
        sideDesc = new String(set);
    }

    public void setSideName(String set) {
        sideName = new String(set);
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
        if(entreeDesc == null){
            tmp+="(None)";
        } else {
            tmp+=entreeDesc;
        }
        tmp+="\nSide: ";
        if(sideDesc==null) {
            tmp+="(None)";
        } else {
            tmp+=sideDesc;
        }
        return tmp;
    }
}
