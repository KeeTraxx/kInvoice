export interface Page {
  _embedded:any;
  _links: {
    profile: string
    self: string;
    next?: string;
    prev?: string;
    first?:string;
    last?:string;
  }
}
