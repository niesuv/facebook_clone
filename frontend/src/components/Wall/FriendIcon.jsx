import React from "react";

const FriendIcon = ({ friend: { url }, z }) => {
  return (
    <div className="rounded-full overflow-hidden w-8 h-8 -ml-2 border-black border-2" style={{
      zIndex: z
    }}>
      <img src={url} alt="" className="h-full w-full object-cover " />
    </div>
  );
};

export default FriendIcon;
