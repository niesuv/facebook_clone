import userApi from "./userApi";

export const getUserByUserName = async (username) => {
  const res = await userApi.get("/?username=" + username);
  return res.data;
};

export const getUserByID = async (id) => {
  const res = await userApi.get("/" + id);
  return res.data;
};

export const createUser = async (createUserDTO) => {
  const date = createUserDTO["birthDay"];
  
  const dateVar = new Date(date).toLocaleDateString("en-GB");
  createUserDTO["birthDay"] = dateVar;
  const res = await userApi.request({
    url: "",
    method: 'put',
    data: createUserDTO,
  });
  return res.data;
}