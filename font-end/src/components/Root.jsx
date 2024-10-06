import React from "react";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { Outlet } from "react-router-dom";
import Header from "./NavBar/Header";


const client = new QueryClient();

const Root = () => {
  return (
    <QueryClientProvider client={client}>
      <div className="flex flex-col w-screen">
        <Header></Header>
        <Outlet></Outlet>
      </div>
    </QueryClientProvider>
  );
};

export default Root;
