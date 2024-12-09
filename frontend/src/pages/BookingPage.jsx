import React, { useState, useEffect } from 'react';
import { useSelector } from 'react-redux';
import { useNavigate, Link, useParams } from 'react-router-dom';

const ROWS = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'];
const COLS = [1, 2, 3, 4, 5, 6, 7, 8];

const BookingPage = () => {
  const navigate = useNavigate();
  const { theaterId } = useParams();
  const [selectedSeats, setSelectedSeats] = useState([]);
  
  // Get states from Redux
  const { selectedMovie } = useSelector((state) => state.movie);
  const { selectedTheaterTime } = useSelector((state) => state.booking);
  
  useEffect(() => {
    // If we don't have the necessary data, redirect to home
    if (!selectedTheaterTime || !selectedMovie) {
      navigate('/');
      return;
    }
  }, [selectedTheaterTime, selectedMovie, navigate]);

  // If we don't have the necessary data, show loading
  if (!selectedTheaterTime || !selectedMovie) {
    return <div className="container mx-auto px-4 py-8 text-center">Loading...</div>;
  }

  const formatShowTime = (beginTime) => {
    if (!beginTime) return '';
    // Since beginTime is just a time string like "14:00:00", we can format it directly
    const [hours, minutes] = beginTime.split(':');
    const time = new Date();
    time.setHours(parseInt(hours), parseInt(minutes));
    return time.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
  };

  const handleSeatClick = (seatId) => {
    setSelectedSeats(prev => 
      prev.includes(seatId)
        ? prev.filter(id => id !== seatId)
        : [...prev, seatId]
    );
  };

  const formatSeatNumbers = (seats) => {
    return seats.sort().map(seat => seat).join(', ');
  };

  const handleBooking = () => {
    if (selectedSeats.length === 0) {
      alert('Please select at least one seat');
      return;
    }
    // Navigate to payment with selected seats
    navigate('/payment', { 
      state: { 
        seats: selectedSeats,
        totalAmount: selectedSeats.length * 10, // $10 per seat
        movieId: selectedMovie.movieId,
        theaterId: selectedTheaterTime.id,
        showTime: selectedTheaterTime.movieBeginTime
      } 
    });
  };

  return (
    <div className="container mx-auto px-4 py-8">
      <div className="max-w-4xl mx-auto">
        {/* Movie Info */}
        <div className="mb-8">
          <h1 className="text-3xl font-bold mb-2">{selectedMovie.movieName}</h1>
          <p className="text-gray-600">
            {formatShowTime(selectedTheaterTime.movieBeginTime)} | {selectedTheaterTime.theater?.name || 'Theater'}
          </p>
        </div>

        {/* Screen */}
        <div className="mb-12">
          <div className="w-full h-4 bg-gray-300 rounded-t-lg mb-2"></div>
          <p className="text-center text-sm text-gray-500">SCREEN</p>
        </div>

        {/* Seat Legend */}
        <div className="flex gap-4 justify-center mb-8">
          <div className="flex items-center gap-2">
            <div className="w-6 h-6 bg-gray-200 rounded"></div>
            <span className="text-sm">Available</span>
          </div>
          <div className="flex items-center gap-2">
            <div className="w-6 h-6 bg-blue-500 rounded"></div>
            <span className="text-sm">Selected</span>
          </div>
          <div className="flex items-center gap-2">
            <div className="w-6 h-6 bg-gray-400 rounded"></div>
            <span className="text-sm">Booked</span>
          </div>
        </div>

        {/* Seat Grid */}
        <div className="mb-8">
          <div className="grid grid-cols-9 gap-2">
            {/* Column Numbers */}
            <div className="h-8"></div> {/* Empty cell for row letters */}
            {COLS.map(col => (
              <div key={col} className="flex items-center justify-center h-8 text-sm text-gray-500">
                {col}
              </div>
            ))}

            {/* Seats */}
            {ROWS.map(row => (
              <React.Fragment key={row}>
                {/* Row Letter */}
                <div className="flex items-center justify-center h-8 text-sm text-gray-500">
                  {row}
                </div>
                {/* Seats in this row */}
                {COLS.map(col => {
                  const seatId = `${row}${col}`;
                  return (
                    <button
                      key={seatId}
                      onClick={() => handleSeatClick(seatId)}
                      className={`
                        w-8 h-8 rounded text-xs
                        ${selectedSeats.includes(seatId)
                          ? 'bg-blue-500 text-white hover:bg-blue-600'
                          : 'bg-gray-200 hover:bg-gray-300'
                        }
                        transition-colors
                      `}
                      aria-label={`Seat ${seatId}`}
                    >
                      {seatId}
                    </button>
                  );
                })}
              </React.Fragment>
            ))}
          </div>
        </div>

        {/* Booking Summary */}
        <div className="mt-8 bg-gray-50 rounded-lg p-6">
          <h2 className="text-xl font-semibold mb-4">Booking Summary</h2>
          <div className="space-y-2 mb-4">
            <p>
              <span className="font-medium">Selected Seats:</span>{' '}
              {selectedSeats.length > 0 ? formatSeatNumbers(selectedSeats) : 'None'}
            </p>
            <p>
              <span className="font-medium">Price per Seat:</span> $10
            </p>
            <p className="text-lg font-semibold">
              Total Amount: ${selectedSeats.length * 10}
            </p>
          </div>
          <button
            onClick={handleBooking}
            className="w-full bg-blue-500 text-white py-3 px-6 rounded-lg hover:bg-blue-600 transition-colors disabled:bg-gray-300 disabled:cursor-not-allowed"
            disabled={selectedSeats.length === 0}
          >
            {selectedSeats.length > 0
              ? `Proceed to Payment ($${selectedSeats.length * 10})`
              : 'Select seats to continue'}
          </button>
        </div>
      </div>
    </div>
  );
};

export default BookingPage;
