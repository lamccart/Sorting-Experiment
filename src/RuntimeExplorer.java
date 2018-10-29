/*
 * NAME: Liam McCarthy
 * PID: A14029718
 */

import java.io.File;
import java.util.ArrayList;
import java.util.*;
import java.io.IOException;

/**
 * This class will explore the runtime of the method getNameCounts, and compare the performance
 * of LinkedList with MRUList. The method getNamesCounts returns an ArrayList of Pair, which
 * contains names with count of each name that appears in Pride And Prejudice.
 *
 * This class will also explore the runtime of three sorting algorithm: Insertion Sort, Merge
 * Sort and Quick Sort, by comparing the time it takes to sort the list of Pair returned 
 * by getNamesCounts
 *
 */
public class RuntimeExplorer {

    // TODO: You will need many constant variables declared here, always specify their meanings
    //Constant numbers used for testing
    static int testStartSize = 5000;
    static int testTimes = 3;
    static int smallTestTimes = 2;

    //Constants for testing sort
    static int smallestNumPairs = 1000;
    static int smallNumPairs = 2000;
    static int midNumPairs = 3000;
    static int largeNumPairs = 4000;
    static int largestNumPairs = 5000;

    // Then other non-constant variables, specify their meaning when necessary
    static String prideAndPrejudice = "./src/PrideAndPrejudice.txt";

    static String smallNames = "./src/SmallNames.txt";
    static String mediumNames = "./src/MediumNames.txt";
    static String largeNames = "./src/LargeNames.txt";

    /**
     * The main method that drives the RuntimeExplorer. 
     * Requirement: 
     * 1. To help you establish good coding habits, in this assignment, you should leave 
     * all the implementaion part in your other methods, as this will keep your main method 
     * short and clear. You will lose style points if this requirement is not met.
     * 2. Avoid magic number.
     *
     * @param args the command line arguments
     */
    public static void main(String [] args) {

        printRunTime("LinkedList", smallNames, testStartSize, testStartSize, testTimes, smallTestTimes);
        printRunTime("MRUList", smallNames, testStartSize, testStartSize, testTimes, smallTestTimes);
        printRunTime("LinkedList", mediumNames, testStartSize, testStartSize, testTimes, smallTestTimes);
        printRunTime("MRUList", mediumNames, testStartSize, testStartSize, testTimes, smallTestTimes);
        printRunTime("LinkedList", largeNames, testStartSize, testStartSize, testTimes, smallTestTimes);
        printRunTime("MRUList", largeNames, testStartSize, testStartSize, testTimes, smallTestTimes);

        //Getting all the names from largeNames and words from Pride and Prejudice
        AbstractList<String> allLargeNames = readNames(largeNames, "LinkedList");
        ArrayList<String> allWords = readWords(prideAndPrejudice, 0, true);
        ArrayList<Pair> namesCount = getNameCounts(allLargeNames, allWords);
        //Printing tests for insertion sort
        printSortsTime(deepCopyArrayList(namesCount), "InsertionSort", smallestNumPairs, testTimes);
        printSortsTime(deepCopyArrayList(namesCount), "InsertionSort", smallNumPairs, testTimes);
        printSortsTime(deepCopyArrayList(namesCount), "InsertionSort", midNumPairs, testTimes);
        printSortsTime(deepCopyArrayList(namesCount), "InsertionSort", largeNumPairs, testTimes);
        printSortsTime(deepCopyArrayList(namesCount), "InsertionSort", largestNumPairs, testTimes);
        //Printing tests for merge sort
        printSortsTime(deepCopyArrayList(namesCount), "MergeSort", smallestNumPairs, testTimes);
        printSortsTime(deepCopyArrayList(namesCount), "MergeSort", smallNumPairs, testTimes);
        printSortsTime(deepCopyArrayList(namesCount), "MergeSort", midNumPairs, testTimes);
        printSortsTime(deepCopyArrayList(namesCount), "MergeSort", largeNumPairs, testTimes);
        printSortsTime(deepCopyArrayList(namesCount), "MergeSort", largestNumPairs, testTimes);
        //Printing tests for quick sort
        printSortsTime(deepCopyArrayList(namesCount), "QuickSort", smallestNumPairs, testTimes);
        printSortsTime(deepCopyArrayList(namesCount), "QuickSort", smallNumPairs, testTimes);
        printSortsTime(deepCopyArrayList(namesCount), "QuickSort", midNumPairs, testTimes);
        printSortsTime(deepCopyArrayList(namesCount), "QuickSort", largeNumPairs, testTimes);
        printSortsTime(deepCopyArrayList(namesCount), "QuickSort", largestNumPairs, testTimes);

        //Finding the three main characters
        AbstractList<String> allSmallNames = readNames(smallNames, "LinkedList");
        ArrayList<Pair> allNamesCount = getNameCounts(allSmallNames, allWords);
        Sorts.insertionSort(allNamesCount, 0, allNamesCount.size());
        printCharacterQuestion(allNamesCount);

    }

    /**
     * This method will read the names from the given file and store them into an LinkedList or
     * MRUList or ArrayList based on the given String listType
     *
     * @param fileName the given file to be read
     * @param listType "LinkedList" or "MRUList" or "ArrayList" which will be used in getNameCounts()
     * @return a LinkedList or MRUList or ArrayList containing all the names from the given file
     */
    public static AbstractList<String> readNames(String fileName, String listType){

        //Check to see which list type to use
        AbstractList<String> allNames;
        if(listType.equals("LinkedList")){
            allNames = new LinkedList<>();
        }else if(listType.equals("MRUList")){
            allNames = new MRUList<>();
        }else{
            allNames = new ArrayList<>();
        }
        //Scan through the given file
        try {
            Scanner sc = new Scanner(new File(fileName));
            //Checks if file has next line
            while (sc.hasNextLine()) {
                String newLine = sc.nextLine();
                //Split line into words
                String[] splitLine = newLine.split("\\s+");
                //Add all the words to allNames
                Collections.addAll(allNames, splitLine);
            }
            sc.close();
        }
        //If file isn't found throw exception
        catch (IOException e) { // If the given file doesn’t exist
            System.out.println("File not found!");
        }

        return allNames;
    }

    /**
     * This method will read certain number of words from the given file (Pride and Prejudice)
     * and store them into an ArrayList. 
     *
     * @param fileName the given file to be read
     * @param numWords the number of words to read from given file
     * @param readAll if true, read all words from given file. Otherwise, only read numWords
     * @return an ArrayList containing all the words from the given file
     */
    public static ArrayList<String> readWords(String fileName, int numWords, boolean readAll){

        //Read the given file
        ArrayList<String> allNames = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(fileName));
            //Check if we have to read the file
            if(readAll){
                while (sc.hasNextLine()) {
                    String newLine = sc.nextLine();
                    //Read split the line into words
                    String[] splitLine = newLine.replaceAll("\\p{P}", "").split("\\s+");
                    //Add the words to allNames
                    Collections.addAll(allNames, splitLine);
                }
            }else{
                int counter = numWords;
                //Check if we need more words
                while (counter > 0) {
                    String newLine = sc.nextLine();
                    //Split line into words
                    String[] splitLine = newLine.replaceAll("\\p{P}", "").split("\\s+");
                    //Add the words to allNames if we still need them
                    for (String word : splitLine) {
                        if (counter > 0) {
                            allNames.add(word);
                            counter--;
                        } else {
                            break;
                        }
                    }
                }
            }

            sc.close();
        }
        //If the file doesn't exist throw exception
        catch (IOException e) { // If the given file doesn’t exist
            System.out.println("File not found!");
        }

        return allNames;
    }

    /**
     * This method would return an ArrayList of pair. Each pair will contain a name from names 
     * list with its number of occurrence in the ArrayList words. You must implement this method 
     * as efficient as possible to save time later. Hint: consider using break or iterators 
     * to make it run faster
     *
     * @param names the given names to find number of occurrence in words
     * @param words the given words from Pride and Prejudice
     * @return an ArrayList of Pair, which contains names with count of each name
     */
    public static ArrayList<Pair> getNameCounts(AbstractList<String> names,
                                                ArrayList<String> words) {
        ArrayList<Pair> nameCounts = new ArrayList<>();
        ArrayList<String> usedNames = new ArrayList<>();
        //For every name check if it's used
        for(String name : names){
            if(!usedNames.contains(name)){
                //Make a pair for the name and the number of times it occurs
                Pair newPair = new Pair(name, Collections.frequency(words, name));
                //Update lists with new pair
                nameCounts.add(newPair);
                usedNames.add(name);
            }
        }

        return nameCounts;
    }

    /**
     * Print run time of getNameCounts with different parameters. The only method you should time
     * is getNamesCounts(). (See details below)
     *
     * @param listType "LinkedList" or "MRUList" or "ArrayList" which will be used in getNameCounts()
     * @param fileName the input file which contains names
     * @param startSize the initial size of words from Pride and Prejudice
     * @param incSize the increased size for each time we run the test
     * @param numTest the total number of tests we need to run
     * @param eachTestTimes the number of times we run each test to take average runtime
     */
    public static void printRunTime(String listType, String fileName,
                                    int startSize, int incSize, int numTest, int eachTestTimes) {

        System.out.println("Class: " + listType + " (" + fileName + ")");
        System.out.println("=================================");

        //Read names from name file and words from Pride and Prejudice
        AbstractList<String> namesList = readNames(fileName, listType);
        ArrayList<String> wordsList = readWords(prideAndPrejudice, startSize, false);
        int currentSize = startSize;

        //Runs through number of tests
        for(int i = 1; i <= numTest; i++){
            //Does a certain number of trials and takes the average run time
            long[] testTimes = new long[eachTestTimes];
            for(int j = 0; j < eachTestTimes; j++){
                long startTime = System.currentTimeMillis();

                ArrayList<Pair> nameCounts = new ArrayList<>();
                nameCounts = getNameCounts(namesList, wordsList);

                long endTime = System.currentTimeMillis();
                long totalTime = startTime - endTime;
                //Add the trials time to the list
                testTimes[j] = totalTime;
            }
            //Find the average of the tests
            long sum = 0;
            for(long time: testTimes){
                sum += time;
            }
            long avgTime = sum / eachTestTimes;
            //Print results of test
            System.out.println(i + ":    " + currentSize + " words in    " + avgTime + " milliseconds");
            //Increase number of words to be read next test
            currentSize = currentSize + incSize;
            //Read new number of words
            wordsList = readWords(prideAndPrejudice, currentSize, false);
        }
        // print new line after each test
        System.out.print("\n");


    }

    /**
     * Print the time it takes to sort nameCounts using Insertion Sort, Merge Sort or Quick Sort,
     * based on the given string sortAlg. 
     *
     * @param namesCount the given ArrayList to sort
     * @param sortAlg if equals "QuickSort", use Quick Sort. If equals "MergeSort", use Merge Sort.
     *                If equals "InsertionSort", use Insertion Sort.
     * @oaram numPairs the number of pairs to sort
     * @param testTimes the number of times we run each test to take average runtime
     */
    public static void printSortsTime(ArrayList<Pair> namesCount, String sortAlg, 
                                                 int numPairs, int testTimes) {
        
        long totalTime = 0;


        System.out.println("Sorting nameCounts using " + sortAlg);

        long[] tests = new long[testTimes];

        //Check which sorting algorithm to use
        if(sortAlg.equals("InsertionSort")){
            //Loops for the number of tests
            for(int i = 0; i < testTimes; i++){
                //Checks how long sort of the name counts takes
                long startTime = System.nanoTime();

                Sorts.insertionSort(namesCount, 0, numPairs);

                long endTime = System.nanoTime();
                totalTime = endTime - startTime;
                //Added to list of trials
                tests[i] = totalTime;
            }
        }else if(sortAlg.equals("MergeSort")){
            //Loops through number of trials
            for(int i = 0; i < testTimes; i++){
                //Check how long sort take
                long startTime = System.nanoTime();

                Sorts.mergeSort(namesCount, 0, numPairs);

                long endTime = System.nanoTime();
                totalTime = endTime - startTime;
                //Added to list of trials
                tests[i] = totalTime;
            }
        }else{
            //Loops for number of trials
            for(int i = 0; i < testTimes; i++){
                //Sees how long the sort takes
                long startTime = System.nanoTime();

                Sorts.quickSort(namesCount, 0, numPairs);

                long endTime = System.nanoTime();
                totalTime = endTime - startTime;
                //Add it to the list of trials
                tests[i] = totalTime;
            }
        }
        //Average the results of each trial
        long sum = 0;
        for(long time: tests){
            sum += time;
        }
        long avgTime = sum / testTimes;

        System.out.println(sortAlg + " takes " + avgTime + " nanoseconds to sort " + numPairs +
                           " pairs in nameCounts\n");
    }

    /**
     * Returns a deep copy of given ArrayList
     *
     * @param old the given old ArrayList
     */
    private static ArrayList<Pair> deepCopyArrayList(ArrayList<Pair> old) {
        ArrayList<Pair> copy = new ArrayList<Pair>(old.size());
        for (Pair i : old){
            copy.add(new Pair(i.getName(), i.getCount()));
        }
        return copy;
    }

    /**
     * Print the main character, the second main character and the third main character
     * in Pride and Prejudice
     *
     * @param sorted a sorted LinkedList of Pair, which contains names with count of each name.
     */
    public static void printCharacterQuestion(ArrayList<Pair> sorted) {
        String mainChar = sorted.get(0).getName();
        String secondChar = sorted.get(1).getName();
        String thirdChar = sorted.get(2).getName();

        System.out.println("In Pride and Prejudice: ");
        System.out.println("The main character is " + mainChar);
        System.out.println("The second main character is " + secondChar);
        System.out.println("The third main character is " + thirdChar);
    }
}
