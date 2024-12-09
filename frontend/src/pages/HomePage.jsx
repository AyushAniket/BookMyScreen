import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { fetchMovies } from '../features/movie/movieSlice';
import { Link } from 'react-router-dom';
import './HomePage.css';

const HomePage = () => {
  const dispatch = useDispatch();
  const { movies, loading, error } = useSelector((state) => state.movie);

  useEffect(() => {
    dispatch(fetchMovies());
  }, [dispatch]);

  console.log('Movies in HomePage:', movies);

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;

  return (
    <div className="container">
      <h1 className="text-3xl font-bold mb-8">Now Showing</h1>
      <div className="movie-grid">
        {movies && movies.map((movie) => {
          console.log('Movie in map:', movie);
          return (
            <div key={movie.movieId} className="movie-card">
              <Link
                to={`/movie/${movie.movieId}`}
                onClick={() => console.log('Clicked movie:', movie)}
                className="block"
              >
                <div className="movie-poster-container">
                  <img
                    src={movie.movieImageUrl}
                    alt={movie.movieName}
                    className="movie-poster"
                  />
                </div>
                <div className="movie-info">
                  <h2 className="movie-title">{movie.movieName}</h2>
                  <div className="movie-details">
                    <p>{movie.categoryName}</p>
                    <p>{movie.duration} mins</p>
                  </div>
                </div>
              </Link>
            </div>
          );
        })}
      </div>
    </div>
  );
};

export default HomePage;
