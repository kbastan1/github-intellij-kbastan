import java.io.*;
import java.nio.*;

public class ReadCSV {
    public static int findYear(String year){
        try (Scanner scanner = new Scanner(new File("GradesSumm.csv"))){

            if (scanner.hasNextLine()) {
            String headerLine = scanner.nextLine();
            String[] headers = headerLine.split(",");

            for (int i = 0; i < headers.length; i++) {
                if (headers[i].equals(year)) {
                    return i;
                }
            }
            }
        }catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
               }
        }//findYear

    //takes a year as an input,
    //returns an array of all the data for the given year column
    public static string[] getYearData(String year) {
        int columnIndex = findYear(year);
        if (columnIndex == -1)
        {
            return new String[0];
        }
        String data = new String[50];
        int count = 0;
        try (Scanner scanner = new Scanner(new File("GradesSumm.csv"))){
            scanner.nextLine();
            while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] values = line.split(",");
             data[count] = values[columnIndex];
             count++;
            }
        }catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
            }
        String output[] = new String[count];
        //cleaning
        for (int i = 0; i < count; i++)
        {
            output[i] = data[i];
        }
        return output;
        }








    }//ReadCSV


