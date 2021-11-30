package de.exxcellent.challenge;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {

    public static List<String[]> readFile(String fileName) {
        // readFile(String fileName, String separator)
        String path = "src/main/resources/de/exxcellent/challenge/" + fileName;
        String nextLine = "";
        String delimiter = ",";

        List<String[]> fileContent = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader(path);

            BufferedReader reader = new BufferedReader(fileReader);
            // check if file is empty
            if((nextLine = reader.readLine()) == null) {
                System.out.println("File is empty!");
                reader.close();
                return null;
            } else do {
                String[] elements = nextLine.split(delimiter);
                fileContent.add(elements);
            } while((nextLine = reader.readLine()) != null);
            
            reader.close();
            return fileContent;
        } catch (IOException e) {
            // e.printStackTrace();
            System.out.println("File does not exist!");
            return null;
        }
    }

    // create HashMap to find the columns of the needed Values quick and easy
    public static HashMap<String, Integer> hashInputHeadline(String[] inputData) {
        HashMap<String, Integer> headlines = new HashMap<String, Integer>();
        for (int i = 0; i<inputData.length; i++) {
            headlines.put(inputData[i], i);
        }
        return headlines;
    }

    // put columns needed for calculation in Integer array
    public static int[][] parseToIntArray(List<String[]> inputData, int minDataCol, int maxDataCol) {
        int[][] data = new int[inputData.size()][2];
        int[] headlines = {minDataCol, maxDataCol};

        for(int i = 0; i<inputData.size() - 1; i++) {
            for(int j = 0; j<headlines.length; j++) { 
                // input data without first line
                data[i][j] = Integer.parseInt(inputData.get(i+1)[headlines[j]]);
            }
        }
        return data;
    }

    public static String findMinDataSpread(List<String[]> inputData, String identifier, String minData, String maxData) {
        int minDataSpread = 1000;
        int tempMinDataSpread = 0;
        String identifierOfMinDataSpread = "";

        // check if inputData is null
        if (inputData == null) return null;

        // put headline of file into hashmap to find entries quicker and easier
        HashMap<String, Integer> identifierHeadlines = hashInputHeadline(inputData.get(0));
        int maxDataCol = identifierHeadlines.get(maxData);
        int minDataCol = identifierHeadlines.get(minData);
        int identifierCol = identifierHeadlines.get(identifier);

        // parse input to Array of int for a better workflow
        int[][] dataArray = parseToIntArray(inputData, minDataCol, maxDataCol);

        // calculate minSpread
        for(int i = 0; i<inputData.size()-1; i++) {
            tempMinDataSpread = Math.abs(dataArray[i][1] - dataArray[i][0]);
            if (tempMinDataSpread < minDataSpread) {
                minDataSpread = tempMinDataSpread;
                identifierOfMinDataSpread = String.valueOf(inputData.get(i+1)[identifierCol]);
            }
        }
        return identifierOfMinDataSpread;
    }

    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {

        // Your preparation code …
        List<String[]> myWeatherData = readFile("weather.csv");
        List<String[]> myFootballData = readFile("football.csv");

        String dayWithSmallestTempSpread = findMinDataSpread(myWeatherData, "Day", "MnT", "MxT");     // Your day analysis function call …
        System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);

        String teamWithSmallestGoalSpread = findMinDataSpread(myFootballData, "Team", "Goals", "Goals Allowed"); // Your goal analysis function call …
        System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);
    }
}
