import {Injectable} from '@angular/core';
import {Http} from "@angular/http";
import 'rxjs/add/operator/toPromise';
import {Invoice} from "./invoice";
@Injectable()
export class InvoiceService {

  constructor(private http: Http) {
  }

  getInvoices(link:string = '/api/invoices') {
    return this.http.get(link).toPromise()
      .then(res => res.json());
  }

  saveInvoice(invoice:Invoice) {
    return this.http.post('/api/invoices/new', invoice).toPromise();
  }

  updateStatus(invoice:Invoice) {
    return this.http.put('/api/invoices/'+invoice.id+'/updateStatus', invoice.status).toPromise();
  }

}
