package vesr;

/**
 * Created by kt_qwacujb on 20.11.2016.
 */
public enum VesrType {
    PAYMENT_CHF("01"),
    PAYMENT_EURO("02");

    private final String text;

    private VesrType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
