import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import App from "./App";

import { StompSessionProvider } from "react-stomp-hooks";

ReactDOM.createRoot(document.getElementById("root")!).render(
  <StompSessionProvider
    url={"http://localhost:8080/ws"}
    //All options supported by @stomp/stompjs can be used here
  >
    <App />
  </StompSessionProvider>
);
