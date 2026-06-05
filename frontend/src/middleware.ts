import { NextResponse, NextRequest } from "next/server";
import { getAuthToken } from "@/lib/auth-cookies";
export function middleware(request: NextRequest) {
  const token = request.cookies.get("rupass.token")?.value;
  const isAuthPage = request.nextUrl.pathname.startsWith("/auth");

  if (!token && !isAuthPage) {
    return NextResponse.redirect(new URL("/auth/login", request.url));
  }

  if (token && isAuthPage) {
    return NextResponse.redirect(new URL("/dashboard", request.url));
  }

  return NextResponse.next();
}

export const config = {
  matcher: ["/dashboard/:path*", "/auth/:path*"],
};
