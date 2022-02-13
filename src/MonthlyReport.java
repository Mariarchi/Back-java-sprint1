public class MonthlyReport {
    public static int SumOfIncome(MonthReport monthReportSearchingSum) {
        int sumIncome = 0;
        for (int i = 0; i < monthReportSearchingSum.itemName.size(); i++) {
            if (!monthReportSearchingSum.isExpense.get(i)) {
                sumIncome += monthReportSearchingSum.quantity.get(i) * monthReportSearchingSum.sumOfOne.get(i);
            }
        }
        return sumIncome;
    }

    public static int SumOfExpenses(MonthReport monthReportSearchingSum) {
        int sumExpenses = 0;
        for (int i = 0; i < monthReportSearchingSum.itemName.size(); i++) {
            if (monthReportSearchingSum.isExpense.get(i)) {
                sumExpenses += monthReportSearchingSum.quantity.get(i) * monthReportSearchingSum.sumOfOne.get(i);
            }
        }
        return sumExpenses;
    }

    public static Integer TheMostProfitableProduct(MonthReport monthReportSearchProfit) {
        int maxSum = 0;
        int index = 0;
        for (int i = 0; i < monthReportSearchProfit.itemName.size(); i++) {
            if ((maxSum < monthReportSearchProfit.sumOfOne.get(i) * monthReportSearchProfit.quantity.get(i))
                    && !monthReportSearchProfit.isExpense.get(i)) {
                maxSum = monthReportSearchProfit.sumOfOne.get(i) * monthReportSearchProfit.quantity.get(i);
                index = i;
            }
        }
        return index;
    }

    public static Integer TheBiggestWaste(MonthReport monthReportSearchWaste) {
        int maxSum = 0;
        int index = 0;
        for (int i = 0; i < monthReportSearchWaste.itemName.size(); i++) {
            if ((maxSum < monthReportSearchWaste.sumOfOne.get(i) * monthReportSearchWaste.quantity.get(i))
                    && monthReportSearchWaste.isExpense.get(i)) {
                maxSum = monthReportSearchWaste.sumOfOne.get(i) * monthReportSearchWaste.quantity.get(i);
                index = i;
            }
        }
        return index;
    }
}
