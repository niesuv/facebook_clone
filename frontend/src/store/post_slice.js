import { createSlice } from "@reduxjs/toolkit";

const postSlice = createSlice({
  name: "post",
  initialState: {
    post: [],
    isLoading: false,
    isError: false,
  },
  reducers: {
    set_load(state) {
      state.isLoading = true;
    },

    addPost(state, action) {
      state.isLoading = false;
      state.post = [...state.post, ...action.payload];
    },
    refresh(state) {
      state.post = [];
    },
  },
});

export const loadNewData = (fetchData) => {
  return async (dispatch) => {
    dispatch(set_load());
    const data = await fetchData();
    dispatch(addPost(data));
  };
};


export const { refresh, addPost, set_load } = postSlice.actions;
export default postSlice.reducer;
