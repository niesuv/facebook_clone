import { useQuery } from "@tanstack/react-query";
import React from "react";
import { useParams } from "react-router-dom";
import { getUserDetails } from "../../util/http";

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
    <div className="w-full bg-black ">
      <div className="w-full flex-col md:max-w-[900px] flex mx-auto ">
        {isLoading && (
          <span className="loading loading-spinner text-blue w-16 mt-8 m-auto"></span>
        )}
        {data && (
          <>
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
              <div className="flex flex-col items-center md:mt-12 md:items-start md:ml-4">
                <p className="text-3xl text-white font-bold mt-2">
                  {data.fullname}
                </p>
                <p className="text-md text-darkwhite font-bold mt-2">
                  {data.totalFriend} người bạn
                </p>
              </div>
            </div>
          </>
        )}
      </div>

      <div className="h-[99999px] "></div>
    </div>
  );
};

export default Wall;
