import { useQuery } from "@tanstack/react-query";
import React from "react";
import { NavLink, useParams } from "react-router-dom";
import { getUserImages } from "../../../util/http";
import LoadingIndicator from "../../LoadingIndicator";
import ImagePreview from "./ImagePreview";
import FriendPreview from "./FriendPreview";
import PostList from "../../Feed/PostList";
const WallPost = () => {
  return (
    <div className=" flex flex-col lg:flex-row  w-full pb-4 gap-4 justify-start">
      <div className="flex flex-col gap-4 w-full lg:w-[41%] mt-5 ">
        <ImagePreview></ImagePreview>
        <FriendPreview></FriendPreview>
      </div>
      <PostList></PostList>

    </div>
  );
};

export default WallPost;
