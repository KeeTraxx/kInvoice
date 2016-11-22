package ch.compile.kinvoice.controller;

import ch.compile.kinvoice.model.Address;
import ch.compile.kinvoice.model.Invoice;
import ch.compile.kinvoice.model.InvoiceStatus;
import ch.compile.kinvoice.model.KInvoiceUserSettings;
import ch.compile.kinvoice.report.InvoiceReportView;
import ch.compile.kinvoice.repository.InvoiceRepository;
import ch.compile.kinvoice.repository.KInvoiceUserSettingsRepository;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    KInvoiceUserSettingsRepository kInvoiceUserSettingsRepository;

    @PostConstruct
    void init() {
        //Invoice i = new Invoice(new Address("Khôi Tran", "Chasseralstrasse 26", "3063", "Ittigen", "Switzerland"), new Address("Khôi Tran", "Chasseralstrasse 26", "3063", "Ittigen", "Switzerland"), "mySales", "myPO", LocalDate.now(), InvoiceStatus.UNPAID, 3000);
        //invoiceRepository.save(i);
    }

    @Autowired
    private InvoiceReportView invoiceReportView;

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public Invoice newInvoice(@RequestBody Invoice invoice, Principal principal) {
        KInvoiceUserSettings settings = kInvoiceUserSettingsRepository.findOne(principal.getName());
        if ( settings == null) {
            throw new RuntimeException("No settings.");
        }
        invoice.setCreditorAddress(settings.getInvoiceAddress());
        invoice.setCreditorAccount(settings.getInvoiceAccount());
        invoice.setStatus(InvoiceStatus.UNPAID);
        invoice.setDueDate(LocalDate.now().plusDays(30));
        return invoiceRepository.save(invoice);
    }

    @RequestMapping("/fakeInvoice")
    public Invoice fakeInvoice() {
        return new Invoice(Address.DUMMY, Address.DUMMY, new BigDecimal(3000));
    }

    @RequestMapping(value = "/report/{ids}", method = RequestMethod.GET)
    public ModelAndView getReports(@PathVariable String ids) {
        Map<String, Object> parameterMap = new HashMap<>();
        List<String> strings = Splitter.on(',').trimResults().omitEmptyStrings().splitToList(ids);
        List<Long> longs = strings.stream().map(Long::valueOf).collect(Collectors.toList());
        Collection<Invoice> invoices = Lists.newArrayList(invoiceRepository.findAll(longs));
        parameterMap.put("datasource", new JRBeanCollectionDataSource(invoices));
        parameterMap.put("format", "pdf");
        return new ModelAndView(invoiceReportView, parameterMap);
    }
}
