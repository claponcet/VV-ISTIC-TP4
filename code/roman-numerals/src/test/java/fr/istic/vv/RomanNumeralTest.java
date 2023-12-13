package fr.istic.vv;

import net.jqwik.api.*;
import net.jqwik.api.constraints.Positive;

public class RomanNumeralTest {

    private boolean isRepeatedThriceAtMost(String aRomanNumber, char aCharacter) {
        return !aRomanNumber.matches(".*" + aCharacter + aCharacter + aCharacter + aCharacter + ".*");
    }

    private boolean isNotRepeated(String aRomanNumber, char aCharacter) {
        return !aRomanNumber.matches(".*" + aCharacter + aCharacter + ".*");
    }

    @Property
    boolean absoluteValueOfAllNumbersIsPositive(@ForAll @Positive int anInteger) {
        return Math.abs(anInteger) >= 0;
    }

    @Property
    boolean romanNumberOnlyContainsValidCharacters(@ForAll int anInteger) {
        String aRomanNumber = RomanNumeraUtils.toRomanNumeral(anInteger);

        return aRomanNumber.matches("[IVXLCDM]*");
    }

    @Property
    boolean romanNumberRepetitionRule1(@ForAll int anInteger) {
        String aRomanNumber = RomanNumeraUtils.toRomanNumeral(anInteger);

        return isRepeatedThriceAtMost(aRomanNumber, 'I') &&
                isRepeatedThriceAtMost(aRomanNumber, 'X') &&
                isRepeatedThriceAtMost(aRomanNumber, 'C') &&
                isRepeatedThriceAtMost(aRomanNumber, 'M');

    }

    @Property
    boolean romanNumberRepetitionRule2(@ForAll int anInteger) {
        String aRomanNumber = RomanNumeraUtils.toRomanNumeral(anInteger);

        return isNotRepeated(aRomanNumber, 'V') &&
                isNotRepeated(aRomanNumber, 'L') &&
                isNotRepeated(aRomanNumber, 'D');
    }

    @Property
    boolean hasOnlyOneSubstractor(@ForAll int anInteger) {
        String aRomanNumber = RomanNumeraUtils.toRomanNumeral(anInteger);

        if (aRomanNumber.length() < 2) {
            return true;
        }

        boolean res = true;
        char left, right;
        int leftValue, rightValue;
        for (int i = 0; i < aRomanNumber.length(); i++) {
            left = aRomanNumber.charAt(i);
            right = i + 1 < aRomanNumber.length() ? aRomanNumber.charAt(i + 1) : '\0';
            leftValue = RomanNumeraUtils.numerals.get(left + "");
            rightValue = RomanNumeraUtils.numerals.get(right + "");
            if (leftValue < rightValue) {
                res = Character.toString(right).matches("[CXV]") && (leftValue == rightValue / 10 || leftValue == rightValue / 5);
            }
        }

        return res;
    }
}
