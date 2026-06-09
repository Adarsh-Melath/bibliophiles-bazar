
import axios from 'axios'
import { useAuthStore } from '../store/useAuthStore';

//create a custom axios instance 

const api = axios.create({
    baseURL: import.meta.env.VITE_BASE_URL,
    withCredentials: true
})

// Automatically attach the JWT token to every outgoing request
api.interceptors.request.use(
    (config) => {
        const token = useAuthStore.getState().accessToken; // Or pull from your preferred 

        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

// Handle token expiration and refresh logic globally
api.interceptors.response.use(
    (response) => response,//directly return successfull responses
    async (error) => {
        const originalRequest = error.config
        // Check if error status is 401 and the request hasn't been retried yet
        if (error.response?.status === 401 && !originalRequest._retry) {
            originalRequest._retry = true; // mark request to avoid infinte loops

            try {
                const res = await axios.post(
                    `${import.meta.env.VITE_API_BASE_URL}/auth/refresh`,
                    {},
                    { withCredentials: true },
                )

                const newToken = res.data.accessToken;
                useAuthStore.getState().setAuth(useAuthStore.getState().user, newToken);

                // Update the failed request's authorization header with the new token
                originalRequest.headers.Authorization = `Bearer ${newToken}`;

                // Retry the original request with the updated config
                return api(originalRequest);
            } catch (refreshError) {
                useAuthStore.getState().clearAuth();
            }
            return Promise.reject(error);
        }
    }
)

export default api;