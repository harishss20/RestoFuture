"use client";

import React, { useEffect } from "react";

import { useRouter } from "next/navigation";

function AuthGuard({ children }) {
  const router = useRouter();

  useEffect(() => {
    const token = localStorage.getItem("token");
    if (token) {
      router.replace("/");
      return;
    } else {
      router.replace("/login");
    }
  });
  return <div>{children}</div>;
}

export default AuthGuard;
