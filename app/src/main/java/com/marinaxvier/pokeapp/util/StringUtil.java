package com.marinaxvier.pokeapp.util;

public class StringUtil {


    public static String toProperCase(String string){
        String properCase = string.substring(0,1).toUpperCase() + string.substring(1);

        return properCase;
    }
}
