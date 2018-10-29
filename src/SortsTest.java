import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

public class SortsTest {

    private static final int ARRAY_SIZE = 10;
    private static final int LAST_TWO = 2;

    @Test
    public void testInsertionSort() {
        ArrayList<Pair> expectedArray = new ArrayList<>();
        ArrayList<Pair> sortedArray = new ArrayList<>();
        ArrayList<Pair> reversedArray = new ArrayList<>();
        ArrayList<Pair> mostlySorted = new ArrayList<>();
        ArrayList<Pair> randomArray = new ArrayList<>();
        Random rand = new Random();
        for(int i = 1; i <= ARRAY_SIZE; i++){
            sortedArray.add(new Pair("sorted", i));
            expectedArray.add(new Pair("expected", i));
        }
        for(int i = 0; i < ARRAY_SIZE; i++){
            reversedArray.add(new Pair("reverse", ARRAY_SIZE-i));
        }
        for(int i = 1; i <= ARRAY_SIZE - LAST_TWO; i++){
            mostlySorted.add(new Pair("mostly", i));
        }
        mostlySorted.add(new Pair("mostly", ARRAY_SIZE));
        mostlySorted.add(new Pair("mostly", ARRAY_SIZE-1));
        for(int i = 1; i <= ARRAY_SIZE; i++){
            randomArray.add(new Pair("random", rand.nextInt(11) + 1));
        }
        Sorts.insertionSort(sortedArray, 0, sortedArray.size());
        Sorts.insertionSort(reversedArray, 0, reversedArray.size());
        Sorts.insertionSort(mostlySorted, 0, mostlySorted.size());
        Sorts.insertionSort(randomArray, 0, randomArray.size());
        boolean sortedCorrect = true;
        boolean reversedCorrect = true;
        boolean mostlyCorrect = true;
        boolean randomCorrect = true;
        for(int i = 0; i < expectedArray.size(); i++) {
            if(expectedArray)
        }
    }

    @Test
    public void testMergeSort() {
    }

    @Test
    public void testQuickSort() {
    }
}