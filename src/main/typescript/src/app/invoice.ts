import {Address} from "./address";
export interface Invoice {
  /*
  @NonNull
  private Address invoiceAddress;

  @NonNull
  private Address clientAddress;

  private Address creditorAddress;

  private String creditorAccount;

  private String salesOrder;
  private String purchaseOrder;

  private LocalDate dueDate = LocalDate.now().plusDays(30);
  private InvoiceStatus status = InvoiceStatus.UNPAID;

  @NonNull
  @Column(precision = 8, scale = 2)
  private BigDecimal amount;
*/

  invoiceAddress:Address;
  clientAddress:Address;
  creditorAddress?:Address;

  creditorAccount?:string;

  salesOrder?:string;
  purchaseOrder?:string;

  dueDate?:Date;
  status?:string;

  amount:number;

}
