// src/app/api/auth/[...nextauth]/route.ts

import GoogleProvider from "next-auth/providers/google";
import NextAuth from "next-auth";
import axios from "axios";

export const authOptions = {
  providers: [
    GoogleProvider({
      clientId: process.env.GOOGLE_CLIENT_ID,
      clientSecret: process.env.GOOGLE_CLIENT_SECRET,
      authorization: {
        params: {
          scope: "openid email profile"
        },
      },
      profile(profile) {
        console.log("✅ Google profile raw:", profile);
        return {
          id: profile.sub,
          name: profile.name,
          email: profile.email,
          image: profile.picture,
        };
      },
    }),
  ],
  callbacks: {
    async signIn({ profile }) {
  console.log(" signIn callback: profile", profile);

  try {
    const res = await axios.post("http://localhost:8080/api/login", {
      email: profile.email,
      name: profile.name,
      oauthProvider: "google",
      oauthId: profile.sub,
    });

    console.log("Backend response:", res.data);
  } catch (err) {
    console.error(" Backend API error:", err.message);
    if (err.response) {
      console.error("➡ Status:", err.response.status);
      console.error("➡ Redirected to:", err.response.headers.location);
    }
  }

  return true;
},


    async session({ session }) {
      return session;
    },
  },
  session: {
    strategy: "jwt",
  },
  debug: true,
};

const handler = NextAuth(authOptions);
export { handler as GET, handler as POST };
