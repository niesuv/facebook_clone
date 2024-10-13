import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import { createBrowserRouter, RouterProvider } from "react-router-dom";

import "./styles/global.css";
import "./index.css";
import "./styles/icons/icons.css";
import Root from "./components/Root";
import Wall from "./components/Wall";
import Feed from "./components/Feed";
const router = createBrowserRouter([
  {
    path: "/",
    element: <Root />,
    children: [
      {
        path: "",
        index: true,
        element: <Feed></Feed>

      }

      ,
      {
        path: "/:username",
        element: <Wall />,
      },
    ],
  },
 
]);

createRoot(document.getElementById("root")).render(
  <StrictMode>
    <RouterProvider router={router} />
  </StrictMode>
);
