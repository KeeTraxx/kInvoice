import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";
import {AppComponent} from "./app.component";
import {InvoiceComponent} from "./invoice/invoice.component";
import {RouterModule} from "@angular/router";
import {appRoutes} from "./app.routes";
import {DataTableModule} from "primeng/components/datatable/datatable";
import {InvoiceService} from "./invoice.service";
import {SettingsComponent} from "./settings/settings.component";
import {InputTextModule} from "primeng/components/inputtext/inputtext";
import {AlertModule} from "ng2-bootstrap";
import {SettingsService} from "./settings.service";
import { NewInvoiceComponent } from './newinvoice/newinvoice.component';

@NgModule({
  declarations: [
    AppComponent,
    InvoiceComponent,
    SettingsComponent,
    NewInvoiceComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(appRoutes),
    AlertModule
  ],
  providers: [InvoiceService, SettingsService],
  bootstrap: [AppComponent]
})
export class AppModule { }
