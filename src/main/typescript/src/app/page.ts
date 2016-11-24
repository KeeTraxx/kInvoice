export interface Page<T> {
  content: T[];
  first: boolean;
  last: boolean;
  totalElements:number;
  totalPages: number;

  // pagesize
  numberOfElements:number;
  sort:any;
  size: number;
  number: number;
}
