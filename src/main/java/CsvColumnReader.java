import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CsvColumnReader {

    public static List<String> readColumn(String fileName, String columnName) {
        List<String> values = new ArrayList<>();

        InputStream inputStream = CsvColumnReader.class.getResourceAsStream("/" + fileName);

        if (inputStream == null) {
            throw new RuntimeException("Could not find file: " + fileName);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

                CSVParser parser = CSVFormat.DEFAULT.builder()
                        .setHeader()
                        .setSkipHeaderRecord(true)
                        .setAllowMissingColumnNames(true)
                        .setTrim(true)
                        .build()
                        .parse(reader)) {
            List<String> headers = parser.getHeaderNames();
            List<CSVRecord> records = parser.getRecords();
            for (int colIdx = 0; colIdx < headers.size(); colIdx++) {
                if (headers.get(colIdx).equals(columnName)) {
                    //we are checking the third row to make sure its a valid column
                    String colCheck = records.get(0).get(colIdx).trim();
                    if (colCheck.equals("Credit Grade Count (%)")) {
                        for (CSVRecord record : records) {
                            values.add(record.get(colIdx));
                        }
                        return values;
                    }
                }
            }
        }catch (Exception e) {
            throw new RuntimeException("CsvColumnReader.java: Error reading CSV",e);
        }
        throw new RuntimeException("big fail idk");
    }
}
