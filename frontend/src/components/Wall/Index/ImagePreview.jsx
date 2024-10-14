import { useQuery } from "@tanstack/react-query";
import React from "react";
import { NavLink, useParams } from "react-router-dom";
import LoadingIndicator from "../../LoadingIndicator";
import { getUserImages } from "../../../util/http";

const ImagePreview = () => {
  const username = useParams("username");
  const { data, isLoading, isError } = useQuery({
    queryFn: () => getUserImages(username),
    queryKey: ["user", username, "image"],
    staleTime: 1000 * 60 * 5,
    cacheTime: 1000 * 60 * 2,
  });
  return (
    <div className="flex flex-col dark:bg-black rounded-md  p-4 ">
      <div className="flex justify-between items-center">
        <NavLink to="photos" className="font-bold text-2xl text-white hover:underline">Ảnh</NavLink>
        <NavLink to="photos" className="dark:text-lightblue text-xl ">
          Xem tất cả ảnh
        </NavLink>
      </div>

      <div
        className="grid grid-cols-3 gap-2 justify-center items-center
         rounded-md overflow-hidden mt-4  
        "
      >
        {isLoading && (
          <LoadingIndicator className={" mx-auto w-12 mt-4"}></LoadingIndicator>
        )}

        {data &&
          data.slice(0, 9).map((img) => (
            <div className=" aspect-square hover:brightness-110 " key={Math.random()}>
              <img
                src={img.url}
                alt=""
                className="object-cover w-full h-full"
              />
            </div>
          ))}
      </div>
    </div>
  );
};

export default ImagePreview;
