import {Component, OnInit} from '@angular/core';
import {Invoice} from "../invoice";
import {InvoiceService} from "../invoice.service";

@Component({
  selector: 'app-newinvoice',
  templateUrl: './newinvoice.component.html',
  styleUrls: ['./newinvoice.component.css']
})
export class NewInvoiceComponent implements OnInit {

  alerts: any = [];

  invoice: Invoice = {
    amount: 0,
    clientAddress: {
      name: '',
      country: '',
      city: '',
      company: '',
      street: '',
      zip: ''
    },
    invoiceAddress: {
      name: '',
      country: '',
      city: '',
      company: '',
      street: '',
      zip: ''
    },
    purchaseOrder: '',
    salesOrder: ''
  };

  sameAsClient: boolean = true;

  constructor(private invoiceService: InvoiceService) {
  }

  ngOnInit() {
  }

  save() {
    if (this.sameAsClient) {
      this.invoice.invoiceAddress = this.invoice.clientAddress;
    }

    this.invoiceService.saveInvoice(this.invoice)
      .then(() => this.alerts.push({msg: 'Settings saved!', type: 'success'}))
      .catch(() => this.alerts.push({msg: 'Error saving settings!', type: 'danger'}));
  }

}
