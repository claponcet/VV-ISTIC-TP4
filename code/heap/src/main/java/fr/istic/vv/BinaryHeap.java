package fr.istic.vv;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BinaryHeap<T> {

    List<T> heap;
    Comparator<T> comparator;

    public BinaryHeap(Comparator<T> comparator) {
        heap = new ArrayList<>();
        this.comparator = comparator;
    }

    public T pop() {
        if (heap.isEmpty()) {
            return null;
        }

        T element = heap.get(0);

        T lastElement = heap.get(heap.size() - 1);
        heap.remove(heap.size() - 1);

        if (heap.size() > 0) {
            // Replace the root of the heap with the last element on the last level.
            heap.set(0, lastElement);
            heapifyDown(0);
        }

        return element;
    }

    public T peek() {
        return heap.get(0);
    }

    public void push(T element) {
        // Add the element to the bottom level of the heap at the leftmost open space.
        heap.add(element);
        heapifyUp(heap.size() - 1);
    }

    public int count() {
        return heap.size();
    }

    private void heapifyDown(int index) {
        int leftIndex = index * 2 + 1;
        int rightIndex = index * 2 + 2;
        int smallestIndex = index;

        // Compare the new root with its children; if they are in the correct order,
        // stop.

        if (leftIndex < heap.size() && comparator.compare(heap.get(leftIndex), heap.get(smallestIndex)) < 0) {
            smallestIndex = leftIndex;
        }

        if (rightIndex < heap.size() && comparator.compare(heap.get(rightIndex), heap.get(smallestIndex)) < 0) {
            smallestIndex = rightIndex;
        }

        // If not, swap the element with one of its children and return to the previous
        // step. (Swap with its smaller child in a min-heap and its larger child in a
        // max-heap.)

        if (smallestIndex != index) {
            swap(index, smallestIndex);
            heapifyDown(smallestIndex);
        }
    }

    private void heapifyUp(int index) {
        int parentIndex = (index - 1) / 2;

        // Compare the added element with its parent; if they are in the correct order,
        // stop
        if (index > 0 && comparator.compare(heap.get(index), heap.get(parentIndex)) < 0) {
            // If not, swap the element with its parent and return to the previous step.
            swap(index, parentIndex);
            heapifyUp(parentIndex);
        }
    }

    private void swap(int index1, int index2) {
        T temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }

    @Override
    public String toString() {
        int index = 0;
        int span = 1;

        String s = "";

        while (index < heap.size()) {
            for (int i = index; i < index + span && i < heap.size(); i++) {
                s += heap.get(i) + " ";
            }
            s += "\n";
            index += span;
            span *= 2;
        }

        return s;
    }
}
