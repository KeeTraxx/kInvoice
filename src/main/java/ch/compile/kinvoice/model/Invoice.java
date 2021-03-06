package ch.compile.kinvoice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ch.compile.vesr.Vesr;
import ch.compile.vesr.VesrType;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Table(indexes = {@Index(columnList = "user")})
@Data
public class Invoice extends BaseEntity implements Serializable {

    private static Long dummyId = (long) 1;

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
        return new ArrayList<Invoice>() {{
            add(getDummyInvoice());
            add(getDummyInvoice());
            add(getDummyInvoice());
        }};
    }

    private String user;

    @NonNull
    private Address invoiceAddress;

    @NonNull
    private Address clientAddress;

    private Address creditorAddress;

    private String creditorAccount;

    private String salesOrder;
    private String purchaseOrder;

    @JsonFormat(pattern = "yyyy-MM-dd")
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
        return Vesr.getESR(VesrType.PAYMENT_CHF, amount, getReferenceNumber(), getCreditorAccount());
    }

}
