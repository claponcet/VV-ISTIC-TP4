package fr.istic.vv;

import java.util.Comparator;
import java.util.List;

import net.jqwik.api.*;

public class BinaryHeapTest<T> {
    @Property
    boolean isHeapSorted(@ForAll List<Integer> list) {
        Comparator<Integer> comparator = (a, b) -> a.compareTo(b);
        BinaryHeap<Integer> heap = new BinaryHeap<Integer>(comparator);

        for (Integer i : list) {
            heap.push(i);
        }

        Integer current, previous = null;
        while (heap.count() > 0) {
            current = heap.pop();
            if (previous != null && comparator.compare(current, previous) < 0) {
                return false;
            }
            previous = current;
        }

        return true;
    }
}
