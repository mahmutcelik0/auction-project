import { User } from "@/types";
import { LoginForm } from "./LoginForm";
import { FC } from "react";

type LoginProps = {
  setUser: React.Dispatch<React.SetStateAction<User>>;
};
export const Login: FC<LoginProps> = ({ setUser }) => {
  return (
    <div className=" w-screen h-screen flex items-center justify-center">
      <LoginForm setUser={setUser} />
    </div>
  );
};
