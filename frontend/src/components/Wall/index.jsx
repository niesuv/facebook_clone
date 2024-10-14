import { useQuery } from "@tanstack/react-query";
import React from "react";
import { NavLink, Outlet, useParams } from "react-router-dom";
import { getUserDetails } from "../../util/http";
import FriendIcon from "./FriendIcon";

const Wall = () => {
  const username = useParams("username");
  const { data, isLoading, isError } = useQuery({
    queryFn: () => {
      return getUserDetails(username);
    },
    queryKey: ["users", username],
    staleTime: 1000 * 60 * 5,
    cacheTime: 1000 * 60 * 2,
  });

  return (
    <div className="w-full dark:bg-darkblack dark:text-text">
      <div className="w-full bg-black ">
        <div className="w-full flex-col dark:bg-black md:max-w-[900px] flex mx-auto ">
          {isLoading && (
            <span className="loading loading-spinner text-blue w-16 mt-8 m-auto"></span>
          )}
          {data && (
            <div className="flex flex-col">
              <div
                id="background"
                className=" bg-red-500 w-full relative  pt-[35%] hover:brightness-110 "
              >
                <img
                  src={data.backgroundUrl}
                  alt=""
                  className="w-full h-full object-cover absolute top-0"
                />

                <div className="rounded-md bg-white absolute  bottom-6 right-8  text-center inline px-2 py-[8px] hover:brightness-90">
                  <i className="fa-solid fa-camera text-2xl text-black"></i>
                </div>
              </div>

              <div
                id="avatar"
                className="relative w-full flex flex-col md:flex-row items-center -top-20
             md:justify-start"
              >
                <div
                  className="rounded-full aspect-1 text-center w-[33%] overflow-hidden border-4 border-black md:ml-8
            max-w-48 hover:brightness-110"
                >
                  <img src={data.avtUrl} alt="" className="object-cover" />
                </div>

                <div className="flex flex-col items-center md:mt-16 pt-2 md:items-start md:ml-4">
                  <p className="text-3xl text-white font-bold mt-2">
                    {data.fullname}
                  </p>
                  <p className="text-md text-darkwhite font-bold mt-2">
                    {data.totalFriend} người bạn
                  </p>
                  <div className="flex mt-2">
                    {data.friends.map((f, index) => (
                      <FriendIcon
                        key={Math.random()}
                        friend={f}
                        z={10 - index}
                      ></FriendIcon>
                    ))}
                  </div>
                </div>
              </div>

              <div className="w-full  border-b-[1px] -mt-10 border-gray "></div>
              <div className="flex font-semibold">
                <NavLink
                  to={"."}
                  end
                  className={({ isActive }) =>
                    isActive ? "text-blue border-b-4 p-4 border-b-blue" : "p-4"
                  }
                >
                  Bài viết
                </NavLink>

                <NavLink
                  to={"friends"}
                  className={({ isActive }) =>
                    isActive ? "text-blue border-b-4 p-4 border-b-blue" : "p-4"
                  }
                >
                  Bạn bè
                </NavLink>

                <NavLink
                  to={"photos"}
                  className={({ isActive }) =>
                    isActive ? "text-blue border-b-4 p-4 border-b-blue" : "p-4"
                  }
                >
                  Ảnh
                </NavLink>
              </div>
            </div>
          )}
        </div>
      </div>
      <div className="w-full mx-auto md:max-w-[900px] px-4 lg:px-0">
        <Outlet></Outlet>
      </div>
    </div>
  );
};

export default Wall;
