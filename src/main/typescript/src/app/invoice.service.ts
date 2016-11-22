import {Injectable} from '@angular/core';
import {Http} from "@angular/http";
import 'rxjs/add/operator/toPromise';
import {Invoice} from "./invoice";
@Injectable()
export class InvoiceService {

  constructor(private http: Http) {
  }

  getInvoices() {
    return this.http.get('/api/invoices').toPromise()
      .then(res => res.json() as Invoice[])
      .catch(console.error);
  }

  saveInvoice(invoice:Invoice) {
    return this.http.post('/api/invoices/new', invoice).toPromise();
  }

}
