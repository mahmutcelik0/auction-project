import { useState, useEffect } from "react";
import { User } from "./types";
import { Login } from "./screens/login";
import { Home } from "./screens/home";

const App = () => {
  const [user, setUser] = useState<User>({
    id: "",
    username: "",
    role: "USER",
  });

  if (user.username) {
    return (
      <div className="mx-auto w-full max-w-4xl">
        <Home user={user} />
      </div>
    );
  }

  return (
    <div>
      <Login setUser={setUser} />
    </div>
  );
};

export default App;
