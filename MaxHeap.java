
import java.lang.Comparable;
import java.util.List;
import java.util.ArrayList;

public class MaxHeap <T extends Comparable<? super T>> {

    // used to store the heap
    private ArrayList<T> array;

    public MaxHeap(List<T> list) {
        array = new ArrayList<T>(list);
        buildMaxHeap();
    }

    public MaxHeap() {
        array = new ArrayList<T>();
    }

    // get the size
    public Integer size() { return array.size(); }

    // get the maximum value in the heap
    public T max() {
        if (array.size() > 0)
            return array.get(0);
        else
            return null;
    }

    // pop the maximum value off the top of the heap
    public T extractMax() {
        if (array.size() < 1)
            return null;

        T max = array.get(0);
        array.set(0, array.get(array.size() - 1));
        array.remove(array.size() - 1);

        if (array.size() > 0)
            heapify(0);

        return max;
    }

    // return the heap as a List
    public List<T> toList() {
        return new ArrayList<T>(array);
    }


    // add value to the heap
    public void insert(T value) {
        array.add(value);
        reverseHeapify(array.size() - 1);
    }

    // return a sorted list, ascending
    public List<T> heapsort() {
        List<T> list = new ArrayList<T>(array); // already a heap

        int size = list.size(); // variable size
        for (int i = list.size() - 1; i >= 1; --i) {
            // swap
            swap(i, 0, list);
            // heapify
            size -= 1;
            heapify(0, list, size);
        }
        return list;
    }

    // float a node up the tree
    private void reverseHeapify(int idx) {
        int parent = idx / 2;
        if (array.get(idx).compareTo(array.get(parent)) > 0) {
            swap(idx, parent);
            reverseHeapify(parent);
        }
    }

    // float node down the tree
    private void heapify(int idx) {
        heapify(idx, array, array.size());
    }

    // float node down the tree
    private void heapify(int idx, List<T> list, int size) {
        if (idx < 0 || idx > size - 1)
            return;

        int max = idx;
        int l = left(idx);
        int r = right(idx);

        // left
        if (l < size && list.get(max).compareTo(list.get(l)) < 0) {
            max = l;
        }

        // right
        if (r < size && list.get(max).compareTo(list.get(r)) < 0) {
            max = r;
        }

        if (idx != max) {
            swap(idx, max, list);
            heapify(max, list, size);
        }
    }

    // construct a heap out of array
    private void buildMaxHeap() {
        for (int i = array.size() / 2 - 1; i >= 0; --i) {
            heapify(i);
        }
    }

    // some extra utility methods
    private int left(int idx) { return (idx << 1) + 1; }
    private int right(int idx) { return (idx << 1) + 2; }
    private void swap(int a, int b) {
        swap(a, b, array);
    }
    private void swap(int a, int b, List<T> list) {
        T temp = list.get(a);
        list.set(a, list.get(b));
        list.set(b, temp);
    }


}
