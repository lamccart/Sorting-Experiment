/*
 * NAME: Liam McCarthy
 * PID: A14029718
 */

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.*;

/**
 * Sorting algorithms for insertion, merge, and quick sorts as well as helper methods in order to do so
 *
 * @author Liam McCarthy
 * @since 10/28/2018
 */

public class Sorts {

    /**
     * This method implements an insertion sort to sort a list of pairs increasing by their count.
     *
     * @param list A list of pairs that are to be sorted
     * @param startIndex Index of the list to start on (Inclusive)
     * @param endIndex Index of list to end on (Exclusive)
     */
    public static void insertionSort(ArrayList<Pair> list, int startIndex, int endIndex) {
        int j;

        for (int i = startIndex; i < endIndex; i++) {
            j = i;
            // Insert numbers[i] into sorted part
            // stopping once numbers[i] in correct position
            while (j > 0 && list.get(j).getCount() < list.get(j - 1).getCount()) {

                // Swap numbers[j] and numbers[j - 1]
                Collections.swap(list, j, j-1);
                j--;
            }
        }
    }

    /**
     * This method implements a merge sort to sort a list of pairs increasing by their count.
     *
     * @param list A list of pairs that are to be sorted
     * @param startIndex Index of the list to start on (Inclusive)
     * @param endIndex Index of list to end on (Exclusive)
     */
    public static void mergeSort(ArrayList<Pair> list, int startIndex, int endIndex) {

        int i;

        if (startIndex < endIndex) {
            i = (startIndex + endIndex) / 2; // Find the midpoint in the partition

            // Recursively sort left and right partitions
            mergeSort(list, startIndex, i);
            mergeSort(list, i + 1, endIndex);

            // Merge left and right partition in sorted order
            merge(list, startIndex, i, endIndex);
        }
    }

    /**
     * This method implements a helper method in order to merge all of the split lists from mergeSort
     *
     * @param list A list of pairs that are to be sorted
     * @param startIndex Index of the list to start on (Inclusive)
     * @param i The middle index that the list is split by
     * @param endIndex Index of list to end on (Exclusive)
     */
    private static void merge(ArrayList<Pair> list, int startIndex, int i, int endIndex) {
        int mergedSize = endIndex - startIndex + 1;             // Size of merged partition
        int mergePos = 0;                         // Position to insert merged number
        int leftPos;                      // Position of elements in left partition
        int rightPos;                        // Position of elements in right partition
        ArrayList<Pair> mergedNumbers = new ArrayList<>(); // Dynamically allocates temporary array for merged numbers

        leftPos = startIndex;                         // Initialize left partition position
        rightPos = i + 1;                   // Initialize right partition position

        // Add smallest element from left or right partition to merged numbers
        while (leftPos <= i && rightPos <= endIndex) {
            if (list.get(leftPos).getCount() <= list.get(rightPos).getCount()) {
                mergedNumbers.add(mergePos, list.get(leftPos));
                leftPos++;
            } else {
                mergedNumbers.add(mergePos, list.get(rightPos));
                rightPos++;

            }
            mergePos++;
        }

        // If left partition is not empty, add remaining elements to merged numbers
        while (leftPos <= i) {
            mergedNumbers.add(mergePos, list.get(leftPos));
            leftPos++;
            mergePos++;
        }

        // If right partition is not empty, add remaining elements to merged numbers
        while (rightPos <= endIndex) {
            mergedNumbers.add(mergePos, list.get(rightPos));
            rightPos++;
            mergePos++;
        }

        // Copy merge number back to numbers
        for (mergePos = 0; mergePos < mergedSize; mergePos++) {
            list.set(startIndex + mergePos, mergedNumbers.get(mergePos));
        }
    }

    /**
     * This method implements a quick sort to sort a list of pairs increasing by their count.
     *
     * @param list A list of pairs that are to be sorted
     * @param startIndex Index of the list to start on (Inclusive)
     * @param endIndex Index of list to end on (Exclusive)
     */
    public static void quickSort(ArrayList<Pair> list, int startIndex, int endIndex) {

        // Base case: If there are 1 or zero elements to sort,
        // partition is already sorted
        if (startIndex >= endIndex) {
            return;
        }

        // Partition the data within the array. Value j returned
        // from partitioning is location of last element in low partition.
        int i = partition(list, startIndex, endIndex);

        // Recursively sort low partition (i to j) and
        // high partition (j + 1 to k)
        quickSort(list, startIndex, i);
        quickSort(list, i + 1, endIndex);
    }

    /**
     * This method implements a partition for the quick sort for each half.
     *
     * @param list A list of pairs that are to be sorted
     * @param i Index of the list to start on (Inclusive)
     * @param k Index of list to end on (Exclusive)
     */
    private static int partition(ArrayList<Pair> list, int i, int k) {
        /* Initialize variables */

        /* Pick middle value as pivot */
        int midpoint = i + (k - i) / 2;
        int pivot = list.get(midpoint).getCount();

        int lo = i;
        int hi = k;

        boolean done = false;
        while (!done) {
            /* Increment l while numbers[l] < pivot */
            while ( list.get(lo).getCount() < pivot) {
                lo++;
            }

            /* Decrement h while pivot < numbers[h] */
            while (pivot < list.get(hi).getCount()) {
                hi--;
            }
            /* If there are zero or one items remaining, all numbers are partitioned. Return h */
            if (lo >= hi) {
                done = true;
            } else {
                /* Swap list[lo] and list[hi], update lo and hi */
                Collections.swap(list, lo, hi);
                lo++;
                hi--;
            }
        }
        return hi;
    }
    
}