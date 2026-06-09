import { zodResolver } from "@hookform/resolvers/zod";
import { useState } from "react";
import { useForm } from "react-hook-form";
import { useSignup } from "../hooks/useSignup";
import { signupSchema, type SignupInput } from "../schemas/auth";
import { Eye, EyeOff } from "lucide-react";
import type { UserSignupFormValues } from "../types/UserSignupFormValues";

function SignUpForm() {
  const [showPassword, setShowPassword] = useState(false);
  const [showConfirmPassword, setConfirmPassword] = useState(false);
  const [otpEmail, setOtpEmail] = useState("");

  const {
    register,
    handleSubmit,
    formState: { errors, isSubmitting },
  } = useForm<SignupInput>({
    resolver: zodResolver(signupSchema),
    mode: "onTouched",
  });

  const { mutate: signup, error, isPending } = useSignup();

  const onSubmit = (data: UserSignupFormValues) => {
    signup(
      {
        name: data.name,
        email: data.email,
        password: data.password,
      },
      { onSuccess: () => setOtpEmail(data.email) },
    );
  };

  return (
    <>
      <h1>Create an account</h1>

      <form onSubmit={handleSubmit(onSubmit)}>
        <div>
          <label htmlFor="name">Name</label>
          <input
            id="name"
            type="text"
            {...register("name")}
            placeholder="enter your name"
          ></input>
          {errors.name && <p>{errors.name.message}</p>}
        </div>

        <div>
          <label htmlFor="email">Email</label>
          <input
            type="email"
            id="email"
            placeholder="enter your email"
            {...register("email")}
          />
          {errors.email && <p>{errors.email.message}</p>}
        </div>

        <div>
          <label htmlFor="password">Password</label>
          <input
            id="password"
            type={showPassword ? "text" : "password"}
            {...register("password")}
          />
          <button onClick={() => setShowPassword((password) => !password)}>
            {showPassword ? <EyeOff /> : <Eye />}{" "}
          </button>
          {errors.password && <p>{errors.password.message}</p>}
        </div>

        <div>
          <label htmlFor="confirmPassword">Confirm Password</label>
          <input
            id="confirmPassword"
            type={showConfirmPassword ? "text" : "password"}
            {...register("confirmPassword")}
          />
          <button onClick={() => setConfirmPassword((password) => !password)}>
            {showPassword ? <EyeOff /> : <Eye />}{" "}
          </button>
          {errors.confirmPassword && <p>{errors.confirmPassword.message}</p>}
        </div>

        <button type="submit" disabled={isSubmitting}>
          {isSubmitting ? "Signing up..." : "Sign Up"}
        </button>
      </form>
    </>
  );
}

export default SignUpForm;
