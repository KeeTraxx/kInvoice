package ch.compile.kinvoice.repository;

import ch.compile.kinvoice.model.KInvoiceUserSettings;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface KInvoiceUserSettingsRepository extends PagingAndSortingRepository<KInvoiceUserSettings, String> {
}
