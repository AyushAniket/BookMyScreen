-- Insert Categories
INSERT INTO category (category_id, category_name) VALUES 
(1, 'Action'),
(2, 'Comedy'),
(3, 'Drama'),
(4, 'Science Fiction'),
(5, 'Adventure')
ON CONFLICT (category_id) DO NOTHING;

-- Insert Directors
INSERT INTO director (director_id, director_name) VALUES 
(1, 'Christopher Nolan'),
(2, 'Martin Scorsese'),
(3, 'Steven Spielberg'),
(4, 'Quentin Tarantino'),
(5, 'James Cameron')
ON CONFLICT (director_id) DO NOTHING;

-- Insert Movies
INSERT INTO movie (movie_id, movie_name, description, duration, release_date, is_display, movie_trailer_url, category_id, director_id) VALUES 
(1, 'Inception', 'A thief who steals corporate secrets through dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.', 148, '2010-07-16', true, 'https://www.youtube.com/watch?v=YoHD9XEInc0', 1, 1),
(2, 'Oppenheimer', 'The story of American scientist J. Robert Oppenheimer and his role in the development of the atomic bomb.', 180, '2023-07-21', true, 'https://www.youtube.com/watch?v=uYPbbksJxIg', 3, 1),
(3, 'Jurassic Park', 'A pragmatic paleontologist visiting an almost complete theme park is tasked with protecting a couple of kids after a power failure causes the park''s cloned dinosaurs to run loose.', 127, '1993-06-11', true, 'https://www.youtube.com/watch?v=QWBKEmWWL38', 5, 3),
(4, 'Pulp Fiction', 'The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.', 154, '1994-10-14', true, 'https://www.youtube.com/watch?v=s7EdQ4FqbhY', 3, 4),
(5, 'Avatar', 'A paraplegic Marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.', 162, '2009-12-18', true, 'https://www.youtube.com/watch?v=5PSNL1qE6VY', 4, 5)
ON CONFLICT (movie_id) DO NOTHING;

-- Insert Movie Images
INSERT INTO movie_image (image_id, image_url, movie_id) VALUES 
(1, 'https://m.media-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_FMjpg_UX1000_.jpg', 1),
(2, 'https://m.media-amazon.com/images/M/MV5BMDBmYTZjNjUtN2M1MS00MTQ2LTk2ODgtNzc2M2QyZGE5NTVjXkEyXkFqcGdeQXVyNzAwMjU2MTY@._V1_.jpg', 2),
(3, 'https://m.media-amazon.com/images/M/MV5BMjM2MDgxMDg0Nl5BMl5BanBnXkFtZTgwNTM2OTM5NDE@._V1_.jpg', 3),
(4, 'https://m.media-amazon.com/images/M/MV5BNGNhMDIzZTUtNTBlZi00MTRlLWFjM2ItYzViMjE3YzI5MjljXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_.jpg', 4),
(5, 'https://m.media-amazon.com/images/M/MV5BZDA0OGQxNTItMDZkMC00N2UyLTg3MzMtYTJmNjg3Nzk5MzRiXkEyXkFqcGdeQXVyMjUzOTY1NTc@._V1_.jpg', 5)
ON CONFLICT (image_id) DO NOTHING;

-- Insert Cities
INSERT INTO city (city_id, city_name, movie_id) VALUES 
(1, 'New York', 1),
(2, 'Los Angeles', 1),
(3, 'Chicago', 2),
(4, 'Houston', 2),
(5, 'Phoenix', 3)
ON CONFLICT (city_id) DO NOTHING;

-- Insert Theaters
INSERT INTO theater (theater_id, theater_name, city_id) VALUES 
(1, 'Cineplex Downtown', 1),
(2, 'Starlight Multiplex', 1),
(3, 'Royal Cinema', 2),
(4, 'IMAX Experience', 2),
(5, 'Sunset Theater', 3)
ON CONFLICT (theater_id) DO NOTHING;

-- Insert Movie Theater Times
INSERT INTO movie_theater_time (id, movie_begin_time, theater_id, movie_id) VALUES 
(1, '14:00:00', 1, 1),
(2, '17:30:00', 1, 1),
(3, '15:00:00', 2, 1),
(4, '19:00:00', 3, 1),
(5, '16:00:00', 1, 2),
(6, '18:30:00', 2, 2),
(7, '20:00:00', 3, 2),
(8, '15:30:00', 4, 2),
(9, '13:00:00', 2, 3),
(10, '16:30:00', 3, 3)
ON CONFLICT (id) DO NOTHING;

-- Insert Actors first (before their images)
INSERT INTO actor (actor_id, actor_name, movie_id) VALUES
(1, 'Leonardo DiCaprio', 1),
(2, 'Joseph Gordon-Levitt', 1),
(3, 'Cillian Murphy', 2),
(4, 'Emily Blunt', 2),
(5, 'Sam Neill', 3),
(6, 'Laura Dern', 3),
(7, 'John Travolta', 4),
(8, 'Samuel L. Jackson', 4),
(9, 'Sam Worthington', 5),
(10, 'Zoe Saldana', 5)
ON CONFLICT (actor_id) DO NOTHING;

-- Insert Actor Images (after actors are inserted)
INSERT INTO actor_image (image_id, image_url, actor_id) VALUES
(1, 'https://example.com/leonardo.jpg', 1),
(2, 'https://example.com/joseph.jpg', 2),
(3, 'https://example.com/cillian.jpg', 3),
(4, 'https://example.com/emily.jpg', 4),
(5, 'https://example.com/sam-neill.jpg', 5),
(6, 'https://example.com/laura.jpg', 6),
(7, 'https://example.com/john.jpg', 7),
(8, 'https://example.com/samuel.jpg', 8),
(9, 'https://example.com/sam-worthington.jpg', 9),
(10, 'https://example.com/zoe.jpg', 10)
ON CONFLICT (image_id) DO NOTHING;

-- Insert Reviews
INSERT INTO review (review_id, review_text, review_by, review_by_user_id, rating, created_at, updated_at, movie_id) VALUES
(1, 'Mind-bending masterpiece! The visuals are stunning and the plot keeps you guessing until the end.', 'John Doe', 'user123', 4.5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
(2, 'Incredible performances by the entire cast, especially DiCaprio and Gordon-Levitt.', 'Jane Smith', 'user456', 5.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
(3, 'A biographical drama that delves into the life of J. Robert Oppenheimer, a complex and intriguing character.', 'Mike Johnson', 'user789', 4.8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
(4, 'Timeless adventure that revolutionized special effects. Still holds up today!', 'Sarah Wilson', 'user101', 4.7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3),
(5, 'Revolutionary filmmaking with groundbreaking visual effects. A true cinematic experience.', 'David Brown', 'user202', 4.9, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5),
(6, 'The plot can be confusing at times, but the overall experience is worth it.', 'Emily White', 'user303', 4.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
(7, 'Perfect blend of action and drama. The casting is spot on.', 'Robert Black', 'user404', 4.6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
(8, 'A nostalgic journey back to when dinosaurs ruled the cinema.', 'Lisa Green', 'user505', 4.5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3),
(9, 'Tarantino''s masterpiece. The non-linear storytelling is brilliant.', 'Tom Gray', 'user606', 4.8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4),
(10, 'A visual feast with a meaningful message about nature and humanity.', 'Alice Blue', 'user707', 4.7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5)
ON CONFLICT (review_id) DO NOTHING;
