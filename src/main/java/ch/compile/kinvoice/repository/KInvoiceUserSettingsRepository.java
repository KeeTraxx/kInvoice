package ch.compile.kinvoice.repository;

import ch.compile.kinvoice.model.KInvoiceUserSettings;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface KInvoiceUserSettingsRepository extends PagingAndSortingRepository<KInvoiceUserSettings, String> {
}
