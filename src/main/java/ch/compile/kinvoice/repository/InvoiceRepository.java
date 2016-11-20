package ch.compile.kinvoice.repository;

import ch.compile.kinvoice.model.Invoice;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface InvoiceRepository extends PagingAndSortingRepository<Invoice, Long> {
}
