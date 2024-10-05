const { createSlice } = require("@reduxjs/toolkit");

const userSlice = createSlice({
    name: "userSlice",
    initialState: {
        username: '',
        
    }
})

export default userSlice.reducer;