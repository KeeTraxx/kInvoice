import {Address} from "./address";
export interface Invoice {
  amount: number;
  clientAddress:Address;
  invoiceAddress:Address;
  creditorAddress?:Address;
  creditorAccount?:string;

}
