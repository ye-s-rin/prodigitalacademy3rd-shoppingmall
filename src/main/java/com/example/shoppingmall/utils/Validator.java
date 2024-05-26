package com.example.shoppingmall.utils;

import java.util.regex.Pattern;

public class Validator {

    public static boolean isAlpha(String name) {
        return Pattern.matches("^[a-zA-Z]*$", name);
    }

    public static boolean isNumber(int price) {
        return Pattern.matches("^[0-9]*$", price + "");
    }

    public static Integer checkRange(Integer i) {
        if (i == null) {
            return null;
        }
        return i != null && i < 1 ? 1 : i;
    }
}
