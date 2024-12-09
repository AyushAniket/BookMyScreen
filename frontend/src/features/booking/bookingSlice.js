import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import theaterTimeService from '../../services/theaterTimeService';

export const fetchTheaterTimes = createAsyncThunk(
  'booking/fetchTheaterTimes',
  async ({ theaterId, movieId }, { rejectWithValue }) => {
    try {
      const response = await theaterTimeService.getMovieTheaterTimeByTheaterAndMovieId(theaterId, movieId);
      return response.data;
    } catch (error) {
      return rejectWithValue(error.response?.data?.message || 'Failed to fetch theater times');
    }
  }
);

const initialState = {
  selectedTheaterTime: null,
  theaterTimes: [],
  loading: false,
  error: null,
};

const bookingSlice = createSlice({
  name: 'booking',
  initialState,
  reducers: {
    setSelectedTheaterTime: (state, action) => {
      state.selectedTheaterTime = action.payload;
    },
    clearSelectedTheaterTime: (state) => {
      state.selectedTheaterTime = null;
    },
    clearError: (state) => {
      state.error = null;
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(fetchTheaterTimes.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(fetchTheaterTimes.fulfilled, (state, action) => {
        state.loading = false;
        state.theaterTimes = action.payload;
      })
      .addCase(fetchTheaterTimes.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
      });
  },
});

export const { setSelectedTheaterTime, clearSelectedTheaterTime, clearError } = bookingSlice.actions;
export default bookingSlice.reducer;
