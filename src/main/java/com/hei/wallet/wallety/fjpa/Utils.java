package com.hei.wallet.wallety.fjpa;

public class Utils {
    public static String toCamelCase(String text){
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }
}
