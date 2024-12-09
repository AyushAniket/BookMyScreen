import React from 'react';
import { Box } from '@mui/material';
import { Routes, Route } from 'react-router-dom';
import { useSelector } from 'react-redux';

import Navbar from './Navbar';
import Footer from './Footer';
import HomePage from '../pages/HomePage';
import MovieDetailsPage from '../pages/MovieDetailsPage';
import BookingPage from '../pages/BookingPage';
import PaymentSuccessPage from '../pages/PaymentSuccessPage';
import AdminMoviePage from '../pages/admin/AdminMoviePage';
import AdminMovieDetailsPage from '../pages/admin/AdminMovieDetailsPage';
import ProtectedRoute from '../components/ProtectedRoute';
import NotFoundPage from '../pages/NotFoundPage';

export default function Layout() {
  const user = useSelector((state) => state.auth.user);

  return (
    <Box sx={{ display: 'flex', flexDirection: 'column', minHeight: '100vh' }}>
      <Navbar />
      
      <Box component="main" sx={{ flexGrow: 1, py: 3 }}>
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/movie/:movieId" element={<MovieDetailsPage />} />
          <Route path="/booking/:theaterId" element={<BookingPage />} />
          <Route path="/payment-success" element={<PaymentSuccessPage />} />
          
          {/* Admin Routes */}
          <Route
            path="/admin/movie/add"
            element={
              <ProtectedRoute isAdmin={true}>
                <AdminMoviePage />
              </ProtectedRoute>
            }
          />
          <Route
            path="/admin/movie/:movieId/edit"
            element={
              <ProtectedRoute isAdmin={true}>
                <AdminMovieDetailsPage />
              </ProtectedRoute>
            }
          />
          
          {/* 404 Page */}
          <Route path="*" element={<NotFoundPage />} />
        </Routes>
      </Box>

      <Footer />
    </Box>
  );
}
