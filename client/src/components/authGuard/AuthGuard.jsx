"use client";

import { usePathname, useRouter } from "next/navigation";

import { useEffect } from "react";
import { useSession } from "next-auth/react";

export default function AuthGuard({
  children,
}) {
  const { status } = useSession();
  const pathname = usePathname();
  const router = useRouter();

  useEffect(() => {
    if (status === "unauthenticated" && pathname !== "/login") {
      router.replace("/login");
    }

    if (status === "authenticated" && pathname === "/login") {
      router.replace("/dashboard");
    }
  }, [status, pathname, router]);

  if (status === "loading") return null;

  return <>{children}</>;
}
