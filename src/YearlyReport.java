public class YearlyReport {
    public static int PrintProfit(YearReport monthProfit, int monthIndex) {
        if (monthProfit.month.get(monthIndex).equals("01")) {
            return monthProfit.amount.get(0) - monthProfit.amount.get(1);
        } else if (monthProfit.month.get(monthIndex).equals("02")) {
            return monthProfit.amount.get(2) - monthProfit.amount.get(3);
        } else {
            return monthProfit.amount.get(4) - monthProfit.amount.get(5);
        }
    }
}
