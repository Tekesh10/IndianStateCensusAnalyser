package com.bridgelabz;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IndianStateCensusAnalyserTest {
    ReadOperation readObj = new ReadOperation();
    String fileName = "IndianStateCensusData.csv";
    @Test
    public void givenStateCensusCSVFileCorrectEnsureNumberOfRecordsMatch() {
        String filePathRead = "C:\\Users\\ACE\\TerminalCommands\\IndianStateCensusAnalyser\\src\\main\\resources\\IndianStateCensusData.csv";
        try {
            int count = readObj.readDataCount(filePathRead, fileName);
            Assertions.assertEquals(29, count);
        } catch (IndianStateCensusAnalyserException indianStateCensusAnalyserException) {
            indianStateCensusAnalyserException.printStackTrace();
        }
    }
    @Test
    public void givenStateCensusCSVFileWhenFileNameIncorrectShouldThrowException() {
        String filePathRead = "./src/main/resources/IndianStateCensusData.csv";
        try {
            int count = readObj.readDataCount(filePathRead, fileName);
            Assertions.assertEquals(29, count);
        } catch (IndianStateCensusAnalyserException indianStateCensusAnalyserException) {
            indianStateCensusAnalyserException.printStackTrace();
        }
    }
    @Test
    public void givenStateCensusCSVFileWhenFileTypeIncorrectShouldThrowException() {
        String filePathRead = "./src/main/resources/IndianStateCensusData.pdf";

        try {
            int count = readObj.readDataCount(filePathRead, fileName);
            Assertions.assertEquals(29, count);
        } catch (IndianStateCensusAnalyserException indianStateCensusAnalyserException) {
            indianStateCensusAnalyserException.printStackTrace();
        }
    }
    @Test
    public void givenStateCensusCSVFileCorrectButDelimiterIncorrectShouldThrowException() {
        String filePathRead = "./src/main/resources/IndianStateCensusData.csv";
        String delimiter = ".";
        try {
            if (delimiter.equals(","))
                Assertions.assertTrue(readObj.readDelimiter(filePathRead, delimiter));
            else
                Assertions.assertFalse(readObj.readDelimiter(filePathRead, delimiter));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    @Test
    public void givenStateCensusCSVFileCorrectButHeaderIncorrectShouldThrowException() {
        List<String> stringName = new ArrayList<>();
        String filePathRead = "./src/main/resources/IndianStateCensusData.csv";
        stringName.add("State");
        stringName.add("Population");
        stringName.add("AreaInSqKm");
        stringName.add("Density");
        try {
            boolean flag = readObj.readHeader(filePathRead, stringName);
            if (flag)
                Assertions.assertTrue(flag);
            else
                Assertions.assertFalse(flag);
        } catch (IOException | IndianStateCensusAnalyserException exception) {
            exception.printStackTrace();
        }
    }
}