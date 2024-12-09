import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { fetchMovieById } from '../features/movie/movieSlice';
import { fetchTheaterTimes, setSelectedTheaterTime } from '../features/booking/bookingSlice';

const MovieDetailsPage = () => {
  const params = useParams();
  const movieId = params.movieId;
  const [selectedTheaterId, setSelectedTheaterId] = useState(1); // Default to first theater
  
  const navigate = useNavigate();
  const dispatch = useDispatch();
  
  const { selectedMovie, loading: movieLoading, error: movieError } = useSelector((state) => state.movie);
  const { theaterTimes, loading: bookingLoading, error: bookingError } = useSelector((state) => state.booking);

  useEffect(() => {
    if (movieId) {
      dispatch(fetchMovieById(movieId));
      dispatch(fetchTheaterTimes({ theaterId: selectedTheaterId, movieId }));
    }
  }, [dispatch, movieId, selectedTheaterId]);

  if (movieLoading || bookingLoading) return <div>Loading...</div>;
  if (movieError) return <div className="text-red-500 p-4">Error: {movieError}</div>;
  if (bookingError) return <div className="text-red-500 p-4">Error loading theater times: {bookingError}</div>;
  if (!selectedMovie) return <div className="text-yellow-500 p-4">Movie not found. Please check the movie ID and try again.</div>;

  const formatShowTime = (beginTime) => {
    if (!beginTime) return '';
    // Since beginTime is just a time string like "14:00:00", we can format it directly
    const [hours, minutes] = beginTime.split(':');
    const time = new Date();
    time.setHours(parseInt(hours), parseInt(minutes));
    return time.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
  };

  return (
    <div className="container mx-auto px-4 py-8">
      <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
        <div>
          <img
            src={selectedMovie.movieImageUrl}
            alt={selectedMovie.movieName}
            className="w-full rounded-lg shadow-lg"
          />
        </div>
        <div>
          <h1 className="text-4xl font-bold mb-4">{selectedMovie.movieName}</h1>
          <div className="space-y-4">
            <p className="text-gray-600">{selectedMovie.description}</p>
            <div>
              <span className="font-semibold">Genre:</span> {selectedMovie.categoryName}
            </div>
            <div>
              <span className="font-semibold">Director:</span> {selectedMovie.directorName}
            </div>
            <div>
              <span className="font-semibold">Duration:</span> {selectedMovie.duration} mins
            </div>
            <div>
              <span className="font-semibold">Release Date:</span>{' '}
              {new Date(selectedMovie.releaseDate).toLocaleDateString()}
            </div>
          </div>

          <div className="mt-8">
            <h2 className="text-2xl font-bold mb-4">Available Shows</h2>
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">
                Select Theater:
              </label>
              <select
                value={selectedTheaterId}
                onChange={(e) => setSelectedTheaterId(Number(e.target.value))}
                className="block w-full p-2 mb-4 border rounded-md"
              >
                <option value={1}>Theater 1</option>
                <option value={2}>Theater 2</option>
                <option value={3}>Theater 3</option>
                <option value={4}>Theater 4</option>
              </select>
            </div>
            <div className="grid grid-cols-2 sm:grid-cols-3 gap-4">
              {theaterTimes.map((time) => (
                <button
                  key={time.id}
                  onClick={() => {
                    dispatch(setSelectedTheaterTime(time));
                    navigate(`/booking/${time.id}`);
                  }}
                  className="bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-600 transition-colors"
                >
                  {formatShowTime(time.movieBeginTime)}
                </button>
              ))}
              {theaterTimes.length === 0 && (
                <p className="text-gray-500 col-span-full">No show times available for this theater.</p>
              )}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default MovieDetailsPage;
