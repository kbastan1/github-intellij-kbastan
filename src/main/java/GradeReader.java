
/**
 * GradeReader.java
 * CS 170 Team Project
 * Team: JavaJuice
 * Members: Saif Kharadi, K Bastan
 *
 * This program reads grade data from Ohlone College CSV file and prints out the
 * percentage of A's and F's for each semester
 *
 * CSV parsing is handled by CsvColumnReader.java (K Bastan's part)
 */

import java.util.List;

public class GradeReader {

    // all seasons and years present in the csv file
    static String[] seasons = { "Fall", "Spring", "Summer" };
    static String[] years = {
            "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000",
            "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009",
            "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018",
            "2019", "2020", "2021", "2022", "2023", "2024", "2025"
    };

    /**
     * Method: main
     *
     * Entry point of the program. Loops through every season and year combination,
     * calls CsvColumnReader to get the grade data for that semester,
     * and prints out the percentage of A's and F's for each one.
     *
     * Input: GradesSumm.csv (placed in resources folder)
     * Output: Formatted semester-by-semester grade breakdown printed to console
     */
    
    public static void main(String[] args) {

        // Name of the csv file to read from
        String csvFile = "GradesSumm.csv";

        System.out.println("=======================================================");
        System.out.println("  Ohlone College - Grade Distribution by Semester");
        System.out.println("=======================================================\n");

        int count = 0;

        for (String season : seasons) {
            for (String year : years) {

                String semesterName = season + " " + year;

                try {
                    // call K Bastan's code to get the column data for this semester
                    List<String> data = CsvColumnReader.readColumn(csvFile, semesterName);

                    // based on CsvColumnReader output format:
                    // index 0 = "Credit Grade Count (%)" header
                    // index 1 = Ohlone Total (100%)
                    // index 2 = Excused Withdrawal
                    // index 3 = Grade A
                    // index 4 = Grade B
                    // index 5 = Grade C
                    // index 6 = Grade D
                    // index 7 = Grade F
                    if (data == null || data.size() < 8) {
                        continue; // skip if not enough data returned
                    }

                    double percentA = parsePercent(data.get(3));
                    double percentF = parsePercent(data.get(7));

                    // only print if we get real non-zero data
                    if (percentA > 0 || percentF > 0) {
                        printSemester(semesterName, percentA, percentF);
                        count++;
                    }

                } catch (Exception e) {
                    // semester doesnt exist in csv, just skip it
                    continue;
                }
            }
        }

        System.out.println("=======================================================");
        System.out.printf("  Total semesters processed: %d%n", count);
        System.out.println("=======================================================");
    }

    /**
     * Method: printSemester
     *
     * Formats and prints the three required pieces of data for one semester
     *
     * Input: semester name (e.g. "Fall 2020"), percentA (e.g. 46.33), percentF (e.g. 6.44)
     * Output: formatted block printed to console
     */    
    private static void printSemester(String semester, double a, double f) {
        System.out.printf("Semester : %s%n", semester);
        System.out.printf("  Grade A : %5.2f%%%n", a);
        System.out.printf("  Grade F : %5.2f%%%n", f);
        System.out.println("-------------------------------------------------------");
    }

    /**
     * Method: parsePercent
     *
     * Strips the % sign from a string and converts it to a double
     *
     * Input: "46.33%"
     * Output: 46.33
     */   
    static double parsePercent(String value) {
        if (value == null || value.trim().isEmpty()) {
            return 0.0;
        }
        try {
            return Double.parseDouble(value.replace("%", "").trim());
        } catch (NumberFormatException e) {
            // if parsing fails just return 0
            return 0.0;
        }
    }
}
