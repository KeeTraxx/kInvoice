package ch.compile.kinvoice.repository;

import ch.compile.kinvoice.model.Invoice;
import ch.compile.kinvoice.model.InvoiceStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.stream.Stream;

public interface InvoiceRepository extends PagingAndSortingRepository<Invoice, Long> {
    @Query("SELECT i FROM Invoice i WHERE status <> :state AND user = :user ORDER BY dueDate DESC")
    Page<Invoice> findAllExcept(Pageable page, @Param("state") InvoiceStatus status, @Param("user") String user);

    @Query("SELECT i FROM Invoice i WHERE id in :ids AND user = :user ORDER BY dueDate DESC")
    Stream<Invoice> findAllByUser(@Param("ids") Iterable<Long> ids, @Param("user") String user);
}
