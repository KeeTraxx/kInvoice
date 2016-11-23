import {Component, OnInit} from "@angular/core";
import {InvoiceService} from "../invoice.service";
import {Invoice} from "../invoice";
import {Page} from "../page";

@Component({
  selector: 'app-invoice',
  templateUrl: './invoice.component.html',
  styleUrls: ['./invoice.component.css']
})
export class InvoiceComponent implements OnInit {

  private invoices: Invoice[];
  private data:Page;

  constructor(private invoiceService: InvoiceService) {

  }

  ngOnInit() {
    this.refresh('/api/invoices');
  }

  refresh(link:string) {
    this.invoiceService.getInvoices(link).then(hal => {
      this.data = hal;
      this.invoices = hal._embedded.invoices.map(invoice => {
        invoice.dueDate = new Date(invoice.dueDate);
        return invoice;
      }) as Invoice[];
    });
  }

  isOverDue(invoice:Invoice) {
    return (new Date().getTime() - invoice.dueDate.getTime()) > 0;
  }

  setStatus(status:string, invoice:Invoice) {
    invoice.status = status;

    this.invoiceService.updateStatus(invoice).then(console.log).catch(console.error);
  }
}
