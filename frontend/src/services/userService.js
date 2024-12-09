import axios from "axios";

class UserService {
    apiUrl = "http://localhost:8080/api/user/users/";

    async handleRequest(requestPromise) {
        try {
            const response = await requestPromise;
            return response.data;
        } catch (error) {
            if (error.response?.data?.message) {
                throw new Error(error.response.data.message);
            }
            throw error;
        }
    }

    async register(registerData) {
        const userRegisterRequest = {
            email: registerData.email,
            password: registerData.password,
            customerName: registerData.fullName
        };
        return this.handleRequest(axios.post(this.apiUrl + "add", userRegisterRequest));
    }

    async isUserAdmin() {
        return this.handleRequest(axios.get(this.apiUrl + "isUserAdmin"));
    }

    async isUserCustomer() {
        return this.handleRequest(axios.get(this.apiUrl + "isUserCustomer"));
    }

    async isUserExists(userId) {
        return this.handleRequest(axios.get(this.apiUrl + "isExist/" + userId));
    }
}

const userService = new UserService();
export default userService;