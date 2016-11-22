import {Routes} from "@angular/router";
import {InvoiceComponent} from "./invoice/invoice.component";
import {SettingsComponent} from "./settings/settings.component";

export const appRoutes: Routes = [
  {path: '', component: InvoiceComponent},
  {path: '/settings', component: SettingsComponent}
];
