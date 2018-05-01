package Utils;

import java.text.NumberFormat;
import java.util.Currency;

/**
 * Created by admin on 4/29/18.
 */

public class Format {
    public static String formatCurrency(double value) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        formatter.setCurrency(Currency.getInstance("VND"));
        formatter.setMaximumFractionDigits(0);
        return formatter.format(value);
    }
}
