import { Injectable } from '@angular/core';
import {Invoice} from "./invoice";
import {Http} from "@angular/http";
import 'rxjs/add/operator/toPromise';

@Injectable()
export class InvoiceService {

  constructor(private http:Http) { }

  saveInvoice(invoice:Invoice) {
    return this.http.post('/api/invoices/new', invoice).toPromise().then(res => res.json());
  }
}
