"use client";

import { signIn } from "next-auth/react";

export default function Login() {
  return (
    <div>
      <button
        onClick={() =>
          signIn("google", {
            callbackUrl: "/dashboard",
          })
        }
      >
        Login with Google
      </button>
    </div>
  );
}
