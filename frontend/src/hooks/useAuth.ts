"use client";

import { useCallback, useMemo, useState } from "react";
import { useRouter } from "next/navigation";
import Cookies from "js-cookie";
import AuthService from "@/services/auth";

const AUTH_COOKIE = "rupass.token";
const TOKEN_EXPIRES_IN_DAYS = 1;

type LoginPayload = {
  email: string;
  password: string;
};

type UseAuthResult = {
  token: string | null;
  isAuthenticated: boolean;
  isLoading: boolean;
  error: string | null;
  login: (payload: LoginPayload) => Promise<void>;
  logout: () => void;
};

export function useAuth(): UseAuthResult {
  const router = useRouter();
  const [token, setToken] = useState<string | null>(
    () => Cookies.get(AUTH_COOKIE) ?? null,
  );
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  const login = useCallback(
    async (payload: LoginPayload) => {
      try {
        setIsLoading(true);
        setError(null);

        const auth = new AuthService();

        const response = await auth.login(payload);
        const jwt = response.token;

        Cookies.set(AUTH_COOKIE, jwt, {
          expires: TOKEN_EXPIRES_IN_DAYS,
          sameSite: "lax",
        });

        setToken(jwt);
        router.push("/dashboard");
      } catch {
        setError(
          "Falha ao fazer login. Verifique suas credenciais e tente novamente.",
        );
        throw new Error("Login failed");
      } finally {
        setIsLoading(false);
      }
    },
    [router],
  );

  const logout = useCallback(() => {
    Cookies.remove(AUTH_COOKIE);
    setToken(null);
    setError(null);
  }, []);

  const isAuthenticated = useMemo(() => Boolean(token), [token]);

  return {
    token,
    isAuthenticated,
    isLoading,
    error,
    login,
    logout,
  };
}
