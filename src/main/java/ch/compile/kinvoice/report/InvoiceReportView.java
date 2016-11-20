package ch.compile.kinvoice.report;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView;

@Component
public class InvoiceReportView extends JasperReportsMultiFormatView {
    public InvoiceReportView() {
        super();
        this.setUrl("classpath:jasper/InvoiceReport.jrxml");
        this.setReportDataKey("datasource");
    }
}
