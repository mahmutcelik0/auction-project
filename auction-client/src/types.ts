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

export type Auction = {
  id: string;
  product: Product;
  user: User;
  end: boolean;
  winner: User | null;
};
