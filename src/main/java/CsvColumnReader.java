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

    /**
    *Method: Read Column
    *
    *Specifically designed to use with GradeSumm.csv, or other csvs like it
    *given an input of the desired semester + year (e.g. "Fall 2020") aswell as the filename, 
    *return that entire column as an Arraylist.
    *
    *Input: "GradeSumm.csv","Fall 2020"
    *Output: [Credit Grade Count (%), 100.00%, 0.13%, 46.33%, 21.25%, 9.89%, 2.81%, 6.44%, 0.00%, 2.10%, 10.71%, 0.34%]
    *
    **/
    public static List<String> readColumn(String fileName, String columnName) {
        List<String> values = new ArrayList<>();

        InputStream inputStream = CsvColumnReader.class.getResourceAsStream("/" + fileName);

        if (inputStream == null) {
            throw new RuntimeException("Could not find file: " + fileName);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                //Creating a commons CSV reader that reads the file from the bufferedreader
                CSVParser parser = CSVFormat.DEFAULT.builder()
                        .setHeader()//the first line is a header file
                        .setSkipHeaderRecord(true)//do not include the headerf ile as a data record
                        .setAllowMissingColumnNames(true)//allow for blank headers
                        .setTrim(true)//trim the extra space if there is any around values
                        .build()//build the csvformat object
                        .parse(reader)){//now parse it 
            List<String> headers = parser.getHeaderNames();
            List<CSVRecord> records = parser.getRecords();


            //looping through every column index
            for (int colIdx = 0; colIdx < headers.size(); colIdx++) {
                if (headers.get(colIdx).equals(columnName)) {
                    //we are checking the first data row after the header to make sure its the correct column
                    //some columns have the right header name but the wrong data
                    String colCheck = records.get(0).get(colIdx).trim();
                    if (colCheck.equals("Credit Grade Count (%)")) {
                        //loop through every row in the desired column then add it
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

        //if this exception is reached, there was an error regarding the columns or rows probably
        throw new RuntimeException("big fail idk");
    }
}
