import Cookies from "js-cookie";

export const AUTH_TOKEN_COOKIE = "rupass.token";
const TOKEN_EXPIRES_IN_DAYS = 1;

export function saveAuthToken(token: string): void {
  Cookies.set(AUTH_TOKEN_COOKIE, token, {
    expires: TOKEN_EXPIRES_IN_DAYS,
    sameSite: "lax",
  });
}

export function getAuthToken(): string | undefined {
  return Cookies.get(AUTH_TOKEN_COOKIE);
}

export function removeAuthToken(): void {
  Cookies.remove(AUTH_TOKEN_COOKIE);
}
