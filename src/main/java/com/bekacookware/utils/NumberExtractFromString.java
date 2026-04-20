package com.bekacookware.utils;
import java.util.regex.*;
public class NumberExtractFromString {

        public static String extractEuroValue(String text) {
            Pattern pattern = Pattern.compile("€\\s*(\\d+[,.]\\d+)");
            Matcher matcher = pattern.matcher(text);
            if (matcher.find()) {
                return matcher.group(1);
            }
            return null;
        }

        public static double extractProductPriceEuroAsDouble(String text) {
            String value = extractEuroValue(text);
            return value != null ? Double.parseDouble(value.replace(",", ".")) : 0.0;
    }
}
