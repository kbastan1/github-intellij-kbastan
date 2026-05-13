import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> fall2020Values =
                CsvColumnReader.readColumn("GradesSumm.csv", "Fall 2020");

        System.out.println(fall2020Values);
    }
}