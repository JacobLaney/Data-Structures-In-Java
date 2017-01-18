// Jacob Laney - 2017
// Implementations of common sorting algorithms provided:
// * quicksort
// * insertionSort
// * selectionSort
// * bubblesort
// * heapSort

import java.util.List;
import java.util.ArrayList;
import java.lang.Comparable;

public class Sorts {

    // swap two values in a list
    private static <T extends Comparable<? super T>> void swap (List<T> list, int a, int b) {
        T temp = list.get(a);
        list.set(a, list.get(b));
        list.set(b, temp);

        return;
    }

    // partition one segment of a list
    private static <T extends Comparable<? super T>> int partition (List<T> list, int left, int right, int pivot) {
        ++right;
        for (;;) {
            // track right
            while (list.get(--right).compareTo(list.get(pivot)) > 0);

            // track left
            while (left + 1 < list.size() && list.get(++left).compareTo(list.get(pivot)) < 0);

            if (left >= right)
                break;
            else {
                Sorts.<T>swap(list, left, right);
            }
        }
        Sorts.<T>swap(list, right, pivot);
        return right;
    }

    // quicksort algorithm
    private static <T extends Comparable<? super T>> void qsort (List<T> list, int left, int right) {
        if (left >= right) {
            return;
        }
        else {
            int pivot = left;
            int part = Sorts.<T>partition(list, left, right, pivot);
            Sorts.<T>qsort(list, left, part - 1);
            Sorts.<T>qsort(list, part + 1, right);
        }

        return;
    }

    // quicksort public call
    public static <T extends Comparable<? super T>> void quicksort (List<T> list) {
        Sorts.<T>qsort(list, 0, list.size() - 1);

        return;
    }

    // insertion sort
    public static <T extends Comparable<? super T>> void insertionSort (List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            int j = i;
            while (j - 1 >= 0 && list.get(j).compareTo(list.get(j - 1)) < 0) {
                swap(list, j, j - 1);
                --j;
            }
        }
        return;
    }

    // selection sort
    public static <T extends Comparable<? super T>> void selectionSort (List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            int min = i;
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j).compareTo(list.get(min)) < 0) {
                    min = j;
                }
            }
            Sorts.<T>swap(list, min, i);
        }
        return;
    }

    public static <T extends Comparable<? super T>> void bubbleSort (List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size() - 1; j++) {
                if (list.get(j).compareTo(list.get(j + 1)) > 0) {
                    swap(list, j, j + 1);
                }
            }
        }
        return;
    }

    // heapsort
    public static <T extends Comparable<? super T>> void heapSort (List<T> list) {
        MaxHeap<T> heap = new MaxHeap<>(list);
        List<T> sortedList = heap.heapsort();   // get sorted list

        // copy into the list reference
        for (int i = 0; i < list.size(); i++) {
            list.set(i, sortedList.get(i));
        }
    }
}
