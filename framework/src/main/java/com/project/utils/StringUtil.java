package com.project.utils;

public class StringUtil {
    public static boolean isBlank(String str) {
        if (str == null || str.isEmpty()) {
            return true;
        }
        for(Character c : str.toCharArray()) {
            if (!Character.isWhitespace(c)) {return false;}
        }
        return true;
    }
}
