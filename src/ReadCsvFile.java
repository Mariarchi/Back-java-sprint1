import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReadCsvFile {
    public static String readFileContentsOrNull(String path) { // было private String readFileContentsOrNull(String path)
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }

    public static MonthReport splitMonthLine(String fileContents) {
        MonthReport monthReportRecord = new MonthReport();
        String[] lines = fileContents.split("\\n");

        for (int i = 1; i < lines.length; i++) { // 1-я строка не считывается
            String line = lines[i];
            String[] lineContents = line.split(",");

            monthReportRecord.itemName.add(lineContents[0]);
            monthReportRecord.isExpense.add(Boolean.parseBoolean(lineContents[1]));
            monthReportRecord.quantity.add(Integer.parseInt(lineContents[2]));
            monthReportRecord.sumOfOne.add(Integer.parseInt(lineContents[3]));

        }

        return monthReportRecord;
    }

    public static YearReport splitYearLine(String fileContents) {
        YearReport yearReportRecord = new YearReport();
        String[] lines = fileContents.split("\\n");

        for (int i = 1; i < lines.length; i++) { // 1-я строка не считывается
            String line = lines[i];
            String[] lineContents = line.split(",");

            yearReportRecord.month.add(lineContents[0]);
            yearReportRecord.amount.add(Integer.parseInt(lineContents[1]));
            yearReportRecord.isExpense.add(Boolean.parseBoolean(lineContents[2]));

        }

        return yearReportRecord;
    }
}
