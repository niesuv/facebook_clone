import React from "react";
import { NavLink, useParams } from "react-router-dom";
import LoadingIndicator from "../../LoadingIndicator";
import { useQuery } from "@tanstack/react-query";
import { getUserDetails } from "../../../util/http";

const FriendPreview = () => {
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
    <div className="flex flex-col dark:bg-black rounded-md  p-4 ">
      <div className="flex justify-between items-center">
        <NavLink
          to="friends"
          className="font-bold text-2xl text-white hover:underline"
        >
          Bạn bè
        </NavLink>
        <NavLink to="friends" className="dark:text-lightblue text-xl ">
          Xem tất cả bạn bè
        </NavLink>
      </div>

      <div
        className="grid grid-cols-3 gap-2 justify-center items-center
            mt-4 
          "
      >
        {isLoading && (
          <LoadingIndicator className={" mx-auto w-12 mt-4"}></LoadingIndicator>
        )}

        {data &&
          data.friends.slice(0, 9).map((f) => (
            <div className="flex flex-col mb-4" key={Math.random()}>
              <div className=" aspect-square hover:brightness-110 rounded-lg overflow-hidden">
                <img
                  src={f.url}
                  alt=""
                  className="object-cover w-full h-full"
                />
              </div>
              <NavLink className="font-semibold ">{f.fullname}</NavLink>
            </div>
          ))}
      </div>
    </div>
  );
};

export default FriendPreview;
