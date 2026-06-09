import { QueryClient } from "@tanstack/react-query";

const queryClient = new QueryClient({
    defaultOptions: {
        queries: {
            staleTime: 1000 * 60 * 5, // five minutes the data will stay fresh
            gcTime: 1000 * 60 * 10,
            retry: 1, //Limit automatic retries on failure
            refetchOnWindowFocus: false // Turn off aggressive refetching on window focus
        }
    }
})

export default queryClient;