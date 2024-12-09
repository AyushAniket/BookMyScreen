# Cinema Booking System Frontend

A modern, responsive cinema ticket booking system built with React and Material UI.

## Features

- Browse movies with dynamic filtering and search
- View detailed movie information
- Book tickets with seat selection
- Secure payment processing
- User authentication and profile management
- Admin panel for movie and screening management

## Tech Stack

- React 18
- Material UI 5
- Redux Toolkit for state management
- React Router 6 for navigation
- Formik & Yup for form validation
- Axios for API communication
- React Toastify for notifications
- Swiper for carousels
- Date-fns for date manipulation

## Getting Started

1. Install dependencies:
```bash
npm install
```

2. Start the development server:
```bash
npm start
```

3. Build for production:
```bash
npm run build
```

## Project Structure

```
src/
├── components/       # Reusable UI components
├── layouts/          # Layout components (Navbar, Footer, etc.)
├── pages/           # Page components
├── features/        # Redux slices and related logic
├── services/        # API services
├── hooks/           # Custom hooks
├── utils/           # Utility functions
└── theme/           # Material UI theme customization
```

## Environment Variables

Create a `.env` file in the root directory with:

```
REACT_APP_API_URL=your_api_url
```
