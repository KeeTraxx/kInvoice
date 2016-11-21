package ch.compile.vesr;

import org.junit.Test;
import java.math.BigDecimal;

public class VesrTest {

    @Test
    public void testChecksumFromAmount() {
        String t = Vesr.getChecksumFromAmount(new BigDecimal(100), VesrType.PAYMENT_CHF);
        assert t.equals("0100000100009");
    }

    @Test
    public void testChecksumFromReferenceNumber() {
        String t = Vesr.getChecksumFromReferenceNumber("80 58140 00000 00000 05000 7200");
        assert t.equals("805814000000000000500072001");
    }


    @Test
    public void testChecksumFromReferenceNumberShort() {
        String t = Vesr.getChecksumFromReferenceNumber("1234");
        assert t.equals("0000000000012347");
    }

    @Test
    public void testChecksumFromReferenceNumberLong() {
        String t = Vesr.getChecksumFromReferenceNumber("123456789012345678");
        assert t.equals("000000001234567890123456785");
    }

    @Test
    public void testFormattedReferenceNumberShort() {
        String t = Vesr.getFormattedReferenceNumber("123456");
        assert t.equals("0 00000 00012 34565");
    }

    @Test
    public void testFormattedReferenceNumberLong() {
        String t = Vesr.getFormattedReferenceNumber("123456789012345678");
        assert t.equals("00 00000 01234 56789 01234 56785");
    }

    //0100000100009>0000000001234565+ 010015252>

    @Test
    public void testESRShort() {
        String t = Vesr.getESR(VesrType.PAYMENT_CHF, new BigDecimal(100.00),"123456", "01-001525-2");

        assert t.equals("0100000100009>0000000001234565+ 010015252>");

    }

    //0100000100009>000000001234567890123456785+ 010015252>
    //0100000100009>000000001234567890123456785+ 010015252>
    @Test
    public void testESRLong() {
        String t = Vesr.getESR(VesrType.PAYMENT_CHF, new BigDecimal(100.00),"123456789012345678", "01-001525-2");
        assert t.equals("0100000100009>000000001234567890123456785+ 010015252>");
    }

}
