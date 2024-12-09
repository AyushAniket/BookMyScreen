import axios from "axios";

export class TheaterTimeService {
    apiUrl = "http://localhost:8080/api/movie/movieTheaterTimes/"

    getMovieTheaterTimeByTheaterAndMovieId(theaterId, movieId) {
        return axios.get(this.apiUrl + "getMovieTheaterTimeTheaterAndMovieId/" + theaterId + "/" + movieId);
    }
}

const theaterTimeService = new TheaterTimeService();
export default theaterTimeService;
