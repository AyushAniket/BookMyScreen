import axios from "axios";

export class ReviewService {

    apiUrl = "http://localhost:8080/api/movie/reviews/"

    getReviewsByMovieId(movieId, pageNo, pageSize=5) {
        return axios.get(this.apiUrl + "getReviewsByMovieId/" + movieId + "/" + pageNo + "/" + pageSize);
    }

    getCountOfReviews(movieId) {
        return axios.get(this.apiUrl + "getCountOfReviews/" + movieId);
    }

    addReview(reviewDto) {
        return axios.post(this.apiUrl + "add", reviewDto);
    }
    
    deleteReview(deleteReviewDto) {
        return axios.post(this.apiUrl + "delete", deleteReviewDto);
    }
}
