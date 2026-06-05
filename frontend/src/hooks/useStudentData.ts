"use client";

import { jwtDecode } from "jwt-decode";
import useSWR from "swr";
import { useAuth } from "@/hooks/useAuth";
import { deleter, fetcher, patcher } from "@/services/api";
import useSWRMutation from "swr/mutation";
import { get } from "http";

export interface StudentData {
  id: string;
  fullName: string;
  email: string;
  registrationNumber: string;
  course: string;
  photoUrl?: string | null;
}

export interface EditStudentData {
  fullName?: string;
  email?: string;
  password?: string;
}

export interface DigitalIdResponse {
  fullName: string;
  registrationNumber: string;
  course: string;
  photoUrl?: string | null;
}

export interface WalletResponse {
  id: string;
  breakfastBalance: number;
  lunchDinnerBalance: number;
}

export interface OrderHistoryItem {
  orderId: string;
  createdAt: string;
  breakfastQuantity: number;
  lunchDinnerQuantity: number;
  totalAmount: number;
  status: "PENDING" | "APPROVED" | "REJECTED" | "CANCELLED";
}

export interface PaginatedOrderResponse {
  content: OrderHistoryItem[];
  totalPages: number;
  last: boolean;
  first: boolean;
  number: number;
}

type JwtPayload = {
  sub?: string;
  studentId?: string;
};

export function useStudentData() {
  const { token } = useAuth();

  const studentId = getStudentIdFromToken(token);

  const { data, error, isLoading, mutate } = useSWR<StudentData>(
    studentId ? `/students/${studentId}` : null,
    fetcher,
  );

  return {
    studentData: data,
    isLoading,
    error,
    mutate,
  };
}

export function getStudentIdFromToken(token: string | null): string | null {
  if (!token) {
    return null;
  }

  try {
    const decoded = jwtDecode<JwtPayload>(token);
    return decoded.studentId ?? null;
  } catch {
    return null;
  }
}

export function useDigitalId() {
  const { token } = useAuth();

  const studentId = getStudentIdFromToken(token);

  const { data, error, isLoading, mutate } = useSWR<DigitalIdResponse>(
    studentId ? `/students/${studentId}/digital-id` : null,
    fetcher,
  );

  return {
    digitalId: data,
    isLoading,
    error,
    mutate,
  };
}

export function useWallet() {
  const { token } = useAuth();
  const studentId = getStudentIdFromToken(token);

  const { data, error, isLoading, mutate } = useSWR<WalletResponse>(
    studentId ? `/students/${studentId}/wallet` : null,
    fetcher,
  );

  return {
    wallet: data,
    isLoading,
    error,
    mutate,
  };
}

export function useOrderHistory(page: number) {
  const { token } = useAuth();
  const studentId = getStudentIdFromToken(token);

  const { data, error, isLoading } = useSWR<PaginatedOrderResponse>(
    studentId ? `/students/${studentId}/orders?page=${page}&size=5` : null,
    fetcher,
  );

  return {
    data,
    isLoading,
    error,
  };
}

export function useEditStudent() {
  const { token } = useAuth();
  const studentId = getStudentIdFromToken(token);
  const endpoint = `/students/${studentId}`;

  const { trigger, isMutating, error } = useSWRMutation(endpoint, patcher);

  return {
    updatedStudent: trigger,
    isLoading: isMutating,
    error,
  };
}

export function useDeleteStudent() {
  const { token } = useAuth();
  const studentId = getStudentIdFromToken(token);
  const endpoint = `/students/${studentId}`;

  const { trigger, isMutating, error } = useSWRMutation(endpoint, deleter);

  return {
    deletedStudent: trigger,
    isLoading: isMutating,
    error,
  };
}
