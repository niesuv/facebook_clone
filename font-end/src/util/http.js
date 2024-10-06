

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

const getUserDetails = async (username) => {
    if (username !== 'niesuv') {
        const data = {
            fullname: "Hữu Sang",
            totalFriend: 502,
            username,
            id: '1234',
            backgroundUrl: 'https://scontent.fsgn19-1.fna.fbcdn.net/v/t39.30808-6/326741594_1397625924315952_8369081978684846629_n.jpg?stp=dst-jpg_s960x960&_nc_cat=104&ccb=1-7&_nc_sid=cc71e4&_nc_eui2=AeHBFltDfNZa7vEiyAN6XwfY0I1RDkorEnLQjVEOSisSchMu2fmPXb2yb1F6OaBzUtsInw2dD27QiTXv345BiaWS&_nc_ohc=R0fznjYpfw0Q7kNvgFINFSG&_nc_ht=scontent.fsgn19-1.fna&_nc_gid=A6l9Ci0uAhXl7IjD8reeqtr&oh=00_AYAbgtN0fgAy2vGhGt2urAW2WhtLfyaJjJzyMW0Fl1xeUw&oe=6706C392',
            avtUrl: 'https://scontent.fsgn19-1.fna.fbcdn.net/v/t39.30808-1/447458148_1428209785247518_705161193208956860_n.jpg?stp=dst-jpg_s200x200&_nc_cat=110&ccb=1-7&_nc_sid=0ecb9b&_nc_eui2=AeFvc6Yux-WdhtPuehYxIpS4bI7Azy2yZspsjsDPLbJmysTqAA6dQw2gl73wxMxelC1FfGIbhQ87id32A2kKk7uA&_nc_ohc=WhGMhrK6J2MQ7kNvgEUZRxu&_nc_ht=scontent.fsgn19-1.fna&_nc_gid=A6l9Ci0uAhXl7IjD8reeqtr&oh=00_AYDWkBDBDhwdDrG07hOpBPkwbyf8QmJkRKas1Iy7nGWoaQ&oe=6706EDAF'
        }
        await sleep(500);
        return Promise.resolve(data);
    }
};

export { getUserDetails };
