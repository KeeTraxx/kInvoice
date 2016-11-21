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

@NgModule({
  declarations: [
    AppComponent,
    InvoiceComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(appRoutes),
    DataTableModule
  ],
  providers: [InvoiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
