
/**
 * GradeReader.java
 * CS 170 Team Project
 * Team: JavJuice
 * Members: Saif Kharadi, K Bastan
 *
 * Reads grade distribution data exported from the California Community Colleges
 * Chancellor's Office DataMart (Ohlone College, all terms) and prints a summary
 * for every semester showing the percentage of students who received an A or an F.
 *
 * CSV parsing is handled by CsvParser.java (see that file).
 * This file owns: program entry, output formatting, and data-analysis logic.
 */

import java.util.List;

public class GradeReader {

    public static void main(String[] args) {

        // Locate the CSV file – adjust the path if needed before running.
        String csvFilePath = "grades.csv";

        System.out.println("=======================================================");
        System.out.println("  Ohlone College – Grade Distribution by Semester");
        System.out.println("=======================================================\n");

        // Ask the CSV parser (teammate's code) for all semester records.
        List<SemesterRecord> semesters = CsvParser.parseSemesters(csvFilePath);

        if (semesters == null || semesters.isEmpty()) {
            System.out.println("No data found. Check that the CSV file path is correct.");
            return;
        }

        // Print one block per semester.
        for (SemesterRecord semester : semesters) {
            printSemesterSummary(semester);
        }

        System.out.println("=======================================================");
        System.out.printf("  Total semesters processed: %d%n", semesters.size());
        System.out.println("=======================================================");
    }

    /**
     * Formats and prints the three required pieces of data for one semester:
     * 1. Semester name
     * 2. Percentage of students who received an A
     * 3. Percentage of students who received an F
     *
     * Using printf with fixed-width fields keeps all semesters visually aligned.
     */
    private static void printSemesterSummary(SemesterRecord semester) {
        System.out.printf("Semester : %s%n", semester.getName());
        System.out.printf("  Grade A : %5.2f%%%n", semester.getPercentA());
        System.out.printf("  Grade F : %5.2f%%%n", semester.getPercentF());
        System.out.println("-------------------------------------------------------");
    }

    // -----------------------------------------------------------------------
    // SemesterRecord – a simple data container shared between this file and
    // CsvParser.java. The CSV parser fills one of these per semester; this
    // class only reads from it.
    // -----------------------------------------------------------------------

    /**
     * Holds the three values we care about for a single semester.
     * CsvParser constructs these; GradeReader reads them.
     */
    public static class SemesterRecord {

        private final String name; // e.g. "Spring 2023"
        private final double percentA; // e.g. 38.87
        private final double percentF; // e.g. 5.27

        public SemesterRecord(String name, double percentA, double percentF) {
            this.name = name;
            this.percentA = percentA;
            this.percentF = percentF;
        }

        public String getName() {
            return name;
        }

        public double getPercentA() {
            return percentA;
        }

        public double getPercentF() {
            return percentF;
        }
    }
}
