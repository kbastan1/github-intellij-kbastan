import java.io.*;
import java.nio.*;

//helper function for getYearData,
//season values: Fall, Spring, Summer
public class ReadCSV {
    public static int findYear(String season,String year){
        try (Scanner scanner = new Scanner(new File("GradesSumm.csv"))){

            if (scanner.hasNextLine()) {
            String headerLine = scanner.nextLine();
            String[] headers = headerLine.split(",");

            for (int i = 0; i < headers.length; i++) {
                if (headers[i].equals(season + " " + year)) {
                    return i;
                }
            }
            }
        }catch (FileNotFoundException e) {
            System.out.println("ReadCSV: File not found");
               }
        }//findYear

    //takes a year as an input,
    //returns an array of all the data for the given year column
    //season values: Fall, Spring, Summer
    public static String[] getYearData(String season, String year) {
        int columnIndex = findYear(season,year);
        if (columnIndex == -1)
        {
            return new String[0];
        }
        String[] data = new String[50];
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
            System.out.println("getYearData: File not found");
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


