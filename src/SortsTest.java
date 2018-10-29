/*
 * NAME: Liam McCarthy
 * PID: A14029718
 */

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

/**
 * Testing methods for all the sorting algorithms
 *
 * @author Liam McCarthy
 * @since 10/28/2018
 */

public class SortsTest {

    private static final int ARRAY_SIZE = 10;
    private static final int LAST_TWO = 2;

    @Test
    public void testInsertionSort() {
        //Initialize the various arrays that will be tested
        ArrayList<Pair> expectedArray = new ArrayList<>();
        ArrayList<Pair> sortedArray = new ArrayList<>();
        ArrayList<Pair> reversedArray = new ArrayList<>();
        ArrayList<Pair> mostlySorted = new ArrayList<>();
        ArrayList<Pair> randomArray = new ArrayList<>();
        Random rand = new Random();
        //Create expected and already sorted array
        for(int i = 1; i <= ARRAY_SIZE; i++){
            sortedArray.add(new Pair("sorted", i));
            expectedArray.add(new Pair("expected", i));
        }
        //Create the reversed array
        for(int i = 0; i < ARRAY_SIZE; i++){
            reversedArray.add(new Pair("reverse", ARRAY_SIZE-i));
        }
        //Create the first 8 already sorted array
        for(int i = 1; i <= ARRAY_SIZE - LAST_TWO; i++){
            mostlySorted.add(new Pair("mostly", i));
        }
        //Add the last two elements that are flipped
        mostlySorted.add(new Pair("mostly", ARRAY_SIZE));
        mostlySorted.add(new Pair("mostly", ARRAY_SIZE-1));
        //Create the random array
        randomArray.add(new Pair("random", 4));
        randomArray.add(new Pair("random", 8));
        randomArray.add(new Pair("random", 6));
        randomArray.add(new Pair("random", 7));
        randomArray.add(new Pair("random", 1));
        randomArray.add(new Pair("random", 3));
        randomArray.add(new Pair("random", 10));
        randomArray.add(new Pair("random", 9));
        randomArray.add(new Pair("random", 5));
        randomArray.add(new Pair("random", 2));

        //Sort all of the arrays
        Sorts.insertionSort(sortedArray, 0, sortedArray.size());
        Sorts.insertionSort(reversedArray, 0, reversedArray.size());
        Sorts.insertionSort(mostlySorted, 0, mostlySorted.size());
        Sorts.insertionSort(randomArray, 0, randomArray.size());
        boolean sortedCorrect = true;
        boolean reversedCorrect = true;
        boolean mostlyCorrect = true;
        boolean randomCorrect = true;
        //Check if the expected array is the same as all the sorted arrays
        for(int i = 0; i < expectedArray.size(); i++) {
            if(expectedArray.get(i).getCount() != sortedArray.get(i).getCount()){
                sortedCorrect = false;
            }
        }
        assertTrue(sortedCorrect);
        for(int i = 0; i < expectedArray.size(); i++) {
            if(expectedArray.get(i).getCount() != reversedArray.get(i).getCount()){
                reversedCorrect = false;
            }
        }
        assertTrue(reversedCorrect);
        for(int i = 0; i < expectedArray.size(); i++) {
            if(expectedArray.get(i).getCount() != mostlySorted.get(i).getCount()){
               mostlyCorrect = false;
            }
        }
        assertTrue(mostlyCorrect);
        for(int i = 0; i < expectedArray.size(); i++) {
            if(expectedArray.get(i).getCount() != randomArray.get(i).getCount()){
                randomCorrect = false;
            }
        }
        assertTrue(randomCorrect);

    }

    @Test
    public void testMergeSort() {
        //Initialize the various arrays that will be tested
        ArrayList<Pair> expectedArray = new ArrayList<>();
        ArrayList<Pair> sortedArray = new ArrayList<>();
        ArrayList<Pair> reversedArray = new ArrayList<>();
        ArrayList<Pair> mostlySorted = new ArrayList<>();
        ArrayList<Pair> randomArray = new ArrayList<>();
        Random rand = new Random();
        //Create expected and already sorted array
        for(int i = 1; i <= ARRAY_SIZE; i++){
            sortedArray.add(new Pair("sorted", i));
            expectedArray.add(new Pair("expected", i));
        }
        //Create the reversed array
        for(int i = 0; i < ARRAY_SIZE; i++){
            reversedArray.add(new Pair("reverse", ARRAY_SIZE-i));
        }
        //Create the first 8 already sorted array
        for(int i = 1; i <= ARRAY_SIZE - LAST_TWO; i++){
            mostlySorted.add(new Pair("mostly", i));
        }
        //Add the last two elements that are flipped
        mostlySorted.add(new Pair("mostly", ARRAY_SIZE));
        mostlySorted.add(new Pair("mostly", ARRAY_SIZE-1));
        //Create the random array
        randomArray.add(new Pair("random", 4));
        randomArray.add(new Pair("random", 8));
        randomArray.add(new Pair("random", 6));
        randomArray.add(new Pair("random", 7));
        randomArray.add(new Pair("random", 1));
        randomArray.add(new Pair("random", 3));
        randomArray.add(new Pair("random", 10));
        randomArray.add(new Pair("random", 9));
        randomArray.add(new Pair("random", 5));
        randomArray.add(new Pair("random", 2));
        //Sort all of the arrays
        Sorts.mergeSort(sortedArray, 0, sortedArray.size()-1);
        Sorts.mergeSort(reversedArray, 0, reversedArray.size()-1);
        Sorts.mergeSort(mostlySorted, 0, mostlySorted.size()-1);
        Sorts.mergeSort(randomArray, 0, randomArray.size()-1);
        boolean sortedCorrect = true;
        boolean reversedCorrect = true;
        boolean mostlyCorrect = true;
        boolean randomCorrect = true;
        //Check if the expected array is the same as all the sorted arrays
        for(int i = 0; i < expectedArray.size(); i++) {
            if(expectedArray.get(i).getCount() != sortedArray.get(i).getCount()){
                sortedCorrect = false;
            }
        }
        assertTrue(sortedCorrect);
        for(int i = 0; i < expectedArray.size(); i++) {
            if(expectedArray.get(i).getCount() != reversedArray.get(i).getCount()){
                reversedCorrect = false;
            }
        }
        assertTrue(reversedCorrect);
        for(int i = 0; i < expectedArray.size(); i++) {
            if(expectedArray.get(i).getCount() != mostlySorted.get(i).getCount()){
                mostlyCorrect = false;
            }
        }
        assertTrue(mostlyCorrect);
        for(int i = 0; i < expectedArray.size(); i++) {
            if(expectedArray.get(i).getCount() != randomArray.get(i).getCount()){
                randomCorrect = false;
            }
        }
        assertTrue(randomCorrect);
    }

    @Test
    public void testQuickSort() {
        //Initialize the various arrays that will be tested
        ArrayList<Pair> expectedArray = new ArrayList<>();
        ArrayList<Pair> sortedArray = new ArrayList<>();
        ArrayList<Pair> reversedArray = new ArrayList<>();
        ArrayList<Pair> mostlySorted = new ArrayList<>();
        ArrayList<Pair> randomArray = new ArrayList<>();
        Random rand = new Random();
        //Create expected and already sorted array
        for(int i = 1; i <= ARRAY_SIZE; i++){
            sortedArray.add(new Pair("sorted", i));
            expectedArray.add(new Pair("expected", i));
        }
        //Create the reversed array
        for(int i = 0; i < ARRAY_SIZE; i++){
            reversedArray.add(new Pair("reverse", ARRAY_SIZE-i));
        }
        //Create the first 8 already sorted array
        for(int i = 1; i <= ARRAY_SIZE - LAST_TWO; i++){
            mostlySorted.add(new Pair("mostly", i));
        }
        //Add the last two elements that are flipped
        mostlySorted.add(new Pair("mostly", ARRAY_SIZE));
        mostlySorted.add(new Pair("mostly", ARRAY_SIZE-1));
        //Create the random array
        randomArray.add(new Pair("random", 4));
        randomArray.add(new Pair("random", 8));
        randomArray.add(new Pair("random", 6));
        randomArray.add(new Pair("random", 7));
        randomArray.add(new Pair("random", 1));
        randomArray.add(new Pair("random", 3));
        randomArray.add(new Pair("random", 10));
        randomArray.add(new Pair("random", 9));
        randomArray.add(new Pair("random", 5));
        randomArray.add(new Pair("random", 2));
        //Sort all of the arrays
        Sorts.quickSort(sortedArray, 0, sortedArray.size()-1);
        Sorts.quickSort(reversedArray, 0, reversedArray.size()-1);
        Sorts.quickSort(mostlySorted, 0, mostlySorted.size()-1);
        Sorts.quickSort(randomArray, 0, randomArray.size()-1);
        boolean sortedCorrect = true;
        boolean reversedCorrect = true;
        boolean mostlyCorrect = true;
        boolean randomCorrect = true;
        //Check if the expected array is the same as all the sorted arrays
        for(int i = 0; i < expectedArray.size(); i++) {
            if(expectedArray.get(i).getCount() != sortedArray.get(i).getCount()){
                sortedCorrect = false;
            }
        }
        assertTrue(sortedCorrect);
        for(int i = 0; i < expectedArray.size(); i++) {
            if(expectedArray.get(i).getCount() != reversedArray.get(i).getCount()){
                reversedCorrect = false;
            }
        }
        assertTrue(reversedCorrect);
        for(int i = 0; i < expectedArray.size(); i++) {
            if(expectedArray.get(i).getCount() != mostlySorted.get(i).getCount()){
                mostlyCorrect = false;
            }
        }
        assertTrue(mostlyCorrect);
        for(int i = 0; i < expectedArray.size(); i++) {
            if(expectedArray.get(i).getCount() != randomArray.get(i).getCount()){
                randomCorrect = false;
            }
        }
        assertTrue(randomCorrect);
    }
}