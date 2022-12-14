package com.bridgelabz;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class CensusAnalyser {
    public static void main(String[] args) throws IOException, IndianStateCensusAnalyserException {
        String filePathRead = "C:\\Users\\ACE\\TerminalCommands\\IndianStateCensusAnalyser\\src\\main\\resources\\IndianStateCensusData.csv";
        String fileName = "IndianStateCensusData";
        String delimiter = ",";
        List<String> stringName = new ArrayList<>();
        stringName.add("State");
        stringName.add("Population");
        stringName.add("AreaInSqKm");
        stringName.add("DensityPerSqKm");
        ReadOperation readObj = new ReadOperation();
        int count = readObj.readDataCount(filePathRead, fileName);
        System.out.println(count);
        readObj.readDelimiter(filePathRead, delimiter);
        readObj.readHeader(filePathRead, stringName);
    }
}