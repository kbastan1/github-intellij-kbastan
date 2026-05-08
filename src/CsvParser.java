/**
 * CsvParser.java
 * CS 170 Team Project
 * Team: kbastan
 *
 * STUB – owned by [teammate name].
 * Replace this file with the real CSV parsing implementation.
 * Contract: parseSemesters() must return one SemesterRecord per semester
 * in the order they appear in the CSV file.
 */

import java.util.List;
import java.util.ArrayList;

public class CsvParser {

    /**
     * Reads the DataMart CSV exported from:
     *   https://datamart.cccco.edu/Outcomes/Grades_Distribution_Summary.aspx
     * (Ohlone College, Select All terms, CSV export)
     *
     * Returns a list of SemesterRecord – one per semester column in the CSV.
     * Returns an empty list (never null) if the file cannot be read.
     *
     * @param filePath path to the downloaded .csv file
     */
    public static List<GradeReader.SemesterRecord> parseSemesters(String filePath) {
        // TODO: teammate implements CSV parsing here.
        // Remove the placeholder return below once real parsing is in place.
        List<GradeReader.SemesterRecord> placeholder = new ArrayList<>();
        placeholder.add(new GradeReader.SemesterRecord("PLACEHOLDER – replace with real parsing", 0.0, 0.0));
        return placeholder;
    }
}
