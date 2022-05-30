import axios from "axios";

const API_URL = "http://localhost:8080/api";

class AuthService {
    login(username, password) {
        return axios.post(API_URL + "/login",
            {username, password})
            .then(response => {
                if (response.data.accessToken) {
                    localStorage.setItem("access_token", JSON.stringify(response.data))
                }
                return response.data;
            })
    }

    signup(username, password) {
        return axios.post(API_URL + "/signup",
            {username, password})
            .catch(error => {
                if (error.response()) {
                    console.log(error.response.data);
                    console.log(error.response.status);
                    console.log(error.response.headers);
                } else if (error.request) {
                    console.log(error.request);
                } else {
                    console.log('Error', error.message);
                }
            })
    }

    logout() {
        localStorage.removeItem("access_token");
    }
}