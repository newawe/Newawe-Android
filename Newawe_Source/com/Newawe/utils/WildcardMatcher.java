package com.Newawe.utils;

public class WildcardMatcher {
    public static boolean match(String text, String pattern) {
        for (String card : pattern.split("\\*")) {
            int idx = text.indexOf(card);
            if (idx == -1) {
                return false;
            }
            text = text.substring(card.length() + idx);
        }
        return true;
    }
}
