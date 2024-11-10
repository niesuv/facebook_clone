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
import { Provider, useDispatch, useSelector } from "react-redux";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import store from "./store/index";
import LoginPage from "./components/LoginPage";
import SignUpPage from "./components/SignUpPage";

const client = new QueryClient();

const router = createBrowserRouter([
  {
    path: "/",
    element: (
      <QueryClientProvider client={client}>
        <Provider store={store}>
          <Root />
        </Provider>
      </QueryClientProvider>
    ),
    children: [
      {
        path: "",
        index: true,
        element: <Feed></Feed>,
      },
      {
        path: "/login",
        element: <LoginPage />,
      },
      {
        path: "/signup",
        element: <SignUpPage />,
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
