package Utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by admin on 4/30/18.
 */

public class FormatTest {

    @Test
    public void testFormatCurrency() {
        double value = 1000000d;
        Assert.assertEquals("VND1,000,000", Format.formatCurrency(value));
    }
}
