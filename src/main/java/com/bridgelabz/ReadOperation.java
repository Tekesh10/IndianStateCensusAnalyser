package com.bridgelabz;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
public class ReadOperation {
    public int readDataCount(String filePathRead, String fileNameUser) throws IndianStateCensusAnalyserException {
        final AtomicBoolean firstLine = new AtomicBoolean(true);
        final AtomicInteger count = new AtomicInteger();
        try {
            File file = new File(filePathRead);
            String fileName = file.getName();
            String fileNameWithoutExtension = " ";
            int pos = fileName.lastIndexOf(".");
            if (pos > 0 && pos < (fileName.length() - 1)) {
                fileNameWithoutExtension = fileName.substring(0, pos);
            }
            if (!fileNameWithoutExtension.equals(fileNameUser)) {
                throw new IndianStateCensusAnalyserException("Please enter a proper file name!",
                        IndianStateCensusAnalyserException.Message.IMPROPER_FILE_NAME);
            }
            if (!fileName.contains(".csv"))
                throw new IndianStateCensusAnalyserException("Please enter a proper file type!",
                        IndianStateCensusAnalyserException.Message.IMPROPER_FILE_TYPE);
            Files.lines(Paths.get(filePathRead)).forEach(lines -> {
                if (lines.startsWith("State"))
                    firstLine.set(false);
                else {
                    lines.split("\n");
                    count.getAndIncrement();
                }
            });
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return Integer.parseInt(String.valueOf(count));
    }
    public boolean readDelimiter(String filePath, String delimiter) throws IOException {
        Scanner input = new Scanner(Paths.get(filePath));
        boolean flag = true;
        try {
            input.useDelimiter(",");
            Pattern result = input.delimiter();
            if (result.pattern().equals(delimiter))
                flag = true;
            else {
                flag = false;
                throw new IndianStateCensusAnalyserException("Please enter a correct delimiter!",
                        IndianStateCensusAnalyserException.Message.IMPROPER_DELIMITER);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return flag;
    }
    public boolean readHeader(String filePath, List<String> stringName)
            throws IOException, IndianStateCensusAnalyserException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        String line = bufferedReader.readLine();
        String[] header = line.split(",");
        boolean flag = true;
        for (int i = 0; i < header.length && i < stringName.size(); i++) {
            if (stringName.get(i).equals(header[i]))
                flag = true;
            else {
                throw new IndianStateCensusAnalyserException("Please enter the correct header!",
                        IndianStateCensusAnalyserException.Message.IMPROPER_HEADER);
            }
        }
        return flag;
    }
}