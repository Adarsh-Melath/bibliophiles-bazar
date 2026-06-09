import { create } from 'zustand'
import { createJSONStorage, persist } from 'zustand/middleware'

interface User {
    id: string;
    email: string;
    name: string;
}

interface AuthState {
    //states
    user: User | null
    accessToken: string | null
    isAuthenticated: boolean

    //actions
    setAuth: (user: User | null, token: string) => void;
    clearAuth: () => void;
    updateUser: (user: Partial<User>) => void;
}

export const useAuthStore = create<AuthState>()(
    persist(
        (set) => ({
            // Initial States
            user: null,
            accessToken: null,
            isAuthenticated: false,

            // Actions
            setAuth: (user, token) =>
                set({
                    user,
                    accessToken: token,
                    isAuthenticated: true
                }),

            clearAuth: () =>
                set({
                    user: null,
                    accessToken: null,
                    isAuthenticated: false
                }),

            updateUser: (updatedFields) =>
                set((state) => ({
                    user: state.user ? { ...state.user, ...updatedFields } : null
                })),
        }),
        {
            name: 'auth-storage', // LocalStorage key
            storage: createJSONStorage(() => localStorage),
            // Industry Best Practice: Do NOT save the access token to localStorage.
            // Only persist the non-sensitive user profile details.
            partialize: (state) => ({
                user: state.user,
                isAuthenticated: state.isAuthenticated
            }),
        }
    ));