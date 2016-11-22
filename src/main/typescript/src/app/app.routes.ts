import {Routes} from "@angular/router";
import {InvoiceComponent} from "./invoice/invoice.component";
import {SettingsComponent} from "./settings/settings.component";
import {NewInvoiceComponent} from "./newinvoice/newinvoice.component";

export const appRoutes: Routes = [
  {path: '', component: InvoiceComponent},
  {path: 'new', component: NewInvoiceComponent},
  {path: 'settings', component: SettingsComponent},
];
