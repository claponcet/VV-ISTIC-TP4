package fr.istic.vv;
import java.util.Arrays;
import java.util.Comparator;

import net.jqwik.api.*;

public class SortingTest {

    @Property
    boolean isOutputTheSameForIntegerArray(@ForAll Integer[] array) {
        Comparator<Integer> comparator = (a, b) -> Integer.compare(a, b);

        Integer[] sorted_1 = Sorting.<Integer>bubblesort(array, comparator);
        Integer[] sorted_2 = Sorting.<Integer>quicksort(array, comparator);
        Integer[] sorted_3 = Sorting.<Integer>mergesort(array, comparator);

        return Arrays.equals(sorted_1, sorted_2) && Arrays.equals(sorted_2, sorted_3);
    }

    @Property
    boolean isOutputTheSameForStringArray(@ForAll String[] array) {
        Comparator<String> comparator = (a, b) -> a.compareTo(b);

        String[] sorted_1 = Sorting.<String>bubblesort(array, comparator);
        String[] sorted_2 = Sorting.<String>quicksort(array, comparator);
        String[] sorted_3 = Sorting.<String>mergesort(array, comparator);

        return Arrays.equals(sorted_1, sorted_2) && Arrays.equals(sorted_2, sorted_3);
    }

    @Property
    boolean isOutputTheSameForDoubleArray(@ForAll Double[] array) {
        Comparator<Double> comparator = (a, b) -> Double.compare(a, b);

        Double[] sorted_1 = Sorting.<Double>bubblesort(array, comparator);
        Double[] sorted_2 = Sorting.<Double>quicksort(array, comparator);
        Double[] sorted_3 = Sorting.<Double>mergesort(array, comparator);

        return Arrays.equals(sorted_1, sorted_2) && Arrays.equals(sorted_2, sorted_3);
    }

    @Property
    boolean isOutputTheSameForCharacterArray(@ForAll Character[] array) {
        Comparator<Character> comparator = (a, b) -> a.compareTo(b);

        Character[] sorted_1 = Sorting.<Character>bubblesort(array, comparator);
        Character[] sorted_2 = Sorting.<Character>quicksort(array, comparator);
        Character[] sorted_3 = Sorting.<Character>mergesort(array, comparator);

        return Arrays.equals(sorted_1, sorted_2) && Arrays.equals(sorted_2, sorted_3);
    }

    @Property
    boolean isOutputTheSameForBooleanArray(@ForAll Boolean[] array) {
        Comparator<Boolean> comparator = (a, b) -> Boolean.compare(a, b);

        Boolean[] sorted_1 = Sorting.<Boolean>bubblesort(array, comparator);
        Boolean[] sorted_2 = Sorting.<Boolean>quicksort(array, comparator);
        Boolean[] sorted_3 = Sorting.<Boolean>mergesort(array, comparator);

        return Arrays.equals(sorted_1, sorted_2) && Arrays.equals(sorted_2, sorted_3);
    }
}