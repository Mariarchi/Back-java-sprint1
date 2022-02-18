import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int NUMBER_OF_MONTH_REPORT = 3;
        MonthReport[] monthReport = new MonthReport[NUMBER_OF_MONTH_REPORT];
        YearReport yearReport = new YearReport();
        // переменные должны быть в глобальной зоне видимости для реализации логики
        String fileContentsMonth = null;
        String fileContentsYear = null;

        while (true) {
            PrintMenu();
            int command = scanner.nextInt();
            switch (command) {
                case 1:
                    for (int i = 1; i <= NUMBER_OF_MONTH_REPORT; i++) {
                        fileContentsMonth = ReadCsvFile.readFileContentsOrNull("resources/" + "m.20210"
                                + i + ".csv");
                        if (fileContentsMonth != null) {
                            monthReport[i - 1] = ReadCsvFile.splitMonthLine(fileContentsMonth);
                            System.out.println("Отчёт за месяц " + GetMonth(i) + " был успешно загружен\n");
                        }
                    }
                    break;
                case 2:
                    fileContentsYear = ReadCsvFile.readFileContentsOrNull("resources/y.2021.csv");
                    if (fileContentsYear != null) {
                        yearReport = ReadCsvFile.splitYearLine(fileContentsYear);
                        System.out.println("Отчёт за год был успешно загружен\n");
                    }
                    break;
                case 3:
                    boolean isError = false;
                    if ((fileContentsMonth != null) && (fileContentsYear != null)) {
                        for (int i = 0; i < yearReport.month.size(); i++) {
                            int j; // индекс для выбора нужного месяца из массива monthReport
                            if (i < 2) { //
                                j = 0;
                            } else if (i < 4) {
                                j = 1;
                            } else {
                                j = 2;
                            }

                            if (yearReport.isExpense.get(i)) {
                                if (yearReport.amount.get(i) != MonthlyReport.SumOfExpenses(monthReport[j])) {
                                    System.out.println("Отчёт за месяц " + GetMonth(j)
                                            + " не соответствует отчёту за год!");
                                    isError = true;
                                }
                            } else {
                                if (yearReport.amount.get(i) != MonthlyReport.SumOfIncome(monthReport[j])) {
                                    System.out.println("Отчёт за месяц " + GetMonth(j)
                                            + " не соответствует отчёту за год!");
                                    isError = true;
                                }
                            }
                        }
                        if (!isError) {
                            System.out.println("Ошибок в отчёте не обнаружено!\n");
                        }
                    } else {
                        System.out.println("Отчёты не были загружены!\n");
                    }
                    break;
                case 4:
                    if (fileContentsMonth != null) {
                        for(int i = 0; i < NUMBER_OF_MONTH_REPORT; i++) {
                            System.out.println("Отчёт за месяц " + GetMonth(i) + ":");
                            int monthIndex = MonthlyReport.TheMostProfitableProduct(monthReport[i]);
                            int maxSum = monthReport[i].sumOfOne.get(monthIndex)
                                    * monthReport[i].quantity.get(monthIndex);
                            System.out.println("Самый прибыльный товар: " + monthReport[i].itemName.get(monthIndex)
                                    + " вышла на сумму: " + maxSum + " у. е.");

                            monthIndex = MonthlyReport.TheBiggestWaste(monthReport[i]);
                            maxSum = monthReport[i].sumOfOne.get(monthIndex) * monthReport[i].quantity.get(monthIndex);
                            System.out.println("Самая большая трата: " + monthReport[i].itemName.get(monthIndex)
                                    + " составила: " + maxSum + " у. е.");
                        }
                    } else {
                        System.out.println("Отчёты за месяц не были загружены!\n");
                    }
                    break;
                case 5:
                    if (fileContentsYear != null) {
                        System.out.println("Рассматриваемый год: " + 2021);
                        for (int i = 0; i < yearReport.month.size() / 2; i++) {
                            System.out.print("Прибыль за: " + GetMonth(i) + " составил(-а): ");
                            System.out.println(YearlyReport.PrintProfit(yearReport, i + 1) + " у. е.");
                        }
                        System.out.println("Средний расход за все месяцы в году составил: "
                                + YearlyReport.AverageExpensesProfit(yearReport, true) + " у. е.");
                        System.out.println("Средний доход за все месяцы в году составил: "
                                + YearlyReport.AverageExpensesProfit(yearReport, false) + " у. е.\n");
                    } else {
                        System.out.println("Отчёты за год не были загружены!\n");
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Извините, такой команды нет.");
                    break;
            }
        }
    }

    public static void PrintMenu() {
        System.out.println("Выберите нужную операцию:");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
    }

    public static String GetMonth(int numberMonth) {
        if (numberMonth == 0) {
            return "январь";
        } else if (numberMonth == 1) {
            return "февраль";
        } else {
            return "март";
        }
    }
}