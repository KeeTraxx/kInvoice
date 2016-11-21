import { Component, OnInit } from '@angular/core';
import {Http} from "@angular/http";
import {InvoiceService} from "../invoice.service";

@Component({
  selector: 'app-invoice',
  templateUrl: './invoice.component.html',
  styleUrls: ['./invoice.component.css']
})
export class InvoiceComponent implements OnInit {

  constructor(private http:Http, private invoiceService:InvoiceService) {

  }

  ngOnInit() {
  }

  test() {
    this.invoiceService.saveInvoice({
      amount: 3000,
      clientAddress: {
        name: 'Peter Muster',
        street: "Musterstrasse 123",
        zip: "3072",
        city: "Ostermundigen"
      },
      invoiceAddress: {
        name: 'Peter Muster',
        street: "Musterstrasse 123",
        zip: "3072",
        city: "Ostermundigen"
      }
    }).then(console.log);
  }
}
