import { useMutation } from "@tanstack/react-query";
import api from "../../../shared/services/axios";
import type { UserSignupFormValues } from "../types/UserSignupFormValues";

// Pass the type to useMutation: <ResponseData, ErrorType, VariablesType>
export const useSignup = () => useMutation<unknown, unknown, UserSignupFormValues>({
    mutationFn: (data) => api.post('/auth/register', data),
});