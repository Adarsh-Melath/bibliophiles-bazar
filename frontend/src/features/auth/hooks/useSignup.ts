import { useMutation } from "@tanstack/react-query";
import api from "../../../shared/services/axios";

export const useSignup = () => useMutation({
    mutationFn: (data) => api.post('/auth/register', data),
});