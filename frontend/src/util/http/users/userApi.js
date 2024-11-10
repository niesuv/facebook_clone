import axios from "axios";
import { GATEWAY } from "../../const";

const userApi = axios.create(
    {
        baseURL: GATEWAY + "/api/v1/user",
        
    }

)

userApi.interceptors.request.use((config) => {
    
    //implement later
    
    return config;
})

export default userApi;