import axios from "axios"

export class DirectorService{
    apiUrl = "http://localhost:8080/api/movie/directors/"

    getall() {
        return axios.get(this.apiUrl + "getAll")
    }

    add(director) {
        return axios.post(this.apiUrl + "add", director);
    }
}
