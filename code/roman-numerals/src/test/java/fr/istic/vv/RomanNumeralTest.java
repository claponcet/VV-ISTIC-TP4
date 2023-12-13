package fr.istic.vv;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.jqwik.api.*;

public class RomanNumeralTest {

    @Property
    boolean generatedNumbersAreValid(@ForAll int anInteger) {
        return RomanNumeraUtils.isValidRomanNumeral(RomanNumeraUtils.toRomanNumeral(anInteger));
    }

    @Property
    boolean isConversionReversible(@ForAll int anInteger) {
        String roman = RomanNumeraUtils.toRomanNumeral(anInteger);
        int integer = RomanNumeraUtils.parseRomanNumeral(roman);

        if (anInteger > 0 && anInteger <= 3999) {
            return anInteger == integer;
        }

        return integer == 0;
    }

    @Property
    boolean isStringValid(@ForAll String aString) {
        boolean isValid = RomanNumeraUtils.isValidRomanNumeral(aString);

        if (!aString.matches("[MDCLXVI]*")) {
            return isValid == false;
        }

        String[] repetitions = {
                "VV", "LL", "DD", "IIII", "XXXX", "CCCC", "MMMM"
        };

        // I, X, C, M cannot be repeated more than 3 times.
        // V, L, D cannot be repeated.
        for (String repetition : repetitions) {
            if (aString.matches(repetition)) {
                return isValid == false;
            }
        }

        // Check if numeral contains valid pairs (ie IV, IX, XC, XL, CD, CM)

        Map<String, Integer> numerals = new HashMap<String, Integer>() {
            {
                put("M", 1000);
                put("D", 500);
                put("C", 100);
                put("L", 50);
                put("X", 10);
                put("V", 5);
                put("I", 1);
            }
        };

        List<String> validPairs = List.of("IV", "IX", "XC", "XL", "CD", "CM");

        if (aString.length() > 1) {
            String currentNumeral = "", nextNumeral = "", pair = "";
            int currentNumeralValue = 0, nextNumeralValue = 0;

            int i = 0;
            while (i < aString.length()) {
                currentNumeral = aString.substring(i, i + 1);

                if (i < aString.length() - 1) {
                    nextNumeral = aString.substring(i + 1, i + 2);
                }

                currentNumeralValue = numerals.get(currentNumeral);
                nextNumeralValue = numerals.get(nextNumeral);
                pair = currentNumeral + nextNumeral;

                if (nextNumeralValue > currentNumeralValue && !validPairs.contains(pair)) {
                    return isValid == false;
                }

                i++;
            }
        }

        return true;
    }
}
