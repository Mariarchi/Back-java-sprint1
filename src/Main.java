import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int NUMBER_OF_MONTH_REPORT = 3;
        MonthReport[] monthReport = new MonthReport[NUMBER_OF_MONTH_REPORT];
        YearReport yearReport = new YearReport();
        String fileContentsMonth = null; // переменная должна быть в глобальной зоне видимости
        String fileContentsYear = null; // переменная должна быть в глобальной зоне видимости

        while (true) {
            PrintMenu();
            int command = scanner.nextInt();
            switch (command) {
                case 1: // считываем все месячные отчёты
                    for (int i = 1; i <= NUMBER_OF_MONTH_REPORT; i++) {
                        fileContentsMonth = ReadCsvFile.readFileContentsOrNull("resources/" + "m.20210"
                                + i + ".csv");
                        //System.out.println(fileContents);
                        if (fileContentsMonth != null) {
                            monthReport[i - 1] = ReadCsvFile.splitMonthLine(fileContentsMonth);
                            System.out.println("Отчёт за месяц " + GetMonth(i) + " был успешно загружен\n");
                        }
                    }
                    break;
                case 2: // считываем отчёт за год
                    fileContentsYear = ReadCsvFile.readFileContentsOrNull("resources/y.2021.csv");
                    //System.out.println(fileContents);
                    if (fileContentsYear != null) {
                        yearReport = ReadCsvFile.splitYearLine(fileContentsYear);
                        System.out.println("Отчёт за год был успешно загружен\n");
                    }
                    break;
                case 3: // is_expense — одно из двух значений: TRUE или FALSE. Обозначает, является ли запись тратой (TRUE) или доходом (FALSE);
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
                                    //System.out.println("Трата True " + MonthlyReport.SumOfExpenses(monthReport[j]));
                                    isError = true;
                                }
                            } else {
                                if (yearReport.amount.get(i) != MonthlyReport.SumOfIncome(monthReport[j])) {
                                    System.out.println("Отчёт за месяц " + GetMonth(j)
                                            + " не соответствует отчёту за год!");
                                    //System.out.println("Доход False " + MonthlyReport.SumOfExpenses(monthReport[j]));
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
                                    + " составил: " + maxSum + " у. е.");

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
                            System.out.print("Прибыль за: " + GetMonth(i));
                            System.out.println(YearlyReport.PrintProfit(yearReport, i * 2));
                        }

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