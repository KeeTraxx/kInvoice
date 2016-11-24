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

  private data:Page<Invoice>;
  private page:number = 0;

  constructor(private invoiceService: InvoiceService) {

  }

  ngOnInit() {
    this.refresh();
  }

  refresh() {
    this.invoiceService.getInvoices(this.page).then(page => {
      this.data = page;
    });
  }

  first() {
    this.page = 0;
    this.refresh();
  }

  prev() {
    this.page--;
    this.refresh();
  }

  next() {
    this.page++;
    this.refresh();
  }

  last() {
    this.page = this.data.totalPages-1;
    this.refresh();
  }


  isOverDue(invoice:Invoice) {
    return (new Date().getTime() - invoice.dueDate.getTime()) > 0;
  }

  setStatus(status:string, invoice:Invoice) {
    invoice.status = status;

    this.invoiceService.updateStatus(invoice).then(console.log).catch(console.error);
  }
}
