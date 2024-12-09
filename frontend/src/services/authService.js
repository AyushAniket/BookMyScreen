import axios from "axios";

class AuthService {
    apiUrl = "http://localhost:8080/api/user/auth/";

    async register(registerDto) {
        try {
            const response = await axios.post(this.apiUrl + "register", registerDto);
            if (response.data.token) {
                localStorage.setItem("user", JSON.stringify(response.data));
            }
            return response.data;
        } catch (error) {
            throw this.handleError(error);
        }
    }

    async login(loginDto) {
        try {
            const response = await axios.post(this.apiUrl + "login", loginDto);
            if (response.data.token) {
                localStorage.setItem("user", JSON.stringify(response.data));
            }
            return response.data;
        } catch (error) {
            throw this.handleError(error);
        }
    }

    getCurrentUser() {
        const user = localStorage.getItem("user");
        return user ? JSON.parse(user) : null;
    }

    getToken() {
        const user = this.getCurrentUser();
        return user?.token;
    }

    logout() {
        localStorage.removeItem("user");
    }

    isAuthenticated() {
        return this.getCurrentUser() !== null;
    }

    handleError(error) {
        if (error.response?.data?.message) {
            return new Error(error.response.data.message);
        }
        return error;
    }

    setupAxiosInterceptors() {
        axios.interceptors.request.use(
            config => {
                const token = this.getToken();
                if (token) {
                    config.headers.Authorization = `Bearer ${token}`;
                }
                return config;
            },
            error => {
                return Promise.reject(error);
            }
        );

        axios.interceptors.response.use(
            response => response,
            error => {
                if (error.response?.status === 401) {
                    this.logout();
                    window.location.href = '/login';
                }
                return Promise.reject(error);
            }
        );
    }
}

const authService = new AuthService();
authService.setupAxiosInterceptors();
export default authService;
