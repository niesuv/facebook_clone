import user_slice from './user_slice'

import { configureStore } from '@reduxjs/toolkit'
import post_slice from './post_slice'



export const store = configureStore({
  reducer: {
    user: user_slice,
    post: post_slice
  }
})

export default store;