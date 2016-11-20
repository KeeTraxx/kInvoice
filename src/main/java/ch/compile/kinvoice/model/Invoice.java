package ch.compile.kinvoice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import vesr.Vesr;
import vesr.VesrType;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Invoice extends BaseEntity implements Serializable {

    private static Long dummyId = (long)1;

    public static Invoice getDummyInvoice() {
        return new Invoice() {{
            setId(dummyId++);
            setClientAddress(Address.DUMMY);
            setInvoiceAddress(Address.DUMMY);
            setCreditorAddress(Address.DUMMY);
            setAmount(BigDecimal.valueOf(3000));
        }};
    }

    public static Collection<Invoice> createDummyList() {
        return new ArrayList<Invoice>(){{
            add(getDummyInvoice());
            add(getDummyInvoice());
            add(getDummyInvoice());
        }};
    }

    @NonNull
    private Address invoiceAddress;

    @NonNull
    private Address clientAddress;

    @NonNull
    private Address creditorAddress;

    private String salesOrder;
    private String purchaseOrder;

    private LocalDate dueDate = LocalDate.now().plusDays(30);
    private InvoiceStatus status = InvoiceStatus.UNPAID;

    @NonNull
    @Column(precision = 8, scale = 2)
    private BigDecimal amount;

    public String getFormattedReferenceNumber() {
        return Vesr.getFormattedReferenceNumber(getReferenceNumber());
    }

    public String getReferenceNumber() {
        return String.format("%04d%05d", LocalDate.now().getYear(), id);
    }

    public String getEsr() {
        return Vesr.getESR(VesrType.PAYMENT_CHF, amount, getReferenceNumber(),"01-92722-7");
    }

}
