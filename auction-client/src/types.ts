export type UserRoles = "admin" | "user";

export type User = {
  userName: string;
  role: UserRoles;
};
