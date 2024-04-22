import { useState } from "react";
import { User } from "./types";
import { Login } from "./screens/login";
import { Home } from "./screens/home";
import { AdminPage } from "./screens/admin/AdminPage";

const App = () => {
  const [user, setUser] = useState<User>({
    id: "",
    username: "",
    role: "USER",
  });

  if (user.username) {
    if (user.role === "ADMIN") {
      // kullanıcı varsa ve admin ise admin sayfasını göster
      return (
        <div className="mx-auto w-full max-w-4xl">
          <AdminPage user={user} />
        </div>
      );
    }
    // kullanıcı varsa home sayfasını göster
    return (
      <div className="mx-auto w-full max-w-4xl">
        <Home user={user} />
      </div>
    );
  }
  // kullanıcı yoksa login sayfasını göster
  return (
    <div>
      <Login setUser={setUser} />
    </div>
  );
};

export default App;
