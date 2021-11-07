package com.example.covidapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class CSVFile {
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private final static String DEFAULT_DELIMITER = ",";

    private InputStream inputStream;
    private Charset charset;
    private String delimiter;
    private boolean ignoreHead;

    public CSVFile(InputStream inputStream) {
        this.inputStream = inputStream;
        this.charset = UTF8;
        this.delimiter = DEFAULT_DELIMITER;
        this.ignoreHead = false;
    }

    public CSVFile setCharset(Charset charset) {
        this.charset = charset;
        return this;
    }

    public CSVFile setDelimiter(String delimiter) {
        this.delimiter = delimiter;
        return this;
    }

    public CSVFile ignoreHead() {
        ignoreHead = true;
        return this;
    }

    public List<String[]> read() {
        boolean firstRow = true;
        List<String[]> resultList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charset));
        try {
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                if (firstRow) {
                    firstRow = false;

                    if (ignoreHead) {
                        continue;
                    }
                }

                String[] row = csvLine.split(delimiter);
                resultList.add(row);
            }
        } catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file: " + ex);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException("Error while closing input stream: " + e);
            }
        }
        return resultList;
    }
}