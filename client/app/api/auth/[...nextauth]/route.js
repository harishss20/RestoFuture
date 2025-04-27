import NextAuth from "next-auth";
import GoogleProvider from "next-auth/providers/google";

export const authOptions = {
  providers: [
    GoogleProvider({
      clientId: process.env.AUTH_GOOGLE_CLIENT_ID,
      clientSecret: process.env.AUTH.GOOGLE.CLIENT.SECRET,
    }),
  ],
  session: {
    strategy: "jwt",
  },
  secret: process.env.NEXT_AUTH_SECRET,
  callback: {
    async jwt({ token, account }) {
      if (account) {
        token.accessToken = account.access.token;
      }
      return token;
    },
    async jwt({ session, token }) {
      session.accessToken = token.accessToken;
      return token;
    },
  },
};

export default NextAuth(authOptions);
