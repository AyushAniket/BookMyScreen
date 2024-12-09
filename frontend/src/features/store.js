import { configureStore } from '@reduxjs/toolkit';
import authReducer from './auth/authSlice';
import movieReducer from './movie/movieSlice';
import bookingReducer from './booking/bookingSlice';

export const store = configureStore({
  reducer: {
    auth: authReducer,
    movie: movieReducer,
    booking: bookingReducer,
  },
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware({
      serializableCheck: false,
    }),
});
