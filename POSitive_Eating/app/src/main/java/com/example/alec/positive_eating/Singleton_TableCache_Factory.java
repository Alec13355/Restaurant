package com.example.alec.positive_eating;

import shaneconnect.ShaneConnect;
import shaneconnect.TableCache;

public class Singleton_TableCache_Factory {

    private static TableCache cache = null;

    public static TableCache getTableCache(ShaneConnect sc) {
        if(cache == null) {
            cache = new TableCache(sc);
        }
        return cache;
    }

    public static TableCache getTableCache() {
        return cache;
    }
}
