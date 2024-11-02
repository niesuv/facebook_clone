import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { Outlet } from "react-router-dom";
import Header from "./NavBar/Header";
import { store } from "../store/index";
import { Provider } from "react-redux";

const client = new QueryClient();

const Root = () => {
  return (
    <Provider store={store}>
      <QueryClientProvider client={client}>
        <div className="flex flex-col w-full dark:bg-darkblack h-full">
          <Header></Header>
          <Outlet></Outlet>
        </div>
      </QueryClientProvider>
    </Provider>
  );
};

export default Root;
