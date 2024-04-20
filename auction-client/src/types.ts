export type UserRoles = "ADMIN" | "USER";

export type User = {
  id: string;
  username: string;
  role: UserRoles;
};

export type Product = {
  productName: string;
  basePrice: number;
  currentPrice: number;
};

export type LogRecords = {
  user: User;
  message: string;
  time: string;
};

export type Auction = {
  id: string;
  product: Product;
  user: User;
  end?: boolean;
  winnerUser?: User | null;
  logRecords?: LogRecords[];
};

export type Offer = {
  user: User;
  offerPrice: number;
};

export type OfferRequest = {
  auctionId: string;
  offer: Offer;
};
