package ch.compile.kinvoice.model;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class KInvoiceUserSettings {
    @Id
    private String id;

    private Address invoiceAddress;
    private String invoiceAccount;
}
