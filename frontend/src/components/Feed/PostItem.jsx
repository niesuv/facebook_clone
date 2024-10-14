import { useQuery } from "@tanstack/react-query";
import React, { useState } from "react";
import { getUserDetails } from "../../util/http";
import { avt_default } from "../../util/const";
import { NavLink } from "react-router-dom";
import { ReactSVG } from "react-svg";

const PostItem = ({ post }) => {
  const { data, isLoading, isError } = useQuery({
    queryFn: () => {
      return getUserDetails(post.userId);
    },
    queryKey: ["users", post.userId],
    staleTime: 1000 * 60 * 5,
    cacheTime: 1000 * 60 * 2,
  });

  const [isLiked, setIsLike] = useState(false);

  return (
    <div className="flex flex-col w-full rounded-lg dark:bg-black  dark:text-text">
      <div className="pt-2 px-4 flex items-center gap-2">
        <div className="w-10 h-10 hover:brightness-110 rounded-full overflow-hidden ">
          <img
            src={data ? data.avtUrl : avt_default}
            className="object-cover h-full w-full"
            alt=""
          />
        </div>

        <div className="flex flex-col  ">
          {data ? (
            <NavLink
              to={`/${data.username}`}
              className="dark:text-white font-semibold hover:underline hover:underline-offset-2"
            >
              {data.fullname}
            </NavLink>
          ) : (
            <p></p>
          )}
          <p className="text-[0.8rem] dark:text-text hover:underline hover:underline-offset-1 cursor-pointer">
            {post.times}
          </p>
        </div>

        <div className="dark:text-white text-lg -mr-2 ml-auto rounded-full dark:hover:bg-gray w-10 h-10 justify-center flex items-center">
          <i className="fa-solid fa-ellipsis"></i>
        </div>

        <div className="dark:text-white text-lg -mr-2 rounded-full dark:hover:bg-gray w-10 h-10 justify-center flex items-center">
          <i className="fa-solid fa-xmark"></i>
        </div>
      </div>

      <div
        className="p-4 text-white text-md"
        style={{ whiteSpace: "pre-line" }}
      >
        {post.content}
      </div>

      <div className="flex flex-wrap justify-center">
        {post.images.map((img) => (
          <div className="w-full" key={Math.random()}>
            <img src={img.url} alt="" className="w-full" />
          </div>
        ))}
      </div>

      <div className="w-full flex  dark:bg-black justify-between h-10 items-center px-4 pt-4">
        <div className="flex gap-1 items-center ">
          <div className="">
            <ReactSVG
              src="/src/components/icons/like.svg"
              className="w-5"
            ></ReactSVG>
          </div>
          <p className="ml-1   hover:underline hover:underline-offset-1 cursor-pointer">
            {post.likes}
          </p>
        </div>

        <div className="flex gap-1 items-center">
          <p className="  hover:underline hover:underline-offset-1 cursor-pointer">
            {post.likes}
          </p>
          <i className="fa-solid fa-comment "></i>

          <p className="ml-3  hover:underline hover:underline-offset-1 hover:cursor-pointer">
            {post.shares}
          </p>
          <i className="fa-solid fa-share"></i>
        </div>
      </div>

      <div className="w-full px-4 mt-2">
        <div className="h-1 border-b-[1px] dark:border-b-gray w-full"></div>
      </div>

      <div className="flex justify-center items-center px-4 py-2"
        
      >
        <div className={isLiked ? "text-blue flex justify-center gap-3 flex-grow  dark:hover:bg-gray rounded-lg cursor-pointer items-center py-2"
          : "flex justify-center gap-3 flex-grow  dark:hover:bg-gray rounded-lg cursor-pointer items-center py-2"
        }
        onClick={() => {setIsLike(l => !l)}}

        >
          <i className="fa-regular fa-thumbs-up text-2xl"></i>
          <p className="text-md font-semibold">Thích</p>
        </div>

        <div className="flex justify-center gap-3 flex-grow dark:hover:bg-gray rounded-lg items-center py-2 cursor-pointer">
          <i className="fa-regular fa-comment text-2xl"></i>
          <p className="text-md font-semibold">Bình luận</p>
        </div>

        <div className="flex justify-center flex-grow gap-3 dark:hover:bg-gray rounded-lg items-center cursor-pointer py-2">
          <i className="fa-regular fa-share-from-square text-2xl"></i>
          <p className="text-md font-semibold">Chia sẻ</p>
        </div>
      </div>
    </div>
  );
};

export default PostItem;
