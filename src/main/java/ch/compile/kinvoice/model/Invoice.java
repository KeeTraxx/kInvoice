package ch.compile.kinvoice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

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

    private String salesOrder;
    private String purchaseOrder;

    private LocalDate dueDate = LocalDate.now().plusDays(30);
    private InvoiceStatus status = InvoiceStatus.UNPAID;

    @NonNull
    @Column(precision = 8, scale = 2)
    private BigDecimal amount;
}
