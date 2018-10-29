
import java.io.File;
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

        printRunTime("LinkedList", smallNames, testStartSize, testStartSize, testTimes, testTimes);
        printRunTime("MRUList", smallNames, testStartSize, testStartSize, testTimes, testTimes);
        printRunTime("LinkedList", mediumNames, testStartSize, testStartSize, testTimes, testTimes);
        printRunTime("MRUList", mediumNames, testStartSize, testStartSize, testTimes, testTimes);
        printRunTime("LinkedList", largeNames, testStartSize, testStartSize, testTimes, testTimes);
        printRunTime("MRUList", largeNames, testStartSize, testStartSize, testTimes, testTimes);

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

        AbstractList<String> allNames;
        if(listType.equals("LinkedList")){
            allNames = new LinkedList<>();
        }else if(listType.equals("MRUList")){
            allNames = new MRUList<>();
        }else{
            allNames = new ArrayList<>();
        }
        try {
            Scanner sc = new Scanner(new File(fileName));
            while (sc.hasNextLine()) {
                String newLine = sc.nextLine();
                String[] splitLine = newLine.split("\\s+");
                Collections.addAll(allNames, splitLine);
            }
            sc.close();
        }
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

        ArrayList<String> allNames = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(fileName));
            if(readAll){
                while (sc.hasNextLine()) {
                    String newLine = sc.nextLine();
                    String[] splitLine = newLine.replaceAll("\\p{P}", "").split("\\s+");
                    Collections.addAll(allNames, splitLine);
                }
            }else{
                int counter = numWords;
                while (counter > 0) {
                    String newLine = sc.nextLine();
                    String[] splitLine = newLine.replaceAll("\\p{P}", "").split("\\s+");
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
        for(String name : names){
            if(!usedNames.contains(name)){
                Pair newPair = new Pair(name, Collections.frequency(words, name));
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

        AbstractList<String> namesList = readNames(fileName, listType);
        ArrayList<String> wordsList = readWords(prideAndPrejudice, startSize, false);
        int currentSize = startSize;

        for(int i = 1; i <= numTest; i++){
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

        // Example of how to time your program in nanoseconds

        long startTime = System.nanoTime();

        // TODO: The program that you time goes here

        long endTime = System.nanoTime();
        totalTime = endTime - startTime;

        System.out.println(sortAlg + " takes " + totalTime + " nanoseconds to sort " + numPairs +
                           " pairs in nameCounts\n");
    }

    /**
     * Returns a deep copy of given ArrayList
     *
     * @param old the given old ArrayList
     */
    private ArrayList<Pair> deepCopyArrayList(ArrayList<Pair> old) {
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
        // TODO: change the variables below to proper value
        String mainChar = "";
        String secondChar = "";
        String thirdChar = "";

        System.out.println("In Pride and Prejudice: ");
        System.out.println("The main character is " + mainChar);
        System.out.println("The second main character is " + secondChar);
        System.out.println("The third main character is " + thirdChar);
    }
}
