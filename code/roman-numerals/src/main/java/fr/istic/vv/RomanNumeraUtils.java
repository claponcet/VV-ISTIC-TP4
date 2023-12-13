package fr.istic.vv;

import java.util.HashMap;
import java.util.Map;

public class RomanNumeraUtils {
    public static final Map<String, Integer> numerals = new HashMap<String, Integer>() {{
        put("I", 1);
        put("IV", 4);
        put("V", 5);
        put("IX", 9);
        put("X", 10);
        put("L", 50);
        put("XC", 90);
        put("C", 100);
        put("D", 500);
        put("M", 1000);
    }};

    public static boolean isValidRomanNumeral(String value) {
        return value.matches("[IVXLCDM]*") &&
                !value.matches(".*I{4,}.*") &&
                !value.matches(".*X{4,}.*") &&
                !value.matches(".*C{4,}.*") &&
                !value.matches(".*M{4,}.*") &&
                !value.matches(".*V{2,}.*") &&
                !value.matches(".*L{2,}.*") &&
                !value.matches(".*D{2,}.*") &&
                !value.matches(".*I{2,}V.*") &&
                !value.matches(".*I{2,}X.*") &&
                !value.matches(".*X{2,}C.*");
    }

    public static int parseRomanNumeral(String numeral) {
        if (numeral.length() == 0) {
            return 0;
        }

        if (numeral.length() == 1) {
            return numerals.get(numeral);
        }

        char left, right;
        int value = 0;
        for (int i = 0; i < numeral.length(); i++) {
            left = numeral.charAt(i);
            right = i + 1 < numeral.length() ? numeral.charAt(i + 1) : '\0';
            value += numerals.containsKey(left + "" + right) ? numerals.get(left + "" + right) : numerals.get(left + "");
        }

        return value;
    }

    public static String toRomanNumeral(int number) {

        String res = "";

        if (number == 0) {
            return res;
        }


        for (Map.Entry<String, Integer> entry : numerals.entrySet()) {
            while (number >= entry.getValue()) {
                res += entry.getKey();
                number -= entry.getValue();
                break;
            }
        }

        return res;
    }
}
