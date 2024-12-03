import { createSlice } from "@reduxjs/toolkit";

const initialState = {
    id : "",
    userName: "",
    fullName: "",
    avtUrl: null,
    email: "",
    backgroundUrl: 'https://picsum.photos/600/400',
    birthday: null
}

const userSlice = createSlice({
    name: "user",
    initialState,
    reducers: {
        logout(state) {
            state = initialState;
        },
        login(state, actions) {
            return actions.payload;
        }

    }
})


export const {login, logout} = userSlice.actions;
export default userSlice.reducer;