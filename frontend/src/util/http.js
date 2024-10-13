function sleep(ms) {
  return new Promise((resolve) => setTimeout(resolve, ms));
}

export const getUserDetails = async (username) => {
  if (username !== "niesuv") {
    const data = {
      fullname: "Hữu Sang",
      totalFriend: 502,
      username: "niesuv",
      id: "123114",
      friends: [
        {
          url: "https://picsum.photos/600/400",
        },
        {
          url: "https://picsum.photos/700/400",
        },
        {
          url: "https://picsum.photos/400/400",
        },
        {
          url: "https://picsum.photos/1000/400",
        },
        {
          url: "https://picsum.photos/600/600",
        },
        {
          url: "https://picsum.photos/600/500",
        },
        {
          url: "https://picsum.photos/600/450",
        },
      ],
      backgroundUrl:
        "https://scontent.fsgn19-1.fna.fbcdn.net/v/t39.30808-6/326741594_1397625924315952_8369081978684846629_n.jpg?stp=dst-jpg_s960x960&_nc_cat=104&ccb=1-7&_nc_sid=cc71e4&_nc_eui2=AeHBFltDfNZa7vEiyAN6XwfY0I1RDkorEnLQjVEOSisSchMu2fmPXb2yb1F6OaBzUtsInw2dD27QiTXv345BiaWS&_nc_ohc=R0fznjYpfw0Q7kNvgFINFSG&_nc_ht=scontent.fsgn19-1.fna&_nc_gid=A6l9Ci0uAhXl7IjD8reeqtr&oh=00_AYAbgtN0fgAy2vGhGt2urAW2WhtLfyaJjJzyMW0Fl1xeUw&oe=6706C392",
      avtUrl:
        "https://scontent.fsgn19-1.fna.fbcdn.net/v/t39.30808-1/447458148_1428209785247518_705161193208956860_n.jpg?stp=dst-jpg_s200x200&_nc_cat=110&ccb=1-7&_nc_sid=0ecb9b&_nc_eui2=AeFvc6Yux-WdhtPuehYxIpS4bI7Azy2yZspsjsDPLbJmysTqAA6dQw2gl73wxMxelC1FfGIbhQ87id32A2kKk7uA&_nc_ohc=WhGMhrK6J2MQ7kNvgEUZRxu&_nc_ht=scontent.fsgn19-1.fna&_nc_gid=A6l9Ci0uAhXl7IjD8reeqtr&oh=00_AYDWkBDBDhwdDrG07hOpBPkwbyf8QmJkRKas1Iy7nGWoaQ&oe=6706EDAF",
    };
    await sleep(500);
    return Promise.resolve(data);
  }
};

export const getNewPost = async () => {
  await sleep(500);
  const data = [
    {
      username: "niesuv",
      postId: "1",
      content:
        "ÔNG THÍCH TÂM PHÚC CHẤP NHẬN MỨC ÁN 8 NĂM TÙ, KHAI NHẬN TẤT CẢ CÁC GIẤY TỜ BẰNG CẤP, SỔ ĐỎ ĐẤT ĐAI ĐỀU LÀ GIẢ 😐 \nBị tòa sơ thẩm tuyên phạt 8 năm tù, ông Nguyễn Minh Phúc kháng cáo xin giảm nhẹ hình phạt. Tuy nhiên, quá trình chờ phúc thẩm, người này bất ngờ rút kháng cáo, chấp nhận hình phạt.\nTheo | Thanh Niên",
      images: [
        {
          url: "https://img.asmedia.epimg.net/resizer/v2/2RWVP53F2RFALMHUGNLARYHV54.jpg?auth=6a6d0dc5842549395820621b2fbb951b0422dbf5d494297ca5c5c65007f74884&width=1200&height=1200&smart=true",
        },
      ],
      likes: 10,
      comments: 5,
      shares: 10,
      times: "1 ngày",
    },
    {
      username: "niesuv",
      postId: "2",
      content: "Hello My Friends",
      images: [
        {
          url: "https://scontent.fsgn19-1.fna.fbcdn.net/v/t39.30808-6/462485255_1366017264784418_3688851991056246265_n.jpg?_nc_cat=103&ccb=1-7&_nc_sid=aa7b47&_nc_eui2=AeGLgQOMTGkCrFO5RuZ7085_bokdUpAWoDRuiR1SkBagNJE6yUhk-ozywEAloj9DLVLj5fr6rGuBXYCjb4SDsV51&_nc_ohc=ib8isxKEttEQ7kNvgGPbnWi&_nc_ht=scontent.fsgn19-1.fna&_nc_gid=AWI_pKsFG2tmZ1VwCfx4ALK&oh=00_AYB-tgcXENQbgRr6A1O94dhVL65jK4s-6crCPWznsOKSCA&oe=670C8487",
        },
      ],
      likes: 10,
      comments: 5,
      shares: 10,
      times: "1 ngày",
    },
  ];
  return Promise.resolve(data);
};
