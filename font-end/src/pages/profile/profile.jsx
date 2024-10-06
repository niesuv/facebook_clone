import React from "react";
import { NavBar } from "../../components/profile/NavBar/NavBar";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import "../../index.css"


const client = new QueryClient();

const ProfilePage = () => {
  return (
    <QueryClientProvider client={client}>
      <div className="flex flex-col w-screen">
        <NavBar></NavBar>
      </div>
    </QueryClientProvider>
  );
};

export default ProfilePage;
