package vesr;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Created by kt_qwacujb on 20.11.2016.
 */
public class Vesr {

    private final static int[] algorithm = {0, 9, 4, 6, 8, 2, 7, 1, 3, 5};

    private final static BigDecimal max = new BigDecimal(9999999999.99);

    public static int getChecksum(int[] input) {
        int r1 = 0;
        for (int i = 0; i < input.length; i++) {
            r1 = algorithm[(r1 + input[i]) % 10];
        }
        return (10 - r1) % 10;

    }

    public static String getChecksumFromAmount(BigDecimal amount, VesrType vesrType) {
        amount = amount.setScale(2, BigDecimal.ROUND_HALF_UP);
        if (amount.compareTo(max) != -1) {
            throw new RuntimeException("Amount too big");
        }

        String value = String.format("%s%010d", vesrType, amount.multiply(new BigDecimal(100)).intValue());

        String[] a = value.split("");
        int[] ints = new int[a.length];

        for (int i = 0; i < a.length; i++) {
            ints[i] = Integer.parseInt(a[i]);
        }

        return String.join("", a) + getChecksum(ints);
    }

    public static String getChecksumFromReferenceNumber(String referenceNumber) {
        String stripped = referenceNumber.replaceAll("[^\\d]", "");
        String input = String.format(stripped.length() < 16 ? "%15s" : "%26s", stripped).replace(' ', '0');
        int[] ints = new int[stripped.length() < 16 ? 15 : 26];
        int start = ints.length - stripped.length();
        for (int i = 0; i < stripped.length(); i++) {
            ints[start + i] = stripped.charAt(i) - '0';
        }

        return input+getChecksum(ints);
    }

    public static String getESR(VesrType vesrType, BigDecimal amount, String referenceNumber, String memberNumber) {
        return String.format("%s>%s+ %s>",
                getChecksumFromAmount(amount, vesrType),
                getChecksumFromReferenceNumber(referenceNumber),
                memberNumber.replaceAll("[^\\d]", "")
        );
    }

    public static String getFormattedReferenceNumber(String referenceNumber) {
        String result = getChecksumFromReferenceNumber(referenceNumber);
        String reverse = Joiner.on(' ').join(Splitter.fixedLength(5).split(new StringBuilder(result).reverse().toString()));
        return new StringBuilder(reverse).reverse().toString();

    }
}
