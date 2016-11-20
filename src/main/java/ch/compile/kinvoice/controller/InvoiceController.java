package ch.compile.kinvoice.controller;

import ch.compile.kinvoice.model.Invoice;
import ch.compile.kinvoice.report.InvoiceReportView;
import ch.compile.kinvoice.repository.InvoiceRepository;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {

    @Autowired
    InvoiceRepository invoiceRepository;

    @PostConstruct
    void init() {
        //Invoice i = new Invoice(new Address("Khôi Tran", "Chasseralstrasse 26", "3063", "Ittigen", "Switzerland"), new Address("Khôi Tran", "Chasseralstrasse 26", "3063", "Ittigen", "Switzerland"), "mySales", "myPO", LocalDate.now(), InvoiceStatus.UNPAID, 3000);
        //invoiceRepository.save(i);
    }

    @Autowired
    private InvoiceReportView invoiceReportView;

    @RequestMapping("/report")
    public ModelAndView getReport() {
        Map<String, Object> parameterMap = new HashMap<>();
        Collection<Invoice> invoices = Invoice.createDummyList();
        parameterMap.put("datasource", new JRBeanCollectionDataSource(invoices));
        parameterMap.put("format", "pdf");
        parameterMap.put("test", "tester3");
        return new ModelAndView(invoiceReportView, parameterMap);
    }

}
