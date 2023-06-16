import java.io.*;
import java.util.*;

public class InsertionSortExample {
    public static void main(String[] args) {
        String inputFile = "dataset.txt";
        String outputFile = "output.txt";
        String sortingTimesFile = "sorting_times.txt";
        int numTrials = 20;

        try {
            BufferedWriter sortingTimesWriter = new BufferedWriter(new FileWriter(sortingTimesFile));

            for (int trial = 1; trial <= numTrials; trial++) {
                System.out.println("Trial " + trial);
                // Read numbers from the input file
                List<Integer> numbers = readNumbersFromFile(inputFile);

                // Perform sorting using Insertion Sort
                long startTime = System.nanoTime(); // Start time

                insertionSort(numbers, startTime); // Call the insertionSort method with start time

                long endTime = System.nanoTime();

                // Write sorted numbers to the output file
                writeNumbersToFile(numbers, outputFile);

                // Calculate and display the sorting time
                long sortingTime = endTime - startTime;
                System.out.println("Sorting time (nanoseconds): " + sortingTime);
                String formattedSortingTime = formatTime(sortingTime);
                System.out.println("Sorting time (formatted): " + formattedSortingTime);
                System.out.println();

                // Write the sorting time to the sorting times file
                sortingTimesWriter.write(formattedSortingTime);
                sortingTimesWriter.newLine();
            }

            // Close the sorting times file writer
            sortingTimesWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Read numbers from a file and return them as a list
    private static List<Integer> readNumbersFromFile(String filename) throws IOException {
        List<Integer> numbers = new ArrayList<>();

        // Open the file for reading
        BufferedReader reader = new BufferedReader(new FileReader(filename));

        // Read each line from the file
        String line;
        while ((line = reader.readLine()) != null) {
            // Convert the line to an integer and add it to the list
            int number = Integer.parseInt(line.trim());
            numbers.add(number);
        }

        // Close the file
        reader.close();

        // Return the list of numbers
        return numbers;
    }

    // Write numbers to a file
    private static void writeNumbersToFile(List<Integer> numbers, String filename) throws IOException {
        // Open the file for writing
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

        // Write each number to a new line in the file
        for (int number : numbers) {
            writer.write(Integer.toString(number));
            writer.newLine();
        }

        // Close the file
        writer.close();
    }

    // Sort the numbers using Insertion Sort algorithm
    private static void insertionSort(List<Integer> numbers, long startTime) {
        int n = numbers.size();

        for (int i = 1; i < n; i++) {
            int key = numbers.get(i);
            int j = i - 1;

            while (j >= 0 && numbers.get(j) > key) {
                numbers.set(j + 1, numbers.get(j));
                j--;
            }

            numbers.set(j + 1, key);

            // Calculate and display the elapsed time and estimated remaining time
            long currentTime = System.nanoTime();
            long elapsedTime = currentTime - startTime;
            long estimatedRemainingTime = (elapsedTime * (n - i - 1)) / (i + 1);

            System.out.println("Iteration " + i);
            System.out.println("Elapsed time (nanoseconds): " + elapsedTime);
            System.out.println("Estimated remaining time (nanoseconds): " + estimatedRemainingTime);
            System.out.println();
        }
    }

    // Format the given time value into HH:MM:SS:ms:ns format
    private static String formatTime(long time) {
        long hours = time / (60 * 60 * 1000000000L);
        time %= (60 * 60 * 1000000000L);
        long minutes = time / (60 * 1000000000L);
        time %= (60 * 1000000000L);
        long seconds = time / 1000000000L;
        time %= 1000000000L;
        long milliseconds = time / 1000000L;
        time %= 1000000L;
        long nanoseconds = time;

        return String.format("%02d:%02d:%02d:%03d:%03d", hours, minutes, seconds, milliseconds, nanoseconds);
    }
}
