package Utils;

import java.util.Arrays;
import java.util.List;

/**
 * Created by admin on 4/29/18.
 */

public class Validator {
    private static final List<String> PHONE_PREFIX_11 = Arrays.asList(
            "0120", "0121", "0122", "0123", "0124", "0125", "0126", "0127", "0128", "0129",
            "0162", "0163", "0164", "0165", "0166", "0167", "0168", "0169", "0186",
            "0188", "0199"
    );

    private static final List<String> PHONE_PREFIX_10 = Arrays.asList(
            "086", "088", "089", "090", "091", "092", "093", "094",
            "095", "096", "097", "098", "099"
    );
    public static boolean phoneValidator(String phone) {
        if (phone.length() < 4 || phone.length() > 11) {
            return false;
        }
        String phonePrefix3 = phone.substring(0, 3);
        String phonePrefix4 = phone.substring(0, 4);
        if (PHONE_PREFIX_10.contains(phonePrefix3) && phone.length() == 10) {
            return true;
        }
        if (PHONE_PREFIX_11.contains(phonePrefix4) && phone.length() == 11) {
            return true;
        }
        return false;
    }

    public static boolean nationalIDValidator(String id) {
        return id.matches("[A-Za-z0-9]{9,12}");
    }
}
