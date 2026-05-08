import java.util.Arrays;
import java.util.Scanner;

//TEST MAIN FILE PLEASE DISREGARD
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter season: ");
        String season = input.nextLine();

        System.out.print("Enter year: ");
        String year = input.nextLine();

        int columnIndex = ReadCSV.findYear(season, year);

        if (columnIndex == -1) {
            System.out.println("Could not find column for " + season + " " + year);
        } else {
            System.out.println("Found " + season + " " + year + " at column index: " + columnIndex);

            String[] yearData = ReadCSV.getYearData(season, year);

            System.out.println("Data for " + season + " " + year + ":");
            System.out.println(Arrays.toString(yearData));
        }

        input.close();
    }
}