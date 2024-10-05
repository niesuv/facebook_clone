import React from "react";
import { Outlet } from "react-router-dom";
import { NavBar } from "./NavBar/NavBar";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import "../output.css";
import "../index.css";


const client = new QueryClient();

const Root = () => {
  return (
    <QueryClientProvider client={client}>
      <div className="flex flex-col w-screen">
        <NavBar></NavBar>
        <Outlet></Outlet>
      </div>
    </QueryClientProvider>
  );
};

export default Root;
