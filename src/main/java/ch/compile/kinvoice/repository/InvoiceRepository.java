package ch.compile.kinvoice.repository;

import ch.compile.kinvoice.model.Invoice;
import ch.compile.kinvoice.model.InvoiceStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvoiceRepository extends PagingAndSortingRepository<Invoice, Long> {
    @Query("SELECT i FROM Invoice i WHERE status <> :state ORDER BY dueDate DESC")
    Page<Invoice> findAllExcept(Pageable page, @Param("state") InvoiceStatus status);
}
