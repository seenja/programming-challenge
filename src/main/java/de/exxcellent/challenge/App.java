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

    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {

        // Your preparation code …

        String dayWithSmallestTempSpread = "Someday";     // Your day analysis function call …
        System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);

        String teamWithSmallestGoalSpread = "A good team"; // Your goal analysis function call …
        System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);

        // read data; call readCSVFile()
        readFile("weather.csv");

        // find minDataSpread; call findMinDataSpread() 
    }
}
