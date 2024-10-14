import React from "react";
import { useNavigate } from "react-router-dom";

export const NavBar = () => {
  const navigate = useNavigate();
  return (
    <div className="bg-black h-14 flex justify-between sticky top-0 z-10000 items-center py-[6px] px-8 ">
      <div id="left-nav" className="h-full flex justify-start gap-2">
        <div
          className="bg-blue rounded-full text-center flex items-end justify-center
        h-full aspect-1 text-white cursor-pointer"
          onClick={() => {
            navigate("/2242");
          }}
        >
          <i className="fa-brands fa-facebook-f text-3xl"></i>
        </div>

        <div
          className="bg-gray rounded-full text-center flex items-center justify-center
        h-full aspect-1 text-darkwhite cursor-pointer hover:brightness-110"
          onClick={() => {}}
        >
          <i className="fa-solid fa-magnifying-glass text-lg "></i>
        </div>
      </div>

      <div id="right-nav" className="h-full flex justify-start gap-2">
        <div
          className="bg-gray rounded-full text-center flex items-center justify-center
        h-full aspect-1 text-darkwhite cursor-pointer hover:brightness-110"
          onClick={() => {}}
        >
          <i className="fa-solid fa-table-cells text-lg "></i>
        </div>

        <div
          className="bg-gray rounded-full text-center flex items-center justify-center
        h-full aspect-1 text-darkwhite cursor-pointer hover:brightness-110"
          onClick={() => {}}
        >
          <i className="fa-solid fa-bell text-lg "></i>
        </div>
      </div>
    </div>
  );
};
