import { z } from 'zod';

const passwordValidation = z
    .string()
    .min(8, 'Password must be at least 8 characters long');

//login schema
export const loginSchema = z.object({
    email: z.string().email('Please enter a valid email address'),
    password: z.string().min(1, 'Password is required'),
});

export type LoginInput = z.infer<typeof loginSchema>;

//signup schema
export const signupSchema = z
    .object({
        name: z.string().min(2, 'Name must be at least 2 characters long'),
        email: z.string().email('Please enter a valid email address'),
        password: passwordValidation,
        confirmPassword: z.string().min(1, 'Please confirm your password'),
    })
    .refine((data) => data.password === data.confirmPassword, {
        message: "Passwords do not match",
        path: ["confirmPassword"], // Points the error specificially to the confirmPassword field
    });


export type SignupInput = z.infer<typeof signupSchema>;