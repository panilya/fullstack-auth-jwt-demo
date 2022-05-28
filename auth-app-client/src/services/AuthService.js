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
}