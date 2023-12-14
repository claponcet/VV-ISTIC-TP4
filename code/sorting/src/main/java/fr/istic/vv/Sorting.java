package fr.istic.vv;
import java.util.Arrays;
import java.util.Comparator;

public class Sorting {

    public static <T>  T[] bubblesort(T[] array, Comparator<T> comparator) { 

        boolean swapped;
        do {
            swapped = false;
            for (int i = 1; i < array.length; i++) {
                if (comparator.compare(array[i - 1], array[i]) > 0) {
                    swap(array, i - 1, i);
                    swapped = true;
                }
            }
        } while (!swapped);

        return null; 
    }

    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static <T> T[] quicksort(T[] array, Comparator<T> comparator)  { 
        quickSortRec(array, comparator, 0, array.length - 1);
        return array;
    }

    private static <T> void quickSortRec(T[] array, Comparator<T> comparator, int low, int high) {
        if (low < high) {
            int partitionIndex = partition(array, comparator, low, high);

            quickSortRec(array, comparator, low, partitionIndex - 1);
            quickSortRec(array, comparator, partitionIndex + 1, high);
        }
    }

    private static <T> int partition(T[] array, Comparator<T> comparator, int low, int high) {
        T pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (comparator.compare(array[j], pivot) <= 0) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, high, i);
        return i + 1;
    }

    public static <T> T[] mergesort(T[] array, Comparator<T> comparator) { 
        mergeSortRec(array, comparator);
        return array;
    }

    public static <T> void mergeSortRec(T[] array, Comparator<T> comparator) {
        int n = array.length;
        if (n > 1) {
            int mid = n / 2;

            T[] left = Arrays.copyOfRange(array, 0, mid);
            T[] right = Arrays.copyOfRange(array, mid, n);

            // Recursively sort each half
            mergeSortRec(left, comparator);
            mergeSortRec(right, comparator);

            // Merge the sorted halves
            merge(array, comparator, left, right);
        }
    }

    public static <T> void merge(T[] array, Comparator<T> comparator, T[] left, T[] right) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (comparator.compare(left[i], right[j]) <= 0) {
                array[k] = left[i];
                i++;
            } else {
                array[k] = right[j];
                j++;
            }
            k++;
        }

        while (i < left.length) {
            array[k] = left[i];
            i++;
            k++;
        }

        while (j < right.length) {
            array[k] = right[j];
            j++;
            k++;
        }
    }
}
