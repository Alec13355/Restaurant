package com.example.alec.positive_eating;

import java.util.ArrayList;

/**
 * Created by cshin on 4/24/2017.
 */

public class Singleton_OrderList {
    private static ArrayList<CustomerOrderItem> ol;

    public static void clearOrderList() {
        ol = null;
    }

    public static void initOrderList() {
        ol = new ArrayList<CustomerOrderItem>();
    }
    public static ArrayList<CustomerOrderItem> getOrderList() {
        return ol;
    }
}
