export type UserRoles = "admin" | "user";

export type User = {
  id: string;
  userName: string;
  role: UserRoles;
};

export type Product = {
  productName: string;
  basePrice: number;
  currentPrice: number;
};

export type Auction = {
  id: string;
  product: Product;
  createdByUser: User;
};
