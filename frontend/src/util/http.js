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
                    fullname: "Huy Bui",
                    url: "https://picsum.photos/600/400",
                },
                {
                    fullname: "Manh Phan",
                    url: "https://picsum.photos/700/400",
                },
                {
                    fullname: "Tan Phat",
                    url: "https://picsum.photos/400/400",
                },
                {fullname: "Tan Phat", url: "https://picsum.photos/1000/400"},
                {fullname: "Tri Vu", url: "https://picsum.photos/600/600"},
                {fullname: "Uong Beo", url: "https://picsum.photos/600/500"},
                {fullname: "Gia Phuc", url: "https://picsum.photos/600/450"},
                {fullname: "Tran Long", url: "https://picsum.photos/611/450"},
                {fullname: "My Hien", url: "https://picsum.photos/620/452"},

            ],
            backgroundUrl:
                "https://picsum.photos/620/400",
            avtUrl:
                "https://picsum.photos/639/400"
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

export const getUserImages = async (username) => {
    const data = [
        {
            url: "https://picsum.photos/601/400",
        },
        {
            url: "https://picsum.photos/701/400",
        },
        {
            url: "https://picsum.photos/401/400",
        },
        {
            url: "https://picsum.photos/1010/400",
        },
        {
            url: "https://picsum.photos/601/600",
        },
        {
            url: "https://picsum.photos/601/500",
        },
        {
            url: "https://picsum.photos/601/449",
        },
        {
            url: "https://picsum.photos/601/459",
        },
        {
            url: "https://picsum.photos/601/455",
        },
        {
            url: "https://picsum.photos/601/451",
        },
    ];
    await sleep(500);
    return Promise.resolve(data);
};
