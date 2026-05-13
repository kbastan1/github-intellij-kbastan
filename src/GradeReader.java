
/**
 * GradeReader.java
 * CS 170 Team Project
 * Team: JavJuice
 * Members: Saif Kharadi, K Bastan
 *
 * This program reads grade data from Ohlone College CSV file and prints out the
 * percentage of A's and F's for each semester
 *
 * CSV parsing is handled by CsvColumnReader.java (Estus's part)
 */

import java.util.List;

public class GradeReader {

    // all seasons and years in the csv
    static String[] seasons = { "Fall", "Spring", "Summer" };
    static String[] years = {
            "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000",
            "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009",
            "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018",
            "2019", "2020", "2021", "2022", "2023", "2024", "2025"
    };

    public static void main(String[] args) {

        String csvFile = "GradesSumm.csv";

        System.out.println("=======================================================");
        System.out.println("  Ohlone College - Grade Distribution by Semester");
        System.out.println("=======================================================\n");

        int count = 0;

        for (String season : seasons) {
            for (String year : years) {

                String semesterName = season + " " + year;

                try {
                    // call Estus's code to get the column data for this semester
                    List<String> data = CsvColumnReader.readColumn(csvFile, semesterName);

                    // based on his output format:
                    // index 0 = header, index 1 = total, index 2 = excused withdrawal
                    // index 3 = Grade A, index 7 = Grade F
                    if (data == null || data.size() < 8) {
                        continue;
                    }

                    double percentA = parsePercent(data.get(3));
                    double percentF = parsePercent(data.get(7));

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

    // prints the semester name and grade percentages
    private static void printSemester(String semester, double a, double f) {
        System.out.printf("Semester : %s%n", semester);
        System.out.printf("  Grade A : %5.2f%%%n", a);
        System.out.printf("  Grade F : %5.2f%%%n", f);
        System.out.println("-------------------------------------------------------");
    }

    // strips % sign and converts to double
    static double parsePercent(String value) {
        if (value == null || value.trim().isEmpty()) {
            return 0.0;
        }
        try {
            return Double.parseDouble(value.replace("%", "").trim());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}
