import {Injectable} from '@angular/core';
import {Http} from "@angular/http";
import 'rxjs/add/operator/toPromise';
import {Invoice} from "./invoice";
import {Page} from "./page";
@Injectable()
export class InvoiceService {

  constructor(private http: Http) {
  }

  getInvoices(page: number = 0) {
    return this.http.get('/api/invoices?page=' + page).toPromise()
      .then(res => res.json())
      .then(data => {
        data.content.forEach(invoice => {
          invoice.dueDate = new Date(invoice.dueDate);
          invoice.createdDate = new Date(invoice.createdDate);
          invoice.modifiedDate = new Date(invoice.modifiedDate);
        });
        return data as Page<Invoice>;
      });
  }

  saveInvoice(invoice: Invoice) {
    return this.http.post('/api/invoices/new', invoice).toPromise();
  }

  updateStatus(invoice: Invoice) {
    return this.http.put('/api/invoices/' + invoice.id + '/updateStatus', invoice.status).toPromise();
  }

}
