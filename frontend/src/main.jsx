import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import { createBrowserRouter, RouterProvider } from "react-router-dom";

import "./styles/global.css";
import "./index.css";
import "./styles/icons/icons.css";
import Root from "./components/Root";
import Wall from "./components/Wall";
import Feed from "./components/Feed";
import WallPost from "./components/Wall/Index/WallPost";
import WallFriend from "./components/Wall/Friends/WallFriend";
import WallPhotos from "./components/Wall/Photo/WallPhotos";
const router = createBrowserRouter([
  {
    path: "/",
    element: <Root />,
    children: [
      {
        path: "",
        index: true,
        element: <Feed></Feed>,
      },

      {
        path: "/:username",
        element: <Wall />,
        children: [
          {
            path: "",
            index: true,
            element: <WallPost></WallPost>,
          },

          {
            path: "friends",
            element: <WallFriend></WallFriend>,
          },

          {
            path: "photos",
            element: <WallPhotos></WallPhotos>,
          },
        ],
      },
    ],
  },
]);

createRoot(document.getElementById("root")).render(
  <StrictMode>
    <RouterProvider router={router} />
  </StrictMode>
);
