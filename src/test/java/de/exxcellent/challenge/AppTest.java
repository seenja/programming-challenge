package de.exxcellent.challenge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Example JUnit 5 test case.
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
class AppTest {

    private String successLabel = "not successful";

    @BeforeEach
    void setUp() {
        successLabel = "successful";
    }

    @Test
    void aPointlessTest() {
        assertEquals("successful", successLabel, "My expectations were not met");
    }

    @Test
    void runFootball() {
        App.main("--football", "football.csv");
    }

    @Test
    void readNonExistingFile() {
        assertNull(App.readFile("nonExisting.csv"));
    }

    @Test
    void readEmptyFile() {
        assertNull(App.readFile("empty.csv"));
    }

    @Test
    void readNonEmptyFile() {
        assertNotNull(App.readFile("weather.csv"));
    }

    @Test
    void findMinDataSpreadOfEmptyList() {
        assertEquals(null, App.findMinDataSpread(null, null, null, null));
    }

    @Test
    void findMinDataSpreadOfSingleLine() {
        String[] lineOne = new String[]{"Day", "MxT", "MnT"};
        String[] lineTwo = new String[]{"1", "3", "2"};
        List<String[]> inputData = new ArrayList<String[]>();
        inputData.add(lineOne);
        inputData.add(lineTwo);
        assertEquals("1", App.findMinDataSpread(inputData, "Day", "MnT", "MxT"));
    }

    @Test
    void findMinDataSpreadOfWeatherData() {
        List<String[]> inputData = App.readFile("weather.csv");
        assertEquals("14", App.findMinDataSpread(inputData, "Day", "MnT", "MxT"));
    }

    @Test
    void findMinDataSpreadOfFootballData() {
        List<String[]> inputData = App.readFile("football.csv");
        assertEquals("Aston_Villa", App.findMinDataSpread(inputData, "Team", "Goals", "Goals Allowed"));
    }

}