import React from 'react';
import { Link } from 'react-router-dom';

const NotFoundPage = () => {
  return (
    <div className="container mx-auto px-4 py-16 text-center">
      <div className="max-w-md mx-auto">
        <h1 className="text-6xl font-bold text-gray-800 mb-4">404</h1>
        <h2 className="text-3xl font-semibold text-gray-600 mb-8">
          Page Not Found
        </h2>
        <p className="text-gray-500 mb-8">
          The page you are looking for might have been removed, had its name
          changed, or is temporarily unavailable.
        </p>
        <Link
          to="/"
          className="bg-blue-500 text-white py-2 px-6 rounded hover:bg-blue-600 transition-colors"
        >
          Go to Home
        </Link>
      </div>
    </div>
  );
};

export default NotFoundPage;
