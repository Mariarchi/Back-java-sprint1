public class YearlyReport {
    public static int PrintProfit(YearReport monthProfit, int monthIndex) {
        int expense = 0;
        int income = 0;

        // ищем доход и расход за указанный месяц
        for (int i = 0; i < monthProfit.month.size(); i++) {
            if (("0" + monthIndex).equals(monthProfit.month.get(i))) {
                if (monthProfit.isExpense.get(i)) {
                    expense = monthProfit.amount.get(i);
                } else {
                    income = monthProfit.amount.get(i);
                }
            }
        }
        return income - expense;
    }

    public static int AverageExpensesProfit(YearReport monthsExpensesProfit, boolean expensesOrProfit) {
        int monthTotal = 0;
        for (int i = 0; i < monthsExpensesProfit.month.size(); i++) {
            if (monthsExpensesProfit.isExpense.get(i) == expensesOrProfit) {
                monthTotal += monthsExpensesProfit.amount.get(i);
            }
        }
        return monthTotal / (monthsExpensesProfit.month.size() / 2);
    }
}